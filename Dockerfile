# repositorio para auxiliar na geracao da imagem pelo maven  https://github.com/spotify/dockerfile-maven
FROM openjdk:11

WORKDIR /ws-algaworks/

#define uma variavel que pode ser passada em tempo de build
ARG JAR_FILE

COPY target/${JAR_FILE} /ws-algaworks/algafood-api.jar

EXPOSE 8080

CMD ["java", "-jar", "algafood-api.jar"]

#mvnw package -> gera o arquivo jar
#docker image build -t algafood-api . -> cria a imagem usando o jar