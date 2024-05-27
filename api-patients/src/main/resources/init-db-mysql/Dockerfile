
# Dockerfile pour init-db-mysql
# Destiné à être lancé 1 seule fois via son docker-compose pour initialiser le volume avec la base de donnée

# from mysql
FROM mysql:8.0

# Copier les scripts SQL dans le répertoire d'initialisation de MySQL
COPY script-init /docker-entrypoint-initdb.d
# MySQL exécute automatiquement les scripts placés dans ce répertoire lors du premier démarrage du conteneur.

# Exposer le port MySQL
EXPOSE 3306
