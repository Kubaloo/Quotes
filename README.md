# Quotes

Application that allows you to store, add, edit and delete quotes. There are simple CRUD endpoints that allows to do this.
A quote is an object that has attributes such as the name and surname of the author and the content of the quote.

Made in Java11, using H2 memory database to store quotes, JPA to connect with db and Maven to manage and organize dependencies.
Tests made with JUnit, are checking if endpoints works correctly.
Also using lombok to eliminate some boilerplate code.

URL for tests in postman after starting the app: http://localhost:8080/api/quotes

How to start a project:
1. Go to the project directory in console
2. Run mvn spring-boot:run

Endpoints:

1. GET - Show all quotes
http://localhost:8080/api/quotes

2. GET - Find Quote by Id - Get quote by specific ID added as a parametr
http://localhost:8080/api/quotes/{QuoteId}

3. POST - Add quote to the db. In body - firstName, lastName and content have to be specified
http://localhost:8080/api/quotes

body example:
{
    "authorFirstName" : "Test",
    "authorLastName" : "test",
    "content" : "content"
}

4. PUT - Update an existing quote by specified Id added in parameter. In request body all parameters have to be specified.
http://localhost:8080/api/quotes/{QuoteId}

body example:
{
    "authorFirstName" : "Test",
    "authorLastName" : "test",
    "content" : "content"
}

5. DELETE - Delete an existing quote by specified Id added in parameter
http://localhost:8080/api/quotes/{QuoteId}

