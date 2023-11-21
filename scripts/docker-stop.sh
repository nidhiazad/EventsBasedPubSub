docker stop $(docker ps --filter name=node -aq)
docker rm $(docker ps --filter name=node -aq)