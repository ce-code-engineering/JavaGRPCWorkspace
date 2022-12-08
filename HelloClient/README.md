# Build docker
1. `mvn clean install spring-boot:repackage`
2. `docker build -t codeengineering/hello-client:latest .`

# Run locally build docker
1. `docker run -p 8081:8081 codeengineering/hello-client:latest`

# Run locally
1. `mvn compile exec:java -Dexec.mainClass="com.codeengineering.consumer.HelloClientApplication"`
2. `mvn spring-boot:run`