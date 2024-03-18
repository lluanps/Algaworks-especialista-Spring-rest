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

#enviando para dockerhub
#Cria um tagname para a imagem -> docker image tag algafood-api:latest lluanps/algafood-api:latest
#Inicia o processo de login no docker -> docker login
#Faz um push para o dockerhub -> docker push lluanps/algafood-api:latest
#Rodando container com a imagem no dockerHub -> docker container run --rm -p 8080:8080 -e DB_HOST=algafood-postgres -e S3-ID-KEY-ACCESS="minha chave aqui" -e s3-key-secret="e minha senha aqui" --network algafood-network lluanps/algafood-api