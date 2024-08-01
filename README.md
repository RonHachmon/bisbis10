# Restaurant Management System

## Overview
The bisbis10 restaurant management system is a backend service designed to handle various operations related to restaurants, their dishes, and ratings. The system aims to provide a comprehensive platform for managing restaurant data, including details about the restaurants, their cuisines, dishes, and customer ratings.

## Functionality
The system provides the following APIs:

- **Restaurants API**: Manages restaurant data.
- **Ratings API**: Manages customer ratings for restaurants.
- **Dishes API**: Manages the dishes offered by each restaurant.

## Technical Aspects
The system is built using Java Spring Boot, leveraging its robust framework for creating RESTful APIs.

## APIs

### Restaurants APIs

| API Description           | Endpoint                | Request Body                                             | Response Status | Response Body                                                                                           |
|---------------------------|-------------------------|----------------------------------------------------------|-----------------|--------------------------------------------------------------------------------------------------------|
| Get all restaurants       | GET /restaurants        |                                                          | 200 OK          | [{"id": "1","name": "Taizu","averageRating" : 4.83,"isKosher" : false,"cuisines": ["Asian","Mexican","Indian"]}] |
| Get restaurants by cuisine| GET /restaurants?cuisine={cuisine} |                                                         | 200 OK          | [{"id": "1","name": "Taizu","averageRating" : 4.83,"isKosher" : false,"cuisines": ["Asian","Mexican","Indian"]}] |
| Get restaurant            | GET /restaurants/{id}      |                                                          | 200 OK          | {"id": "1","name": "Taizu","averageRating" : 4.83,"isKosher" : false,"cuisines": ["Asian","Mexican","Indian"],"dishes": [{"id": "1","name": "Noodles","description": "Amazing one","price": 59}]} |
| Add a restaurant          | POST /restaurants       | {"name": "Taizu","isKosher": false,"cuisines": ["Asian","Mexican","Indian"]} | 201 CREATED     |                                                                                                        |
| Update a restaurant       | PUT /restaurants/{id}     | {"cuisines": ["Asian"]}                                 | 200 OK          |                                                                                                        |
| Delete a restaurant       | DELETE /restaurants/{id}    |                                                          | 204 No Content  |                                                                                                        |


### Ratings APIs

| API Description           | Endpoint               | Request Body                          | Response Status | Response Body |
|---------------------------|------------------------|---------------------------------------|-----------------|---------------|
| Add a restaurant rating   | POST /ratings          | {"restaurantId": 2, "rating":3.3}     | 200 OK          |               |

### Order APIs

| API Description           | Endpoint               | Request Body                          | Response Status | Response Body |
|---------------------------|------------------------|---------------------------------------|-----------------|---------------|
| Order    | POST /order          | {"restaurantId": 2, "orderItems":[{"dishId":12,"amount":1},{"dishId":14,"amount":1} ]} ]   | 200 OK          |  {orderId:"ef401fc8-d545-424b-928d-4789cd47bb6e"}             |

### Dishes APIs

| API Description           | Endpoint                | Request Body                             | Response Status | Response Body                                                     |
|---------------------------|-------------------------|------------------------------------------|-----------------|------------------------------------------------------------------|
| Add a dish                | POST /restaurants/{id}/dishes | {"name":"Shakshuka","description":"Great one","price": 34} | 201 CREATED     |                                                                  |
| Update a dish             | PUT /restaurants/{id}/dishes/{dishId} | {"description":"Great one","price": 34} | 200 OK          |                                                                  |
| Delete a dish             | DELETE /restaurants/{id}/dishes/{dishId} |                                        | 204 No Content  |                                                                  |
| Get dishes by a restaurant| GET /restaurants/{id}/dishes  |                                         | 200 OK          | [{"id":"1","name":"Humus","description":"Good one","price": 48}] |
