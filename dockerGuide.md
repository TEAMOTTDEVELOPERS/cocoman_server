
## Guide for docker
### Use Docker hub
1. Pull docker image
 - [https://hub.docker.com/repository/docker/celi1004/cocomandb/general](https://hub.docker.com/repository/docker/celi1004/cocomandb/general)
```
$ docker pull celi1004/cocomandb:[version]
```
 > version (미지정 시 latest)
 > - latest
 > - v1
 
2. Run and connect to Container
  - Same as below
  
### Use Dockerfile
1. Build docker image
```
$ docker build -t [image name]:[tag] .
```
> docker build -t celi1004/cocomandb

2. Run Container
```
$ docker run -d -p 5432:5432 --name [container name] [image name]:[tag]
```
> docker run -d -p 5432:5432 --name cocoman celi1004/cocomandb

3. Connect to Container
```
$ docker exec -it [container name] /bin/bash
postgres@7ef98b1b7243:/$ psql -d cocoman -U cocomanlocal --password
```
