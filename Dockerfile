FROM adoptopenjdk/openjdk11:jre
COPY ./target/pub-sub-project-1.0-SNAPSHOT-jar-with-dependencies.jar ~/jar/app.jar
CMD ["java" ,"-cp" ,"~/jar/app.jar", "Main"]

EXPOSE 6000