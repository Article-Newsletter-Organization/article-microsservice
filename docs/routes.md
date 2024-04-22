## Endpoint: /articles
### Description:
This endpoint aims to allow manipulation of articles posted by authors.

### HTTP Methods:
- GET, POST, PUT, DELETE

### URL:
- ``http://localhost:8080/articles``


## Method GET:

### Request example:


```http
GET http://localhost:8080/articles
Content-Type: application/json


{}
```


### Response example:


#### Case: `Success`

```http 
Status code: 200

Content-Type: application/json


{
  "data": [
    {
      "id": "<UUID>",
      "title": "A title representing the article.",
      "subtitle": "A brief sample of the article's subject.",
      "objectId": "<UUID>",
      "description": "A short description of the article",
      "postedAt": "2024-04-22T17:53:29.886+00:00",
      "authorId": "<UUID>"
    }
  ],
  "error": null,
  "timestamp": "2024-04-22T17:53:29.981+00:00"
}
```


## Method GET:

### Request example:


```http
GET http://localhost:8080/articles/<ARTICLE_UUID>
Content-Type: application/json


{}
```


### Response example:


#### Case: `Success`

```http 
Status code: 200

Content-Type: application/json


{
  "data": {
    "id": "<UUID>",
    "title": "A title representing the article.",
    "subtitle": "A brief sample of the article's subject.",
    "objectId": "<UUID>",
    "description": "A short description of the article",
    "postedAt": "2024-04-22T17:53:29.886+00:00",
    "authorId": "<UUID>"
  },
  "error": null,
  "timestamp": "2024-04-22T17:53:29.981+00:00"
}
```


## Method POST:

### Request example:


```http
POST http://localhost:8080/articles
Content-Type: application/json


{
  "title": "A title representing the article.",
  "subtitle": "A brief sample of the article's subject.",
  "objectId": "<UUID>",
  "description": "A short description of the article",
  "authorId": "<UUID>"
}
```


### Response example:


#### Case: `Success`

```http 
Status code: 201

Content-Type: application/json


{
  "data": {
    "id": "<UUID>",
    "title": "A title representing the article.",
    "subtitle": "A brief sample of the article's subject.",
    "objectId": "<UUID>",
    "description": "A short description of the article",
    "postedAt": "2024-04-22T17:53:29.886+00:00",
    "authorId": "<UUID>"
  },
  "error": null,
  "timestamp": "2024-04-22T17:53:29.981+00:00"
}
```

#### Case: `Title is already in use`

```http 
Status code: 409

Content-Type: application/json


{
  "data": null,
  "error": {
    "message": "Title already in use!",
    "status": "CONFLICT",
    "statusCode": 409,
    "details": {
      "name": "DUPLICATED_DATA_EXCEPTION",
      "message": "Title already in use!",
      "stack": null,
      "issues": []
    }
  },
  "timestamp": "2024-04-22T17:55:23.745+00:00"
}
```

#### Case: `Invalid data request body`

```http 
Status code: 400

Content-Type: application/json


{
  "data": null,
  "error": {
    "message": "The data received are not valid.",
    "status": "BAD_REQUEST",
    "statusCode": 400,
    "details": {
      "name": "VALIDATION_EXCEPTION",
      "message": "The data received are not valid.",
      "stack": null,
      "issues": [],
      "fields": [
        {
          "target": "<field name>",
          "message": "<error message>"
        }
      ]
    }
  },
  "timestamp": "2024-04-22T17:56:18.722+00:00"
}
```


## Method PUT:

### Request example:


```http
PUT http://localhost:8080/articles/<ARTICLE_UUID>
Content-Type: application/json


{
  "title": "A title representing the article.",
  "subtitle": "A brief sample of the article's subject.",
  "objectId": "<UUID>",
  "description": "A short description of the article",
  "authorId": "<UUID>"
}
```


### Response example:


#### Case: `Success`

```http 
Status code: 200

Content-Type: application/json


{
  "data": {
    "id": "<UUID>",
    "title": "A title representing the article.",
    "subtitle": "A brief sample of the article's subject.",
    "objectId": "<UUID>",
    "description": "A short description of the article",
    "postedAt": "2024-04-22T17:53:29.886+00:00",
    "authorId": "<UUID>"
  },
  "error": null,
  "timestamp": "2024-04-22T17:53:29.981+00:00"
}
```

#### Case: `Title is already in use`

```http 
Status code: 409

Content-Type: application/json


{
  "data": null,
  "error": {
    "message": "Title already in use!",
    "status": "CONFLICT",
    "statusCode": 409,
    "details": {
      "name": "DUPLICATED_DATA_EXCEPTION",
      "message": "Title already in use!",
      "stack": null,
      "issues": []
    }
  },
  "timestamp": "2024-04-22T17:55:23.745+00:00"
}
```

#### Case: `Invalid data request body`

```http 
Status code: 400

Content-Type: application/json


{
  "data": null,
  "error": {
    "message": "The data received are not valid.",
    "status": "BAD_REQUEST",
    "statusCode": 400,
    "details": {
      "name": "VALIDATION_EXCEPTION",
      "message": "The data received are not valid.",
      "stack": null,
      "issues": [],
      "fields": [
        {
          "target": "<field name>",
          "message": "<error message>"
        }
      ]
    }
  },
  "timestamp": "2024-04-22T17:56:18.722+00:00"
}
```


## Method DELETE:

### Request example:


```http
DELETE http://localhost:8080/articles/<ARTICLE_UUID>
Content-Type: application/json


{}
```


### Response example:


#### Case: `Success`

```http 
Status code: 204

Content-Type: application/json


{}
```

## Endpoint: /health
### Description:
This endpoint aims to display the health of the API, indicating whether it is available for use. It returns a response describing the system's health.

### HTTP Methods:
- GET

### URL:
- ``http://localhost:8080/health``


## Method GET:

### Request example:


```http
GET http://localhost:8080/health
Content-Type: application/json


{}
```


### Response example:


#### Case: `Success`

```http 
Status code: 200

Content-Type: application/json


{
  "data": {
    "status": "GOOD",
    "active": true
  },
  "error": null,
  "timestamp": "2024-04-22T18:00:13.223+00:00"
}
```

