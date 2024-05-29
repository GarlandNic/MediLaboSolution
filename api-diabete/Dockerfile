
# Dockerfile pour api-diabete

# Utiliser une image de base OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Créer un répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré dans l'image Docker
COPY target/api-diabete-0.1.0-SNAPSHOT.jar /app/api-diabete.jar

# Exposer le port sur lequel l'application écoute (par défaut pour Spring Boot : 8080)
EXPOSE 8093

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/api-diabete.jar"]
