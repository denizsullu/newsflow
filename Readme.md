# News Aggregator API and React Application

This project automatically fetches news from various news websites and presents them as an API endpoint. The news is then retrieved from this API and displayed in a user-friendly interface in a React application. The system performs a news refresh operation every 10 minutes to keep the news up-to-date. This application is ideal for users who want to stay updated with news from a single platform.

## Features

- Automatic news updates from multiple sources
- User-friendly React interface
- Regular updates every 10 minutes
- Comprehensive news aggregation

## Installation

Follow these steps to install and run the project:

1. **Clone the Repository**
2. **Set Up PostgreSQL Database**
- Ensure you have Docker installed on your system.
- Navigate to the `backend` directory.
- Modify the `docker-compose.yml` file with your PostgreSQL configuration.
- Start the PostgreSQL database using Docker:
  ```
  docker-compose up -d

3. **Start the Backend Server**
- Inside the `backend` folder, make sure you have Java and Maven installed.
- Install dependencies and build the project:
  ```
  mvn clean install
  ```
- Start the Spring Boot application:
  ```
  mvn spring-boot:run
  ```

4. **Start the Frontend Application**
- Navigate to the `frontend` directory.
- Install dependencies:
  ```
  pnpm install
  ```
- Start the frontend application:
  ```
  pnpm dev
  ```

## Usage

To use the API, send requests to the following endpoints:
- Fetch news: `/api/news`

## Screenshots
![Screenshot](screens/Screenshot.png)


## Contributing

Guidelines for those who want to contribute to this project.

## FAQ

- Q: How often is the news updated?
  A: Every 10 minutes.

## License

This project is licensed under the MIT License. For more information and terms of use, please see the [LICENSE.md](LICENSE.md) file in the root directory of the project.

## Contact

For any questions, please email [denizsullu@protonmail.com](mailto:denizsullu@protonmail.com).

