FROM openjdk:11

WORKDIR /ws-algaworks/

COPY target/*.jar /ws-algaworks/algafood-api.jar

EXPOSE 8080

CMD ["java", "-jar", "algafood-api.jar"]