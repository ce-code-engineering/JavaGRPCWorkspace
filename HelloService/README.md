# Build docker
1. `mvn clean install spring-boot:repackage`
2. `docker build -t codeengineering/hello-app:latest .`

# Run locally build docker
1. `docker run -p 8080:8080 codeengineering/hello-app:latest`

# Run locally
1. `mvn compile exec:java -Dexec.mainClass="com.codeengineering.hello.HelloServiceApplication"`
2. `mvn spring-boot:run`