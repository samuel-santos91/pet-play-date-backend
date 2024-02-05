# pet-play-date-backend
## (APPLICATION IN PROGRESS)

## Description
* This Spring Boot backend application serves as the backend for the pet-play-date frontend. 
* It provides RESTful APIs to manage the pets' information and messages between users.
### Purpose
* The purpose of this project was to:
    * practice building a backend with Spring Boot
    * integrate a server with a frontend
    * build an authentication security system to log in
    * integrate a database with a server
    * practice building the relationship between tables
### Tech Stack
* Spring Boot
* MySQL (database)

## Features 
 - RESTful API to manage the pets' information and messages among users (CRUD operations);
 - Sign/log in authentication system with JWT(Json Web Token);
 - Database integration (<strong>MySQL</strong>) for persistent data storage;
 - Error handling.

## Database Table Structure
<img width="500" alt="pet-play-date database" src="https://github.com/samuel-santos91/pet-play-date-backend/assets/107240729/7f421d15-7daa-4df3-9151-2012b4f3ebf7">

## Getting Started
### Prerequisites
 - Java Development Kit (JDK) 8 or higher installed;
 - Eclipse IDE or any Java development environment;
 - MySQL database.

### Installation
 - Clone this repository;
 - Import project into your IDE;
 - Configure the database connection application.properties;
 - Configure cors in WebConfig.java;
 - Run the application.

### Frontend repository
Refer to https://github.com/samuel-santos91/pet-play-date

### API Endpoints
#### Auth
 - POST /url/auth/register: Register a user.
 - POST /url/auth.login: Log into the app.

#### User
 - GET /url/users: Retrieve all users.
 - GET /url/users/{userID}/pet: Checks if current user is the pet owner. Returns a boolean.
 - POST /url/users/{userID}/pet/like: Method used to like a pet.

#### Pet
 - POST /url/pet: Creates a new pet.

## Future Goals
 - Complete the relationship logic between tables
 - Create methods to send and receive messages between users
   

