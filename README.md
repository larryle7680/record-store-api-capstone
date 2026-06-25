# Record Store API Capstone

A RESTful backend API for a record store/e-commerce application. This project was built with Java, Spring Boot, MySQL, Spring Security, JWT authentication, and JPA.

## Features

* User registration and login
* JWT-based authentication
* Role-based access for users and admins
* Browse and search products
* Manage product categories
* Add products to a shopping cart
* Update or clear cart items
* View and update user profile
* Checkout cart and create orders
* Store order line items for each purchased product

## Tech Stack

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* Spring Security
* JWT
* MySQL
* Maven
* OpenAPI / Swagger

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── org/yearup/
│   │       ├── controllers/
│   │       ├── models/
│   │       ├── repositories/
│   │       ├── services/
│   │       └── security/
│   └── resources/
├── database/
├── openapi.yaml
└── pom.xml
```

## API Endpoints

### Authentication

| Method | Endpoint    | Description                 |
| ------ | ----------- | --------------------------- |
| POST   | `/login`    | Login and receive JWT token |
| POST   | `/register` | Register a new user         |

### Products

| Method | Endpoint         | Description                                |
| ------ | ---------------- | ------------------------------------------ |
| GET    | `/products`      | Get all products or search/filter products |
| GET    | `/products/{id}` | Get product by ID                          |
| POST   | `/products`      | Add product, admin only                    |
| PUT    | `/products/{id}` | Update product, admin only                 |
| DELETE | `/products/{id}` | Delete product, admin only                 |

### Categories

| Method | Endpoint                            | Description                 |
| ------ | ----------------------------------- | --------------------------- |
| GET    | `/categories`                       | Get all categories          |
| GET    | `/categories/{id}`                  | Get category by ID          |
| GET    | `/categories/{categoryId}/products` | Get products by category    |
| POST   | `/categories`                       | Add category, admin only    |
| PUT    | `/categories/{id}`                  | Update category, admin only |
| DELETE | `/categories/{id}`                  | Delete category, admin only |

### Shopping Cart

| Method | Endpoint                     | Description              |
| ------ | ---------------------------- | ------------------------ |
| GET    | `/cart`                      | View current user's cart |
| POST   | `/cart/products/{productId}` | Add product to cart      |
| PUT    | `/cart/products/{productId}` | Update product quantity  |
| DELETE | `/cart`                      | Clear cart               |

### Profile

| Method | Endpoint   | Description                   |
| ------ | ---------- | ----------------------------- |
| GET    | `/profile` | View current user's profile   |
| PUT    | `/profile` | Update current user's profile |

### Orders

| Method | Endpoint  | Description                             |
| ------ | --------- | --------------------------------------- |
| POST   | `/orders` | Checkout and convert cart into an order |

## How Checkout Works

When a user sends a `POST` request to `/orders`, the API:

1. Gets the currently logged-in user.
2. Retrieves the user's shopping cart.
3. Creates a new order record.
4. Creates one order line item for each cart item.
5. Saves the order and line items to the database.
6. Clears the user's shopping cart.

No request body is required for checkout because the API uses the authenticated user's cart.

## Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 17
* Maven
* MySQL
* IntelliJ IDEA, VS Code, or another Java IDE
* Insomnia or Postman for API testing

### Installation

Clone the repository:

```bash
git clone https://github.com/larryle7680/record-store-api-capstone.git
```

Navigate into the project folder:

```bash
cd record-store-api-capstone
```

Install dependencies:

```bash
mvn install
```

Set up the database using the SQL script inside the `database` folder.

Update your database connection settings in `application.properties`.

Run the application:

```bash
mvn spring-boot:run
```

The API will run locally at:

```text
http://localhost:8080
```

## Authentication

Protected routes require a JWT token.

First, log in:

```http
POST /login
```

Then copy the returned token and include it in your request headers:

```http
Authorization: Bearer YOUR_TOKEN_HERE
```

## Example Checkout Request

```http
POST http://localhost:8080/orders
Authorization: Bearer YOUR_TOKEN_HERE
```

No request body is required.

## What I Learned

Through this project, I practiced:

* Building REST APIs with Spring Boot
* Creating controller, service, repository, and model layers
* Using JPA to connect Java objects to database tables
* Implementing JWT authentication
* Working with authenticated users
* Managing shopping cart logic
* Converting cart data into orders and order line items
* Testing endpoints with API tools like Insomnia

## Author

Larry Le

GitHub: [larryle7680](https://github.com/larryle7680)
