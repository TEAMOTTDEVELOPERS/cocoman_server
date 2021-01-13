
## Guide for docker
### Use Docker hub
1. Pull docker image
```
$ docker pull celi1004/cocomandb:v1
```
2. Run and connect to Container
  - Same as above
  
### Use Dockerfile
1. Build docker image
```
$ docker build -t [image name]:[tag] .
```
2. Run Container
```
$ docker run --rm -P --name [container name] [image name]:[tag]
```
3. Connect to Container
```
$ docker run --rm -t -i --link [container name]:[local_alias] [image name]:[tag] bash
postgres@7ef98b1b7243:/$ psql -h $PG_PORT_5432_TCP_ADDR -p $PG_PORT_5432_TCP_PORT -d cocoman -U cocomanlocal --password
```
