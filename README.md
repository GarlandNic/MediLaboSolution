
# Application MediLaboSolution

Projet P9 de la formation OpenclassRooms Développeur d'application Java : Développez une solution en microservices pour votre client

Auteur : Nicolas Garland


## Description

L'application MediLaboSolution est une application à destination des médecins.
Celle-ci doit les aider à gérer leurs patients, notamment dans le cadre d'une surveillance des risque de diabète.

### Fonctionnalités

Avec cette application le médecin est capable de :
- Se connecter à l'application de manière sécurisée,
	- Le reste des opérations n'est pas possible si le bon identifiant et le bon mot de passe ne sont pas donnés.
	- L'identifiant et le mot de passe sont pour l'instant inscrits en dur dans le code (1 seul identifiant/mot de passe est fonctionnel) :
		- identifiant : "docteur"
		- mot de passe : "docteur".
- Consulter et modifier la liste des patients,
	- La liste des patients est enregistrée de manière durable.
	- Il est possible d'ajouter un nouveau patient, ce qui nécessite de renseigner ses informations personnelles.
- Consulter et modifier les informations personnelles d'un patient,
	- Les informations personnelles incluent le nom, le prénom, la date de naissance, le genre, l'adresse postale (optionnelle) et le numéro de téléphone (optionnel).
	- Le médecin doit basculer du mode consultatif à un mode modificatif avant toute modification.
	- Il est possible de supprimer le patient en question depuis le mode modificatif (en passant par une étape de confirmation).
- Consulter et modifier des notes prises à propos d'un patient,
	- Les notes sont à modifier ou à ajouter directement sur la page d'affichage des informations du patient.
	- Il n'est pas nécessaire d'être en mode modificatif pour modifier ou ajouter une note.
	- Une note est supprimée si elle est enregistrée avec un texte vide.
	- Les notes du patient sont supprimées si le patient est supprimé de l'application.
- Obtenir une information liée à la détection de mots-clés dans les notes.
	- Les mots-clés sont en lien avec les risques de diabète. La liste a été fourni par le client.
	- En combinant le nombre de mots-clés apparaissant dans les notes et les informations liées à l'âge est au sexe du patient, celui-ci est catégorisé dans un des 4 niveaux de risque défini par le client.
	- Le niveau de risque calculé est affiché en-dessous des notes, sur la page des informations personnelles du patient.
	- Le niveau de risque est affiché avec une police et un code couleur en relation avec le niveau d'alerte.
	- L'information sur le nombre de mots-clés détectés est accessible en survolant avec la souris le niveau de risque, afin que le médecin puisse comprendre la situation si le niveau de risque détecter ne lui parait pas cohérent.
	

## Outils et versions

- Java 17
- Spring boot v3.3.0-SNAPSHOT
- Thymeleaf v 3.1.2.RELEASE
- MySql v8.0
- MongoDB v4.4
- Docker v26.0.0
- Eclipse v4.25.0
- Maven v3.9.5


## Structure du code

Le projet est construit avec une architecture en microservices.


```
medilab-front --> medilab-gateway
                  |
                  +--> api-patients
                  |    |
                  |    +--> BD : mysql
                  |
                  +--> api-notes
                  |    |
                  |    +--> BD : mongodb
                  |
                  +--> api-diabete
                       |
                       +--> medilab-gateway
```

#### `medilab-front`
L'interaction avec les pages *html* est géré par le microservice `medilab-front` à l'aide de la librairie Thymeleaf.
Ce microservice ne gère que le *front* et écoute sur le port 8080.
Afin d'obtenir les informations à afficher, il utilise la librairie Feign pour faire appel au microservice `medilab-gateway`.
Pour l'instant, la sécurité est assurée par Spring Boot Security avec une simple authentification sur la page généré par défaut par Spring Boot Security.
L'identifiant et le mot de passe sont inscrit en dur dans le code.

#### `medilab-gateway`
Le microservice `medilab-gateway` écoute sur le port 8090.
Il utilise la librairie `spring-cloud-starter-gateway-mvc` afin de manipuler les requêtes reçues.
Les requêtes sont modifiées et renvoyées vers les API correspondantes : `api-patients`, `api-notes`, ou `api-diabete`
La sécurité est assurée par une authentification Basic *Stateless* dont l'identifiant et le mot de passe sont connus de `medilab-front` et de `api-diabete`.

#### `api-patients`
Le microservice `api-patients` écoute sur le port 8091.
Il a pour mission de gérer la liste des patients et leurs informations personnelles.
Ces informations sont stockées dans une base de donné MySql.
Le microservice expose les *endpoints* permettant les opérations CRUD, ainsi que deux autres permettant d'obtenir le sexe et la date de naissance d'un patient particulier (dont a besoin `api-diabete`).
La sécurité est assurée par une authentification Basic *Stateless* dont l'identifiant et le mot de passe sont connus de `medilab-gateway`.

Voici comment sont stockées les données dans la base :  
```
Table Patient  
|   Id    |  Last_Name   |  First_Name  | Date_Of_Birth |    Gender     |   Address    |     Tel      |  
|---------|--------------|--------------|---------------|---------------|--------------|--------------|  
| integer | varchar(255) | varchar(255) |     date      | enum('M','F') | varchar(255) | varchar(255) |
```

