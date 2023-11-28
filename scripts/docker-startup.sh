docker build -t pubsub .
docker run -d -p 6010:6000 --name sev3 pubsub
docker run -d -p 6000:6000 --name sev1 pubsub
docker run -d -p 6005:6000 --name sev2 pubsub
