# --- 1. Aşama: Jar'dan katmanları çıkar ---
FROM eclipse-temurin:17-jdk-alpine AS builder
ARG JAR_FILE=target/JavaNewsRss-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
# Spring Boot 2.3+ için layertools
RUN java -Djarmode=layertools -jar app.jar extract

# --- 2. Aşama: Sadece runtime için hafif imaj ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Katmanları ayrı ayrı kopyalayalım ki değişmeyenler cache’lensin
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder application/ ./
COPY --from=builder spring-boot-loader/ ./

EXPOSE 8080
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
