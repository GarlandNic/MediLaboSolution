
# Dockerfile pour api-patients

# Utiliser une image de base OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Créer un répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré dans l'image Docker
COPY target/api-patients-0.1.0-SNAPSHOT.jar /app/api-patients.jar

# Exposer le port sur lequel l'application écoute (par défaut pour Spring Boot : 8080)
EXPOSE 8091

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/api-patients.jar"]
