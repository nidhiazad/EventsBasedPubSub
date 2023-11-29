# Use the base image with OpenJDK 11
FROM adoptopenjdk/openjdk11:jre

# Set the working directory
WORKDIR /app

# Copy the application JAR file and configuration file into the container
COPY ./target/pub-sub-project-1.0-SNAPSHOT.jar ./pub-sub-project-1.0-SNAPSHOT.jar
COPY ./config/test.yaml ./config/test.yaml

# Define environment variable for port
ENV PORT 6000

# Define environment variables
ENV TOPIC_DIR /Users/sahanakuchur/Desktop/Final/EventBasedPubSub/TopicsRootFolder

# Expose the ports your application will run on
EXPOSE 6000

CMD ["java", "-cp", "pub-sub-project-1.0-SNAPSHOT.jar", "edu.scu.distributed.server.PubSubServerImpl", "./config/test.yaml", "6000", "/Users/sahanakuchur/Desktop/EventsProject/EventBasedPubSub/TopicsRootFolder"]
