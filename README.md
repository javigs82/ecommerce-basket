# Basket

Basket is in charge of managing basket services for the e-commerce **Feliz&Cia.**
Basket should provide a scalable and elastic service to have the ability of 
support customer demand.

In this project you'll see the following in action:

 - Domain Driven Design: Hexagonal Architecture with Ports and Adapters
 - Quarkus: Routes, Reactive Messages, Docker, Kubernetes
 - Java 11
 - Asynchronism with Completable Futures


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

 - **Catalog service** will provide info about items, so ` CatalogAdapter.java` 
 returns hardcoded results
 - **Marketing service** will provide info discounts, so `MarketingAdapter.java`
 returns hardcoded results.


## Getting started

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./gradlew quarkusDev
```

### Packaging and running the application

The application can be packaged using `./gradlew quarkusBuild`.
It produces the `ecommerce-basket-1.0.0-SNAPSHOT-runner.jar` file in the `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/ecommerce-basket-1.0.0-SNAPSHOT-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

### Creating a native executable

You can create a native executable using: `./gradlew build -Dquarkus.package.type=native`.

Or, if you don't have GraalVM installed, you can run the native executable build 
in a container using: `./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./build/ecommerce-basket-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult 
https://quarkus.io/guides/gradle-tooling#building-a-native-executable.

## Docker

Before building the docker image run:

> ./gradlew clean build

Then, build the image with:

> docker build -f Dockerfile -t javigs82/ecommerce-basket .

Then run the container using:

> docker run -i --rm -p 8080:8080 javigs82/ecommerce-basket

If you want to include debug port into your docker image you will have to expose
debug port (default 5005) like this:`EXPOSE 8080 5050`

Then run the container using : 

> docker run -i --rm -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" javigs82/ecommerce-basket

## Kubernetes

## Author

[javigs82](https://github.com/javigs82)

## License

This project is licensed under the terms of the MIT license: see the 
[LICENSE](./LICENSE) file for details
