# Basket

Basket is in charge of managing basket services for the e-commerce "felicisimo".
Basket should provide a scalable and elastic service to have the ability of 
support customer demand.

## Requirements

Basket must provide following endpoints:

 - create basket: `/basket/`
 - get/delete basket: GET/DELETE `/basket/{id}`
 - add/remove element by id: POST/DELETE `/basket/{id}/item/{id}`

The server must support concurrent invocations of those operations: any of them 
may be invoked at any time, while other operations are still being performed, 
even for the same basket.

At this stage, the service shouldn't use any external databases of any kind, 
but it should be possible to add one easily in the future.

## Assumptions

 - **Catalog service** will provide info about items, so CatalogAdapter.java 
 returns hardcoded results
 - **Marketing service** will provide info discounts, so MarketingAdapter.java
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

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./build/ecommerce-basket-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable.

## Test

## Docker

## Helm

## CI/CD (jenkins)

## Author

[javigs82](https://github.com/javigs82)

## License

This project is licensed under the terms of the MIT license: see the 
[LICENSE] (./LICENSE) file for details
