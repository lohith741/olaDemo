# Ola Demo Application

## Database Schema

DataBase Name : ola_db

### Table:
driver_db:
```
Long driver_id             //to store id of each driver
Boolean driver_Status      //to get the status of each driver
```

request_db:
```
Long request_id            // id of the request
Long customer_id           // id of the customer
Long driver_id             // id of the driver assigned
Boolean request_Status     // status of each request
Date request_time          // request logged time
Date accepted_time         // request accepted time
```
added sql files of the tables in database folder

## BackEnd

### API Methods Executed:
Here are few api calls executed in the back end.

1. To add customer request to the request database


> URI: localhost:8080/ola/customer/25

> Method: POST

2. To get all the all the data required for driver app

> URI: localhost:8080/ola/driver/3

> Method : GET

3. To select a particular request for a particular driver

> URI: localhost:8080/ola/driver/selectRequest?requestId=11&driverId=2

> Method: POST

4. To get all the data of requests for the dashboard

> URI: localhost:8080/ola/request

> Method: GET

added postman collections file

## FrontEnd

All the files related to frontEnd are in front_end folder

### Driver Application

Driver's application can be called from index.html

### Customer Application

Customer application can be called from customer.html

### DashBoard Application

DashBoard application can be called from dashboard.html

