# Hotel Management REST API

A simple REST API for hotel management, developed during my internship at ITSvil S.R.L.

This REST API is designed for managing hotel operations, including user authentication, room booking, and reservation management.

It provides endpoints for user registration, authentication, room listing, room details, checking room availability within a specific timeframe, as well as booking creation, simulation, listing, retrieval, and cancellation.

## Endpoints

- **User Management**:
  - `POST /user/signup`: Register a new user.
  - `POST /user/login`: Authenticate a user.
  - `GET /user/logout`: Logout the authenticated user.

- **Room Management**:
  - `GET /rooms`: Retrieve all rooms.
  - `GET /rooms/{id}`: Retrieve details of a specific room by ID.
  - `GET /rooms/available`: Retrieve available rooms within a specified timeframe (query parameters: check_in, check_out).

- **Booking Management**:
  - `POST /bookings`: Create a new booking (authentication required).
  - `POST /bookings/simulate`: Simulate a booking and view the total price.
  - `GET /bookings`: Retrieve all bookings (authentication required).
  - `GET /bookings/{id}`: Retrieve details of a specific booking by ID (authentication required).
  - `DELETE /bookings/{id}`: Cancel a booking by ID (authentication required).

## Additional Information

This project is developed for educational purposes. It utilizes Swagger for API documentation, allowing users to explore all features. Unit tests are conducted using JUnit and Mockito.

## Getting Started

To get started with this API, please refer to the documentation provided by Swagger or explore the available endpoints directly. Ensure proper authentication when accessing authenticated endpoints.
