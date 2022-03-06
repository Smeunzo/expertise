# Expertise

## Prérequis
* Java 11 [ici](https://www.oracle.com/fr/java/technologies/javase/jdk11-archive-downloads.html)
* Maven 3.6.3 

### Build

```sh
mvn package
```

### Run

Dans 4 shells différents lancer dans cet ordre (toujours à la racine)

```shell
# SHELL 1
java -jar services/gateway/target/gateway.jar
```

```shell
# SHELL 2 
java -jar services/auth/target/auth.jar
```

```shell
# SHELL 3 
java -jar services/user/target/user.jar
```

```shell
# SHELL 4 
java -jar services/inventory/target/inventory.jar
```