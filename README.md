# e-Commerce Basket

Basket is in charge of managing basket services for the e-commerce **Feliz&Cia.**
Basket should provide a scalable and elastic service to have the ability of 
support customer demand.

In this project you'll see the following in action:

 - Domain Driven Design: Hexagonal Architecture with Ports and Adapters
 - Quarkus: Routes, Reactive Messages, Docker, Kubernetes
 - Java 11
 - Reactive plus Completable Futures

## Requirements

Basket must provide following endpoints:

 - create basket: **POST** `/basket/`
 - delete basket: **DELETE** `/basket/{id}`
 - get basket by id: **GET** `/basket/{id}`
 - add item by id: **POST** `/basket/{id}/item/{id}`
 - delete item by id: **DELETE** `/basket/{id}/item/{id}`

**The server must support concurrent invocations of those operations: any of them 
may be invoked at any time, while other operations are still being performed, 
**even for the same basket.**

At this stage, the service shouldn't use any external databases of any kind, 
but it should be possible to add one easily in the future.

## Assumptions

 - **Database** is not the purpose of this ReleaseCandidate, so `in memory access` 
 is implemented.
 - **Currency** selected as default is Euro. As EUROPE is the country of development, 
 cents is the unit of money and euro is the unit of currency The system does not
 provide any other currency in this pre-release. 
 - **Catalog service** will provide info about items, so ` CatalogAdapter.java` 
 returns hardcoded results
 - **Marketing service** will provide info discounts, so `MarketingAdapter.java`
 returns hardcoded results. **Discounts** must be implemented based on marketing 
 requirements, but for this pre-release, `minimum amount` of items and `discount percentage`
 are implemented.  

## Getting started

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/

These instructions will get you a copy of the project up and running on your local 
machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system.

### To get the code

Clone the repository:

    $ git clone git@github.com:javigs82/ecommerce-basket.git

If this is your first time using Github, review http://help.github.com to learn the basics.

### Prerequisites

What things you need to install the software and how to install them
* openJDK 11 
* Gradle 6.5

## Installing

Following command will clean and build the applications. In next sections, 
it will be described how to docker it.

```

./gradlew clean build

```

#### Dev Mode

You can run your application in dev mode that enables live coding using:

```

./gradlew quarkusDev

```

## Test

In order to execute test, please run:

```

./gradlew test -info

```

It will produce output as reports store in the following route:

> build/reports/tests/test/index.html

## Docker

Before building the docker image run:

> ./gradlew clean build

Then, build the image with:

> docker build -f src/main/docker/Dockerfile.jvm -t quarkus/ecommerce-basket-jvm .

Then run the container using:

> docker run -i --rm -p 8080:8080 quarkus/ecommerce-basket-jvm

If you want to include debug port into your docker image you will have to expose
debug port (default 5005) like this:`EXPOSE 8080 5050`

Then run the container using : 

> docker run -i --rm -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" javigs82/ecommerce-basket

## Kubernetes

Please refer to [build/kubernetes/kubernetes.yml](build/kubernetes/kubernetes.yml).
This is only a minimal spec. Service and Networking will be defined on heml chart.

## TODO

 - ci/cd
 - helm chart

## Author

 - javigs82 [github](https://github.com/javigs82/)

## License

This project is licensed under the terms of the MIT license: see the 
[LICENSE](./LICENSE) file for details
