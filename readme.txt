java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml 6000 /Users/nidhi/Desktop/EventsBasedPubSub/TopicsRootFolder/sev1
java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml 6005 /Users/nidhi/Desktop/EventsBasedPubSub/TopicsRootFolder/sev2
java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml 6010 /Users/nidhi/Desktop/EventsBasedPubSub/TopicsRootFolder/sev3


Publisher :

java -cp  /Users/nidhi/Desktop/EventsBasedPubSub/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.ClientService publisher 10.0.0.191 8090 10.0.0.191 6000
send 10.0.0.191 6000 /NewYear Bay Area New Year's Party

Subscriber :
java -cp /Users/nidhi/Desktop/EventsBasedPubSub/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.ClientService subscriber 10.0.0.191 8071 10.0.0.191 6000
	subscribe 10.0.0.191 6000 /NewYear
	getMessages 10.0.0.191 6000 /NewYear