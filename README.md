# Supplier-Service

### Как собрать и запустить 
*Сборка .jar и запуск без Docker*

- Сборка .jar

```
    mvn clean package
```
- Запуск .jar

```
    java -jar .\target\supplier-service-0.0.1-SNAPSHOT.jar
```

*Сборка Docker image и запуск в docker-compose*

```
    docker-compose build
```
```
    docker-compose up
```
