
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app


COPY . .


RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test


CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar build/libs/*.jar"]