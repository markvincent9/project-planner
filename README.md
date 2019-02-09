# Project Planner

Project Planner is a Spring Boot Application for schuduling task for a Project.
A Project can have one or more task and a Task can have zero or more dependencies(task).
If a task depends on some other task. It can only be started after dependency hsa been completed 

#### Tech Stack

* Java 1.8
* Spring Boot
* PostgreSQL 11

#### Prerequisite

1. Install Docker

    https://docs.docker.com/install/
    
2. Install Docker compose

    https://docs.docker.com/compose/install/
    
#### How to run

1. Go Project Directory

2. Build and Start the containers

    Run: `docker-compose up`

#### ERD

<p align="left">
  <img src="https://i.imgur.com/xD1yign.png"
      title="Project Planner ERD" width="600" height="350">
<p>

##### Task Statuses

OPEN        - Task is newly created
INPROGRESS  - Task has been started
COMPLETE    - Task has been completed

#### How it works

1. Project and Task can be created via REST api.

2. A Scheduler Service automatically start open task if current date is >= Start Date of task
and automatically completes task if currend date >= End Date of task.

3. Task with dependencies will start after all of its subtask have status = COMPLETE

#### Endpoints

1. List all Projects

    `GET http://localhost:8080/projects`
    
    ##### Sample Request:
    
    ```json
    [
      {
        "id": 1,
        "name": "First Project",
        "tasks": [
          {
            "name": "Dependent Task 1",
            "startAt": "2019-02-05T09:52:32.704+0000",
            "endAt": "2019-02-06T09:52:32.704+0000",
            "status": "COMPLETE",
            "dependentTasks": [
                {
                  "name": "Dependent Task 1",
                  "startAt": "2019-02-05T09:52:32.704+0000",
                  "endAt": "2019-02-06T09:52:32.704+0000",
                  "status": "COMPLETE",
                  "dependentTasks": []
                }
            ]
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
      "tasks": [
        {
          "name": "Dependent Task 1",
          "startAt": "2019-02-05T09:52:32.704+0000",
          "endAt": "2019-02-06T09:52:32.704+0000",
          "dependentTasks": [
              {
                "name": "Dependent Task 1",
                "startAt": "2019-02-05T09:52:32.704+0000",
                "endAt": "2019-02-06T09:52:32.704+0000",
                "dependentTasks": []
              }
          ]
        }
      ]
    }
    ```
    
    ##### Sample Response:
    
    ```json
    {
      "id": 1,
      "name": "First Project",
      "tasks": [
        {
          "id": 1,
          "name": "Task 1",
          "startAt": "2019-02-09T09:52:32.704+0000",
          "endAt": "2019-02-10T09:52:32.704+0000",
          "status": "COMPLETE",
          "dependentTasks": [
              {
                "id": 2,
                "name": "Dependent Task 1",
                "startAt": "2019-02-05T09:52:32.704+0000",
                "endAt": "2019-02-06T09:52:32.704+0000",
                "status": "COMPLETE",
                "dependentTasks": []
              }
          ]
        }
      ]
    }
    ```
    
3. Add Task to Project

    `POST http://localhost:8080/tasks?projectId={projectId}`
    
    ##### Sample Request:

    ```json
    {
      "name": "New Task",
      "startAt": "2019-02-06T09:52:32.704+0000",
      "endAt": "2019-02-07T09:52:32.704+0000",
      "dependentTasks": [
          {
            "name": "Dependent Task",
            "startAt": "2019-02-05T09:52:32.704+0000",
            "endAt": "2019-02-06T09:52:32.704+0000",
            "dependentTasks": []
          }
      ]
    }
    ```