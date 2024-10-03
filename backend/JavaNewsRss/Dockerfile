# 1. Adım: OpenJDK'nın hafif bir imajını kullan
FROM openjdk:17-jdk-alpine

# 2. Adım: Uygulama JAR dosyasını imajın içine kopyala
ARG JAR_FILE=target/JavaNewsRss-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 3. Adım: Uygulamanın çalıştırılacağı komutu belirt
ENTRYPOINT ["java", "-jar", "/app.jar"]
