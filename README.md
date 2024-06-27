<h1>Project Description:</h1>
The project is a simple CRUD (Create, Read, Update, Delete) application for managing multiple-choice question tests. It utilizes Spring Boot with Spring Data JPA for database operations and Mockito for unit testing.
<br> <h3>Controller Layer:</h3>
<br>
Receives HTTP requests from clients and routes them to appropriate methods.
Methods include:
POST /api/questions: saveQuestions(): Creates a new question.
GET /api/questions: getAllQuestions(): Retrieves all questions.
GET /api/questions/{id}: getQuestionById(): Retrieves a specific question by ID.
DELETE /api/questions/{id}: deleteQuestion(): Deletes a specific question.
PUT /api/questions/{id}: updateQuestion(): Updates a specific question.
<br><h3>Service Layer:</h3>

Contains business logic for managing multiple-choice questions.
Methods include:
saveQuestions(MultipleChoiceQuestionTest question): Saves a new question.
getAllQuestion(): Retrieves all questions.
getQuestionById(Long id): Retrieves a specific question by ID.
deleteQuestion(Long id): Deletes a specific question.
updateQuestion(Long id, String question, String optionOne, String optionTwo, String optionThree, String optionFour, String correctOption, String category): Updates a specific question.
<br><h3>Repository Layer:</h3>

Interfaces with the database using Spring Data JPA repositories.
Implements standard CRUD operations for MultipleChoiceQuestionTest entities.
<br><h3>Model Layer:</h3>

Defines the structure of MultipleChoiceQuestionTest entities using JPA annotations.
Represents attributes such as question, options, correct answer, category, etc.
Database Connectivity:

Utilizes Spring Data JPA and configuration properties (application.properties) for database connection.


<h3>Testing:</h3>

Unit tests are implemented using JUnit 5 and Mockito.
Tests cover service layer methods (saveQuestions(), getAllQuestion(), getQuestionById(), deleteQuestion(), updateQuestion()).

<h1>Build Steps</h1>
  <br>1) Ensure you have Java Development Kit (JDK) 8 or higher installed.
  <br>2) Clone the repository
  <br>3) Navigate to the project root directory.
  <br>4) Build the project using Maven.
<h1>Execution Steps</h1>
  <br>1) After building the project, ensure your database is set up and configured in application.properties.
  <br>2) Start the Spring Boot application.
  <br>3) The application will start, and you can access the endpoints using tools like Postman:
  
          POST /api/questions: Create a new question.
          GET /api/questions: Retrieve all questions.
          GET /api/questions/{id}: Retrieve a specific question by ID.
          PUT /api/questions/{id}: Update a specific question by ID.
          DELETE /api/questions/{id}: Delete a specific question by ID.
