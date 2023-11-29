docker build -t pubsub .
docker run -d -p 6000:6000 --name serv1 pubsub
docker run -d -p 6005:6000 --name serv2 pubsub
docker run -d -p 6010:6000 --name serv3 pubsub
