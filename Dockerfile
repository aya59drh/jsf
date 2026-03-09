# Étape 1 : Build du projet avec Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copier les fichiers du projet
COPY pom.xml .
COPY src ./src

# Compiler et générer le WAR
RUN mvn clean package -DskipTests


# Étape 2 : Exécuter l'application avec Tomcat
FROM tomcat:10.1-jdk17

# Supprimer les apps par défaut
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier le WAR généré dans Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port
EXPOSE 8080

# Lancer Tomcat
CMD ["catalina.sh", "run"]
