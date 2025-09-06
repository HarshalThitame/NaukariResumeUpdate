FROM maven:3.9.6-eclipse-temurin-17

# Essentials
RUN apt-get update && apt-get install -y wget gnupg ca-certificates curl unzip && rm -rf /var/lib/apt/lists/*

# Microsoft Edge (Linux)
RUN curl -fsSL https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor -o /usr/share/keyrings/microsoft.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/microsoft.gpg] https://packages.microsoft.com/repos/edge stable main" \
    > /etc/apt/sources.list.d/microsoft-edge.list && \
    apt-get update && apt-get install -y microsoft-edge-stable && rm -rf /var/lib/apt/lists/*

# App
WORKDIR /app
COPY . .

# Build once (tests later)
RUN mvn -q -e -DskipTests clean install

# Cache for WebDriverManager (optional)
ENV WDM_CACHE=/root/.cache/webdriver

# Default command runs tests in Edge headless
CMD ["mvn","-q","test","-Dbrowser=edge","-Dheadless=true"]
