# Task

Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.

Explicit requirements:

1. You can use Java or Kotlin.
2. Keep it simple and to the point (e.g. no need to implement any authentication).
3. Assume the API is invoked by multiple systems and services on behalf of end users.
4. You can use frameworks/libraries if you like ( except Spring ), but don't forget about
requirement #2 and keep it simple and avoid heavy frameworks.
5. The datastore should run in-memory for the sake of this test.
6. The final result should be executable as a standalone program (should not require a
pre-installed container/server).
7. Demonstrate with tests that the API works as expected.

Implicit requirements:

1. The code produced by you is expected to be of high quality.
2. There are no detailed requirements, use common sense.

## Features

This is a REST api to simulate a customer banking console. it features the following operations:

* Money transfer between customer's accounts
* Display customer
* Display all customers
* Simulate the log in of a customer

## How to start

To start the project you can run the jar file HomeTask.jar using:

Java -jar HomeTask.jar

Once it runs it will launch a server under localhost:4567 with the following services:

* Get all users   (/Customers)
* Get user        (/Customer/:id)
* LogIn user      (/CustomerLogIn/:id)
* Transfer Money! (/Customer/Transfer/:acc1/:acc2/:amount)

## How to Transfer Money

The system will genarate a set of in memory users everytime is launched and each user will have a list with 5 accounts.
By default the system will login a user to the payment console but this can changed by consuming the /CustomerLogIn service with a different customer id.

To transfer money set the two account numbers and the amount to the Customer/Transfer service e.g.:

* http://localhost:4567/Customer/Transfer/123456/234567/25



