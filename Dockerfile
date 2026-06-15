FROM gradle:jdk17-corretto AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]

#FROM amazoncorretto:17-alpine AS build
#
#WORKDIR /app
#
#COPY build/libs/agendador-tarefas-0.0.1-SNAPSHOT.jar /app/agendador-tarefas.jar
#
#EXPOSE 8083
#
#CMD ["java", "-jar", "/app/agendador-tarefas.jar"]
