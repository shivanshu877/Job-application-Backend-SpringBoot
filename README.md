
# Job Application Backend System

Welcome to the Job Application Backend System repository! This project showcases a scalable backend architecture for managing job applications, built using Spring Boot microservices and various supporting technologies.

## Overview

This project implements a microservices architecture using Spring Boot and includes several key components:
- **Service Discovery:** Eureka server for service registration and discovery.
- **API Gateway:** Gateway service (localhost:8084) for routing and securing requests to job, company, and review services.
- **Resilience Patterns:** Utilizes Resilience4j for implementing circuit breakers, rate limiting, and distributed tracing with Zipkin.
- **Database Management:** PostgreSQL for persistent storage of job and company data.
- **Containerization:** Docker for packaging services into containers.
- **Orchestration:** Kubernetes for automating deployment, scaling, and management of containerized applications.
- **Messaging Queue:** RabbitMQ for asynchronous communication between services.

## Running the Application

To run the entire system locally, follow these steps:

1. **Clone the Repository:**
   ```
   git clone https://github.com/your-username/job-application-backend.git
   ```
2. **Get Api Collection:**
   
   [<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/20448680-a3776866-fd74-4b60-aaee-d6774847c580?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D20448680-a3776866-fd74-4b60-aaee-d6774847c580%26entityType%3Dcollection%26workspaceId%3Df1fba814-8e97-45b5-a81a-d5380c559ec1)
4. **Open Projects in IntelliJ IDEA:**
Open each microservice project (job-service, company-service, review-service) in IntelliJ IDEA.

5. **Start Each Service:**
- Run Eureka server:
  ```
  cd eureka-server
  mvn spring-boot:run
  ```

- Run job service, company service, and review service in separate terminals or IntelliJ instances:
  ```
  cd job-service
  mvn spring-boot:run
  ```
  ```
  cd company-service
  mvn spring-boot:run
  ```
  ```
  cd review-service
  mvn spring-boot:run
  ```

- Run API Gateway service:
  ```
  cd api-gateway
  mvn spring-boot:run
  ```
  Access all services through Gateway at: [http://localhost:8084](http://localhost:8084)

5. **Verify Services:**
Confirm that all services are registered with Eureka by visiting [http://localhost:8000](http://localhost:8000).

## Usage

- **Endpoints:**
- jobs: Provides endpoints for managing job applications.
- companies: Manages company-related information.
- reviews: Handles job reviews and feedback.

- **Gateway:** Use [http://localhost:8084](http://localhost:8084) to access and test all services through a single entry point.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your enhancements.

