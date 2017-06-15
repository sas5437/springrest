## Build
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

