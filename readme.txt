java -cp /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl config/test.yaml 6000 /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/TopicsRootFolder

java -cp /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl config/test.yaml 6005 /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/TopicsRootFolder


java -cp /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.server.PubSubServerImpl config/test.yaml 6010 /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/TopicsRootFolder

java -cp  /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.UserClient publisher  192.168.1.114 8090 192.168.1.114 6000
send 192.168.1.114 6000 /topic2 hiSahana
send 192.168.1.114 6000 /topic2 HelloDeepak
6005 serv3 local 8
6010 serv2 local 3
6000 serv1  local 2
java -cp /Users/sahanakuchur/Documents/Java/pubsub/PubSubModel/target/pub-sub-project-1.0-SNAPSHOT.jar edu.scu.distributed.client.UserClient subscriber 192.168.1.114 8071 192.168.1.114 6005

 subscribe 192.168.1.114 6000 /topic4
 getMessages 1192.168.1.114 6000 /topic4
getMessages 192.168.1.114 6000 /topic2