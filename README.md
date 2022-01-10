# Reading is good application

**ReadingIsGood** is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock **consistency** is the first priority for their
vision operations.

Since consistency is the priority, it's better to go with a relation DB than a NoSQL one. 
In this project, I used Spring Boot as a framework for backend and PostgreSQL as a DB. 

### DB diagram
![img.png](img.png)
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#using-boot-devtools)

I tried to add unit tests and integration tests in the project (Junit). 

## Guidelines
Since this application should be containerized, I used docker for it. 
1. In case you don't have docker please follow this [link](https://docs.docker.com/get-docker/) to install it.

2. clone the repo
```sh
git clone https://github.com/nesrinesghaier/ReadingIsGood.git
```

3. build the project
```maven
mvn clean package -Dmaven.test.skip=true
```
4. run docker-compose-dev file to start the db service alongside the java app or run the docker-compose-test file to run the whole application 
```
docker-compose -f docker-compose-dev.yml up -d
```

[Link](https://galactic-station-824484.postman.co/workspace/Team-Workspace~0ddfccd2-fc24-4c62-8c05-3b935d830cad/collection/4959808-46289f14-bea5-44e3-997c-ae410b36b37f) to postman collection: 
