FROM eclipse-temurin:latest

EXPOSE 8080

# add everything in the current directory to the image
ADD . .

WORKDIR /marvelSnapDeckWeb

CMD [ "/bin/ls", "-alt" ]

ENTRYPOINT [ "java", "-jar", "target/marvelSnapDeckWeb-0.0.1-SNAPSHOT.jar" ]

# docker build -t marvel-snap-deck-web .
# docker run -p 8080:8080 marvel-snap-deck-web
