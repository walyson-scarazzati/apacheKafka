# Apache Kafka - Spring Boot Microservices

Study project from the course [Apache Kafka - Valdir Cezar](https://www.udemy.com/course/apache-kafka-valdir) on Udemy.

This project demonstrates the implementation of Spring Boot microservices using Apache Kafka for asynchronous communication between services.

## 📋 Description

The project consists of 4 main microservices:

- **payment-server**: Payment message producer (JSON)
- **json-consumer**: JSON message consumer
- **str-producer**: String message producer
- **str-consumer**: String message consumer

## 🏗️ Architecture

```
┌─────────────────┐         ┌──────────────┐         ┌─────────────────┐
│ payment-server  │────────>│    Kafka     │────────>│  json-consumer  │
│   (Producer)    │         │   Broker     │         │   (Consumer)    │
└─────────────────┘         └──────────────┘         └─────────────────┘
                                    │
                            ┌───────┴────────┐
                            │    Kafdrop     │
                            │  (UI Monitor)  │
                            └────────────────┘
```

## 🛠️ Technologies Used

- Java 17
- Spring Boot 2.6.3
- Spring Kafka
- Apache Kafka 7.4.1
- Docker & Docker Compose
- Kafdrop (monitoring UI)
- Lombok
- Maven

## 📦 Prerequisites

Before starting, make sure you have installed:

- [Docker](https://docs.docker.com/get-docker/) (version 20.10 or higher)
- [Docker Compose](https://docs.docker.com/compose/install/) (version 1.29 or higher)
- [Java JDK 17](https://adoptium.net/) or higher
- [Maven 3.6+](https://maven.apache.org/download.cgi) (or use the included mvnw wrapper)

To verify installations:
```bash
docker --version
docker-compose --version
java -version
mvn --version
```

## 🚀 How to Run

### Option 1: Using Docker Compose (Recommended)

This option runs the entire environment (Kafka, Zookeeper, Kafdrop, and microservices) in containers:

```bash
docker-compose up -d
```

To check the logs:
```bash
docker-compose logs -f
```

To stop the services:
```bash
docker-compose down
```

### Option 2: Run Locally

#### 1. Start the infrastructure (Kafka + Zookeeper + Kafdrop)

```bash
docker-compose up -d kafka zookeeper kafdrop
```

#### 2. Build the projects

**On Windows:**
```cmd
:: Payment Server
cd payment-server
mvnw clean package -DskipTests
cd ..

:: JSON Consumer
cd json-consumer
mvnw clean package -DskipTests
cd ..

:: String Producer
cd str-producer
mvn clean package -DskipTests
cd ..

:: String Consumer
cd str-consumer
mvnw clean package -DskipTests
cd ..
```

**On Linux/Mac:**
```bash
# Payment Server
cd payment-server
./mvnw clean package -DskipTests
cd ..

# JSON Consumer
cd json-consumer
./mvnw clean package -DskipTests
cd ..

# String Producer
cd str-producer
mvn clean package -DskipTests
cd ..

# String Consumer
cd str-consumer
./mvnw clean package -DskipTests
cd ..
```

#### 3. Run the microservices

Open 4 different terminals and execute:

**Terminal 1 - Payment Server:**
```bash
cd payment-server
./mvnw spring-boot:run    # Linux/Mac
mvnw spring-boot:run      # Windows
```

**Terminal 2 - JSON Consumer:**
```bash
cd json-consumer
./mvnw spring-boot:run    # Linux/Mac
mvnw spring-boot:run      # Windows
```

**Terminal 3 - String Producer:**
```bash
cd str-producer
mvn spring-boot:run
```

**Terminal 4 - String Consumer:**
```bash
cd str-consumer
./mvnw spring-boot:run    # Linux/Mac
mvnw spring-boot:run      # Windows
```

## 🔗 Endpoints and Ports

| Service | Port | URL |
|---------|------|-----|
| Payment Server | 8000 | http://localhost:8000 |
| Kafka Broker | 9092 | localhost:9092 |
| Kafdrop (UI) | 19000 | http://localhost:19000 |
| Zookeeper | 2181 | localhost:2181 |

## 📡 Testing the Application

### 1. Access Kafdrop

Open your browser and go to: http://localhost:19000

You will be able to view:
- Created topics
- Messages
- Consumers
- Kafka configurations

### 2. Send payment messages

Using payment-server, you can send messages via REST API (refer to the source code for specific endpoints).

Example using curl:
```bash
curl -X POST http://localhost:8000/payments \
  -H "Content-Type: application/json" \
  -d '{"id":1,"value":100.50,"description":"Test payment"}'
```

### 3. Check the consumers

Observe consumer logs in the terminals or in Docker:

```bash
docker-compose logs -f json-consumer
docker-compose logs -f consumer
```

## 📁 Project Structure

```
kafka/
├── docker-compose.yml          # Container orchestration
├── payment-server/             # Payment producer
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── json-consumer/              # JSON consumer
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── str-producer/               # String producer
│   ├── src/
│   └── pom.xml
└── str-consumer/               # String consumer
    ├── src/
    └── pom.xml
```

## 🐛 Troubleshooting

### Kafka won't start

Make sure ports 9092, 2181, and 19000 are not in use:

**Windows:**
```cmd
netstat -ano | findstr :9092
netstat -ano | findstr :2181
netstat -ano | findstr :19000
```

**Linux/Mac:**
```bash
lsof -i :9092
lsof -i :2181
lsof -i :19000
```

### Kafka connection error

Wait a few seconds after starting docker-compose. Kafka needs time to fully initialize.

### Clear all Kafka data

```bash
docker-compose down -v
docker-compose up -d
```

### Rebuild Docker images

```bash
docker-compose build --no-cache
docker-compose up -d
```

## 📚 Concepts Learned

- **Producers**: Sending messages to Kafka topics
- **Consumers**: Consuming messages from Kafka topics
- **Topics**: Message organization
- **Partitions**: Distribution and parallelism
- **Consumer Groups**: Consumer scalability
- **Serialization/Deserialization**: JSON and String
- **Spring Kafka**: Integration with Spring Boot

## 🎓 Additional Resources

- [Udemy Course - Apache Kafka](https://www.udemy.com/course/apache-kafka-valdir)
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
- [Kafdrop](https://github.com/obsidiandynamics/kafdrop)

## 📝 Notes

- Docker images for the services are already published on Docker Hub: `walysonsilva/payment-service:1.0.1` and `walysonsilva/json-consumer:1.0.1`
- For local development, make sure `KAFKA_HOST` points to `localhost:9092`
- The project uses Spring Boot 2.6.3 and Java 17

## 👨‍💻 Author

Project developed during the Apache Kafka course taught by Valdir Cezar on Udemy.

## 📄 License

This project is intended for educational purposes.
