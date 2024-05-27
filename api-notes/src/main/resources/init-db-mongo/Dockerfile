
# Dockerfile pour init-db-mongo
# Destiné à être lancé 1 seule fois via son docker-compose pour initialiser le volume avec la base de donnée

# from mongo
FROM mongo:4.4

# Copier les scripts SQL dans le répertoire d'initialisation de MySQL
COPY script-init /docker-entrypoint-initdb.d
# Docker exécute automatiquement les scripts placés dans ce répertoire lors du premier démarrage du conteneur.

# Exposer le port MongoDB
EXPOSE 27017
