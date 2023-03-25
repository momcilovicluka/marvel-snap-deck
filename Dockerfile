# Base image
FROM eclipse-temurin:latest

# Expose port 8080
EXPOSE 8080

# add everything in the current directory to the image
COPY . .

# install maven
RUN apt update && apt install -y maven

# builc the marvelSnapDeckJPA project
RUN cd /marvelSnapDeckJPA && mvn clean install && mvn package && cd ..

# copy the jar file to the marvelSnapDeckWeb directory
RUN cp /marvelSnapDeckJPA/target/marvelSnapDeckJPA-0.0.1-SNAPSHOT.jar /marvelSnapDeckWeb/marvelSnapDeckJPA-0.0.1-SNAPSHOT.jar

# set the working directory to /marvelSnapDeckWeb
WORKDIR /marvelSnapDeckWeb

# build the image
RUN mvn clean install -DskipTests
RUN mvn package -DskipTests

# run the application
ENTRYPOINT [ "java", "-jar", "target/marvelSnapDeckWeb-0.0.1-SNAPSHOT.jar" ]

# docker build -t marvel-snap-deck-web .
# docker run -p 8080:8080 marvel-snap-deck-web