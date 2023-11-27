
java -cp /Users/nidhi/Documents/project/pub-sub-project/target/pub-sub-project-1.0-SNAPSHOT.jar PubSubServerImpl /Users/nidhi/Documents/project/pub-sub-project/config/test.yaml
 6000 /Users/nidhi/Documents/project/pub-sub-project/TopicsRootFolder



java -cp /Users/nidhi/Documents/project/pub-sub-project/target/pub-sub-project-1.0-SNAPSHOT.jar PubSubServerImpl /Users/nidhi/Documents/project/pub-sub-project/config/test.yaml 6000 /Users/nidhi/Documents/project/pub-sub-project/TopicsRootFolder


java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar PubSubServerImpl ./config/test.yaml 6000 /Users/nidhi/Documents/project/pub-sub-project/TopicsRootFolder

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar PubSubServerImpl ./config/test.yaml 6000 /Users/dharahastallapally/masters/cs249/finals/topics/sev1

java -cp ./target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl ./config/test.yaml 6000 /Users/nidhi/Documents/project/pub-sub-project/topics/sev1


java -cp /Users/nidhi/Documents/project/pub-sub-project/target/pub-sub-project-1.0-SNAPSHOT.jar PubSubServerImpl /Users/nidhi/Documents/project/pub-sub-project/config/test.yaml 6000 /Users/nidhi/Documents/project/pub-sub-project/TopicsRootFolder


java -cp /Users/nidhi/Documents/project/pub-sub-project/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl /Users/nidhi/Documents/project/pub-sub-project/config/test.yaml 6010 /Users/nidhi/Documents/project/pub-sub-project/TopicsRootFolder/sev3

java -cp  /Users/nidhi/Documents/project/pub-sub-project/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.UserClient publisher 10.0.0.191 8090 10.0.0.191 6000

send 10.0.0.191 6000 /topic55 Chk This

6005 serv3 local 8
6010 serv2 local 3
6000 serv1  local 2

java -cp /Users/nidhi/Documents/project/pub-sub-project/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.UserClient subscriber 10.0.0.191 8071 10.0.0.191 6005

	subscribe 10.0.0.191 6000 /topic4
	getMessages 10.0.0.191 6000 /topic4


subscribe 10.0.0.191 8080 /Users/nidhi/Documents/project/pub-sub-project/TopicsRootFolder/topic1

