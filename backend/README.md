# FinBit Backend (Spring Boot + H2)

- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:file:./data/finbit-db`, User: `sa`, no password)
- REST Base: http://localhost:8080/api

Endpoints:
- POST /api/auth/signup
- POST /api/auth/login
- GET  /api/friends
- POST /api/friends
- DELETE /api/friends/{id}
- GET  /api/bills
- POST /api/bills

Quick run:
1) Install JDK 17+
2) In `backend/`, run: `./mvnw spring-boot:run` (or `mvn spring-boot:run` if Maven is installed)
