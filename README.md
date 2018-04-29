
## Overview
This program is a deep web crawler to go upto provided depth with maximum limit. Deep crawling service is exposed as REST endpoint and returns Completable Future

## Implementation
The solution is implemented as a Spring Boot / maven project.

## Building the app
Execute ```mvn spring-boot:run``` from the root of the project, then use the following URL

http://localhost:8080/api/v1/crawler?url=<pageUrl>&depth=<depthValue>

Ex url : ```http://localhost:8080/api/v1/crawler?url=https://start.spring.io/&depth=2```

swagger URL : ```http://localhost:8080/swagger-ui.html ```
##### Sample success response
```
{
    "code": 200,
    "applicationLabel": "WEBCRAWLER",
    "time": "2018-04-29T16:31:17.174+1000",
    "correlationId": "6bb85638-706e-4f52-8d7c-34431a932518",
    "method": "GET",
    "data": {
        "url": "https://start.spring.io/",
        "title": "Spring Initializr",
        "valid": true,
        "nodes": [
            {
                "url": "https://start.spring.io/#",
                "title": "Spring Initializr",
                "valid": true,
                "nodes": [
                    {
                        "url": "https://github.com/spring-io/initializr/",
                        "title": "GitHub - spring-io/initializr: A quickstart generator for Spring projects",
                        "valid": true,
                        "nodes": []
                    },
                    {
                        "url": "https://run.pivotal.io",
                        "title": "Pivotal Web Services | Home",
                        "valid": true,
                        "nodes": []
                    }
                ]
            }
        ]
    },
    "status": null,
    "message": null,
    "errors": null,
    "path": "/api/v1/crawler"
}
```
##### Smaple error response
```
{
    "code": 4000,
    "applicationLabel": "WEBCRAWLER",
    "time": "2018-04-29T16:46:28.457+1000",
    "correlationId": "d5b7a17f-306b-4932-9eb1-47ceeb28a45e",
    "method": "GET",
    "data": {},
    "status": null,
    "message": null,
    "errors": [
        {
            "code": "4000",
            "message": "HTTP error fetching URL : http://www.linkedin.com/company/1212"
        }
    ],
    "path": "/api/v1/crawler"
}
```

## Improvements
- Log Handler with Transaction context details
- add more tests.


