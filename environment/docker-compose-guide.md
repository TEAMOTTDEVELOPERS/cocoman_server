## Guide for docker-compose
### How to use docker-compose?

0. Remove legacy docker container and image
```shell
# Find running docker container
$ docker ps

# Stop running docker container
$ docker stop <container-id>

# Remove running docker container
$ docker rm <container-id>
```

1. Run docker-compose
```shell
# Run this command in environment/
$ docker-compose -f docker-compose.yaml up -d
```