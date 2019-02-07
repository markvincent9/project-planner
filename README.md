# Project Planner

SpringBoot application for Generating schedule for a Project

#### Tech Stack

* Java 1.8
* Spring Boot
* PostgreSQL 11

#### Prerequisite

1. Install Docker

    https://docs.docker.com/install/
    
2. Install Docker compose

    https://docs.docker.com/compose/install/
    
3. Install Maven

    https://maven.apache.org/install.html
    
#### How to run

1. Go Project Directory

2. Build the JAR File
    
    Run: `mvn package`
    
3. Build and Start the containers

    Run: `docker-compose up`
    
#### Endpoints

1. List all Projects

    `GET http://localhost:8080/projects`
    
    ##### Sample Request:
    
    ```json
    [
      {
        "id": 1,
        "name": "First Project",
        "tasks": [{
            "name": "Dependent Task 1",
            "startAt": "2019-02-05T09:52:32.704+0000",
            "endAt": "2019-02-06T09:52:32.704+0000",
            "status": "COMPLETE",
            "dependentTasks": []
          },
          {
            "name": "Dependent Task 2",
            "startAt": "2019-02-05T09:52:32.704+0000",
            "endAt": "2019-02-06T09:52:32.704+0000",
            "status": "COMPLETE",
            "dependentTasks": []
          }
        ]
      },
      {
        "id": 2,
        "name": "Second Project",
        "tasks": []
      }
    ]
    ```
2. Create new Project

    `POST http://localhost:8080/projects`
    
    ##### Sample Request:
    
    ```json
    {
      "name": "First Project",
      "tasks": []
    }
    ```
    
    ##### Sample Response:
    
    ```json
    {
      "id": 1,
      "name": "First Project",
      "tasks": []
    }
    ```
    
3. Add Task to Project

    `POST http://localhost:8080/tasks?projectId={projectId}`
    
    ##### Sample Request:
        