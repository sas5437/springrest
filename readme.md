## System Requirements
1. JDK 8 (build and tested on 1.8.0_121-b13)  
2. MySQL database with a dbuser granted appropriate permissions depending on development or non-development environment  
3. Gradle (on macOS run `which gradle || brew install gradle`)  
## Build
First create a file for database connection settings (substitute `localhost/database`, `dbuser`, and `dbpass` on the last 3 lines as necessary).  
```
$ cat << EOF > src/main/resources/application.properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/database
spring.datasource.username=dbuser
spring.datasource.password=dbpass
EOF
```
Then build the project and JAR.
```
$ gradle build  
$ gradle jar  
$ gradle bootRepackage  
```
## Run
For development:  
```
$ gradle build && gradle bootRun  
```
For distribution:  
```
$ java -jar /build/libs/gs-rest-service0.1.0.jar
```
## Test
With the tomcat server running on port 8080 (default), `curl` the `/users` path with HTTP verbs `GET` or `POST`
### List users
```
$ curl localhost:8080/users
[
  {
    "id": 4,
    "email": "user1@email.com"
  },
  {
    "id": 5,
    "email": "scott@gmail6.com"
  },
  {
    "id": 6,
    "email": "s7awef@gmaaail.com"
  }
]
```
### Create user
```
$ curl -X POST -H "Content-Type: application/json" -d '{"email":"jake@gmailer.com"}' localhost:8080/users
{
  "id": 7,
  "email": "jake@gmailer.com"
}
```

