# Phase 1: Build
FROM openjdk:22-slim AS build
WORKDIR /app

# Install maven
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://apache.osuosl.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz | tar xz -C /opt && \
    ln -s /opt/apache-maven-3.8.8/bin/mvn /usr/bin/mvn && \
    rm -rf /var/lib/apt/lists/*

# Copy project files from current directory
COPY ../pom.xml ./
COPY ../src ./src

# Build project and generates .jar
RUN mvn clean package

# Phase 2: Final image
FROM openjdk:22-slim
WORKDIR /app

# Copy 'wait-for-it.sh' to final container
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# Copy .jar file from build phase
COPY --from=build /app/target/spaceship-service.jar /app/spaceship-service.jar

# Execute app
CMD ["/app/wait-for-it.sh", "database:5432", "--", "java", "-jar", "/app/spaceship-service.jar"]