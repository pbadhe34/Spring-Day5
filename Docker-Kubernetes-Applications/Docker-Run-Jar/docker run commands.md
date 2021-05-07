
docker build -t app-image -f SpringBootApp-Dockerfile.txt .
docker image ls
docker inspect app-image


docker ps 
docker inspect  springbootapp

Build the image with another file
docker build -t app-jre-run -f Dockerfile-JRE.txt .

Run the image into container
docker run -d --name springbootapp app-image

Run the second image into container


docker run -d --name app app-jre-run 



Connect to the OS inside the container 

docker exec -it springbootapp /bin/bash

>ls
>pwd
>java -version
>exit

Stop the container
docker stop springbootapp 

Remove the container
docker rm springbootapp 


Bind to the localhost port 8090
docker run -d -p8090:8090 --name app1 app-image

check in browser http://localhost:8090/web/test


Test with Curl in cmd or RESTED plugin in Chome

curl http://localhost:8090/web/test

Test with  RESTED plugin in Chome

Get method-->http://localhost:8090/web/test
 

Bind to  Any avaibale port on Host system randomly
docker run -d -p:8090 app-image

docker ps

Stop all the contaiers
docker stop <container-name/id>
Remove the containers
docker rm <container-name/id>

Check all the containebrs
docker ps -a

To publish/push the image to docker-Hub rehistry on Inyrenet network
 Tag the image 
docker tag app-image pbadhe34/apps:springboot-app

Login to the Docker HIb as tegistred user
docker login

docker push pbadhe34/apps:springboot-app


Delete all local images that you built.

docker rmi app-image
docker rmi app-jre-run 
docker rmi pbadhe34/apps:springboot-app

Run the image by pulling from docker hub

docker run -d -p8090:8090 pbadhe34/apps:springboot-app