#### `api-notes`
Le microservice `api-notes` écoute sur le port 8092.
Il a pour mission de gérer la liste des notes prises pour tous les patients.
Les informations sont stockées dans une base NoSql avec MongoDB.
Le microservice expose les *endpoints* permettant les opérations CRUD, 
La sécurité est assurée par une authentification Basic *Stateless* dont l'identifiant et le mot de passe sont connus de `medilab-gateway`.

#### `api-diabete`
Le microservice `api-diabete` écoute sur le port 8093.
Il a pour mission de calculer le niveau de risque d'un patient donné.
Pour ce faire, il accède aux `api-patients` et `api-notes` via `medilab-gateway` afin d'obtenir les informations pertinentes.
La sécurité est assurée par une authentification Basic *Stateless* dont l'identifiant et le mot de passe sont connus de `medilab-gateway`.


## Docker

L'application a été dockerisée.
Pour ce faire, il y a un `Dockerfile` dans chacun des microservices, ainsi qu'un `docker-compose.yml` à la racine du projet.
Chaque microservice a été compilé indépendamment (les fichiers *.jar* se trouve dans les dossiers *target* de chacun).
En plus des 5 services correspondant aux 5 microservices, le `docker-compose.yml` construit un service mysql `db_sql` reposant sur un volume externe `patients_db` et un service mongoDB `db_mongo` reposant sur un volume externe `notes_db` afin de faire persister les données.

Ces 2 volumes externes doivent être initialisés avant le pouvoir lancer le `docker-compose.yml`. 
On peut les créer en utilisant les commande de docker, mais il faut encore initialiser les base de données.  
Dans le projet `api-patients`, sous le dossier *src/main/ressources*, se trouve un dossier *init-db-mysql* contenant des scripts sql ainsi qu'un `Dockerfile` et un `docker-compose.yml`.
En lançant ce fichier `docker-compose.yml`, on va créer la base de données et la remplir avec les données de tests.  
De même, dans le projet `api-notes`, sous le dossier *src/main/ressources*, se trouve un dossier *init-db-mongo* contenant un script javascript ainsi qu'un `Dockerfile` et un `docker-compose.yml`.
En lançant ce fichier `docker-compose.yml`, on va créer la base de données et la remplir avec les données de tests.  


## Perspectives d'évolution

### Amélioration du design

Le design actuel des pages html est extrêmement sobre.
La présentation pourra être repensé, avec par exemple, la présence du logo de l'application en haut de page, et des mentions légales en bas de page.  
La page de connexion est actuellement la page par défaut de Spring Boot Security, mais il peut être envisagé de la remplacer par une page customisée.


### Sécurisation de l'accès

Actuellement, la connexion à l'application (au *front*) se fait par un nom d'utilisateur et mot de passe unique, codé en dur dans le code (et avec un mot de passe particulièrement facile à deviner).
Pour une réelle application, il faudra modifier ça.
On pourra mettre en place une authentification avec une liste d'utilisateurs/mots de passe enregistrés dans une base de données par exemple, et contraindre l'utilisation de mots de passe complexe.

Les autres microservices sont accessibles sans passer par le *front* (par exemple avec Postman).
Il faut donc qu'ils soient sécurisées.
Actuellement, ils sont protégés par une authentification *stateless* Basic, qui utilise un unique nom d'utilisateur et mot de passe, renseignés en dur dans le code.
Il pourrait être intéressant de remplacer cela par une authentification par token ou par un protocole RSA.


### Green code

Le *green code*, ou la programmation écologique ou éco-responsable, consiste à prendre en compte l'impact environnemental de son application.
Idéalement, une application ne doit pas consommer trop de CPU (de puissance de calcul), elle ne doit pas utiliser ou réserver trop de place mémoire (valable pour la mémoire vive mais aussi pour la mémoire disque), elle doit limiter ses accès disque, et elle doit préserver au maximum la bande passante en réduisant ses communications internet.

Pour notre application, voici quelques axes d'amélioration ou de vigilance :
- Pour les pages html, si on souhaite les développer davantage, on prendra soin de ne pas les surcharger d'images inutiles.
Les images en question devront être compressées côté serveur conformément à leur résolution d'affichage (il ne faut pas envoyer une image en ultra-haute définition tout en demandant au navigateur du client de l'afficher en petit).
- Actuellement, le procédé de confirmation qu'on a implémenté lorsque l'utilisateur veut supprimer un patient se fait côté serveur avec Thymeleaf.
Cela nécessite de renvoyer l'intégralité de la page html à chaque fois.
Une amélioration possible serait de mettre cette confirmation côté client avec du javascript par exemple.
- Dans notre application, il y a des *endpoints* et des fonctionnalités qui ne sont pas utilisées. 
Par exemple, l'API *diabete* peut découper une note (la chaîne de caractères) afin d'en faire ressortir les mots-clés détectés.
Ces fonctions non-utilisé prennent tout de même une place inutile dans le code compilé (le fichier *.jar*) et dans les images Docker qui en résultent.
















