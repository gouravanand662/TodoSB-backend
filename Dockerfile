# ===============================
# 🧱 BUILD STAGE
# ===============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests

# ===============================
# 🚀 RUNTIME STAGE
# ===============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/TodoJWT-0.0.1-SNAPSHOT

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
