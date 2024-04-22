# README

This is the README for the article micro-service of the system, developed using the Spring boot framework and Java.

## Requirements

- **Java**: 17
- **Database**: Postgres (version >= 16.2)
- **Gradle**: version >= 8.7.0

Make sure Java is in the specified version and that the databases are configured correctly before proceeding.

## Installation

1. Clone this repository.
2. Install dependencies using the command:
   ```bash
   ./gradlew build
   ```
3. Configure environment variables by creating a `.env` file. Use the `.env.example` file as an example. [Example `.env.example` file](.env.example)
4. Start the application using the command:
    ```bash
        ./gradlew bootRun ./gradlew bootRun --main-class=br.com.anp.microservice.article.ArticleApplication
    ```
   
### Docker integration

This project have docker integration, make sure to have those dependencies installed:

1. **Docker**: version >= v25.0
2. **Docker Compose(Optional)**: version >= v2.24.3

#### Using docker:
```
   export $(cat .env | xargs) && \
   docker build -t article-microsservice:latest -f docker/Dockerfile . && \
   docker run --name article_service article-microsservice:latest -d
```

#### Using docker compose:
```
   export $(cat .env | xargs) && \
   docker-compose -f docker/docker-compose.yaml up
```

## Usage

The article microservice has two main routes:

1. **Article**: Route to manage the article data.

2. **Health check**: Route to verify the availability of the API.

For more details about the endpoints, refer to the [docs/routes.md](docs/routes.md) file.