FROM eclipse-temurin:17-jre
WORKDIR /app
COPY backend/deploy-railway/target/projeto-railway-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
