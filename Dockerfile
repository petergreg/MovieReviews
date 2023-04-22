FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal

WORKDIR /app

COPY ./bootstrap/target/movie-reviews.jar .

EXPOSE 8080

CMD java \
    $JAVA_OPTS \
    -jar movie-reviews.jar
