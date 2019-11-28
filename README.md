# NetCracker Training Project of Group 1 - Autumn 2019

[![MIT License](https://img.shields.io/pypi/l/aiogram.svg?style=flat-square)](https://opensource.org/licenses/MIT)
<br>
This system helps user to decide what to read next,
see friends, what friends read and so on.

You can find the project website [here](https://nc-group1-2019-project.herokuapp.com/).
## Stack

- [Spring Framework](https://spring.io/)
- [Angular](https://angular.io/)
- [PostgreSQL](https://www.postgresql.org/)

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)
- [NodeJS](https://nodejs.org)
- [NPM](https://www.npmjs.com/)

To build the project simply run this command in the root directory: 
```shell
mvn clean install 
```
## Running the application locally

This project is a Maven multimodule project with two submodules: 
 - backend
 - frontend

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method in the `Application` class from your IDE 
and go to `http://localhost:8080`. 

Alternatively you can use the 
[Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

To run Angular application on your local machine 
you can go to the Angular root directory and run:

```shell
ng serve --open
```
This will automatically open `http://localhost:4200` in your browser.

## Developers

- Aleksey Gritsenko
- Rostislav Koval
- Julia Alpaeva
- Kirill Kandel
- Diana Teper
- Dmitriy Skorohodov
- Artem Tarasenko

## Deploy

For application deployment we are using free cloud application platform [Heroku](https://dashboard.heroku.com).
