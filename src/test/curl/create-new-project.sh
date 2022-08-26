#!/bin/sh

curl -X POST \
  http://localhost:8080/projects \
  -H 'Content-Type: application/json' \
  -d '{
      "name": "New Project new 2",
      "tasks": [
        {
          "name": "Task 1",
            "startAt": "2019-02-05T09:52:32.704+0000",
            "endAt": "2019-02-06T09:52:32.704+0000",
            "dependentTasks": [
              {
                "name": "Subtask 2",
                "startAt": "2019-02-05T09:52:32.704+0000",
                "endAt": "2019-02-06T09:52:32.704+0000",
                "dependentTasks": [
                  {
                    "name": "Subtask 3",
                    "startAt": "2019-02-05T09:52:32.704+0000",
                    "endAt": "2019-02-06T09:52:32.704+0000"
                  }
                ]
              }
            ]
        }
      ]
}' | json_pp