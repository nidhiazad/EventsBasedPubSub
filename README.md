# Events Based PubSub Project

This project implements a simple Events Based PubSub system with servers for managing topics and clients for publishing and subscribing to events.

## Getting Started

Follow the steps below to set up and run the Events Based PubSub system.

### Prerequisites

- Java Development Kit (JDK) installed
- Maven for building the project

## Building the Project

### Clone the repository and navigate to the project folder.

git clone <repository-url>
cd pub-sub-project

Build the project using Maven.

mvn clean install
### Starting PubSub Servers
### Start multiple PubSub servers, each with a unique server port and a path to a folder for managing topics.

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml server-port-1 path-to-folder-1

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml server-port-2 path-to-folder-2

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml server-port-3 path-to-folder-3

Example: ava -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml 6000 /Users/sahanakuchur/Desktop/Demo/EventsBasedPubSub/TopicsRootFolder/sev1

### Starting Publisher Client
### Start a publisher client, specifying the publisher's host, port, and the server's host and port.

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.ClientService publisher publisher-host publisher-port server-host-1 server-port-1

Example:java -cp  ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.ClientService publisher xx.x.x.x 8090 xx.x.x.x 6000

### Sending Event from Publisher
### Send an event from the publisher client, specifying the server's host, port, topic, and event name.

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.ClientService send server-host-1 server-port-1 topic event-name

Example: send x.xx.x.x 6000 /Sports Event name

### Starting Subscriber Client
### Start one or more subscriber clients, specifying the subscriber's host, port, and the server's host and port.

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.ClientService subscriber subscriber-host subscriber-port server-host-1 server-port-1

Example : java -cp  ./target/pub-sub-project-1.0-SNAPSHOT.jar  edu.scu.distributed.client.ClientService subscriber localhost 8071 xx.x.x.x 6000
### Getting Messages from Subscriber
### Retrieve messages from a subscriber client, specifying the server's host, port, and the subscription path.
subscriber x.x.x.x 8071 xx.x.x.x 6005

getMessages x.x.x.x 6000 /sev1

## Customization
Customize the server configurations in the ./config/test.yaml file.
Adjust the placeholder values in the commands based on your specific setup.
