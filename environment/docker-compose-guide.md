## Guide for docker-compose
### How to use docker-compose?

* Remove legacy docker container and image
```shell
# Find running docker container
$ docker ps

# Stop running docker container
$ docker stop <container-id>

# Remove running docker container
$ docker rm <container-id>
```

* Run docker-compose
```shell
# Remove exist docker file that executes spring application
$ docker rmi cocoman-applicaiton-docker

# Run this command in environment/
$ docker-compose -f docker-compose.yaml up -d
```
* Stop docker-compose
```
# Run this command in environment/
$ docker-compose -f docker-compose.yaml down
```