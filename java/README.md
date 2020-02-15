
# Cheese Shop
The PZ Cheeseria
PZ being lovers of fine cheese are looking to open up our own cheeseria. Through our extremely capable and magnificent sourcing abilities, we have uncovered our initial 5 cheeses we are looking to sell. As an MVP of our cheeseria, we are looking to display our cheese selection that can be purchased in-store.
 
Task: Build a small POC of the front-end and an API showing crud capabilities that displays 5 different cheeses with pictures, price per kilo and the cheese color. This data should reside within the backend (API) of the app. 
As an optional addition, we would like to offer a calculator that allows our customers to select a particular type of cheese and show them the total price based on the weight they're interested in.

Deliverable: Application source code and Readme in a github repo. You are allowed to add comments in the code to describe what you would do if you had more time (i.e what other persistence mechanism you'd use etc.).
We'd also like to see how you would structure a build pipeline using a Docker multi-stage build.

Here's a link to get you started: https://cheese.com/


We'd expect your source code to include:
- Angular or React (front end)
- NodeJS or .Net Core (back end)
- Swagger / Open API
- Unit Tests
- Dockerfile


## Docker
To build the project into a docker image, run
```
docker build -t cheese-poc .
```

When the image is finished building, run
`docker run -it --rm -p 8080:8080 cheese-poc`

and the UI is at http://localhost:8080

## Swagger API
The swagger API definition is located at http://localhost:8080/swagger-ui

## Architecture
 - backend: Java + Micronaut
 - frontend: React + Redux
 - database: H2 in memory database by default
 - JPA persistence layer
 - Swagger API definition is generated at the compilation time using annotation processor thanks to Java compiler. No human / hard coded to the yml. This ensures that the documentation is always up to date with the interface.
 - the frontend and backend code are all built via Gradle plugins with ease.
## Run Locally
- server: `./gradlew run`
- frontend: `./gradlew start`

## Consideration for proof of concept
- the frontend and backend are bundled together, however they can be easily separated and deployed onto different stacks.
- in-memory database is used to speed up and simplify the development cycle.
- the test cases are written at bare minimum to test the core functionality only. 
- the code has no deep consideration around data validation and exception handling. 
- no CSS or UX consideration. It is written at 'it just works' level.
- no security / auth check.
- min. API documentation and description. However the additional information can be easily done by adding the annotation (io.swagger.v3.oas.annotations) to the relevant method on the controller.
 - `au.id.kang.cheese.Initialiser` class holds the base data and it is loaded to the database when the server starts up. 
 - More data can be added via the API. Refer to `http://localhost:8080/swagger-ui`. The changes will not survive the server restarts.
 

## Future Consideration
- The HATEOAS Driven REST APIs implementation is not completed. Request and Response DTOs have the HAL place holders however the data population is not completed. 
- The project is written to take advantage of DSL + Code generation in mind. New product line can be easily added and enhance the functionality of the application.  For example, Car.
