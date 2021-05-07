 
 
 


  Check wheteher docker is ruinning
  docker info

 OR to display the doscker running contaienrs
 docker ps

To display active docker images:
  docker ps -a

  docker image ls

  Change to the Docker direcory is current user dir

 
docker build -t springboot-rest-mysql -f Dockerfile-Tomcat-Deploy-War.txt .


Run  To make this app available outside the docker container

   docker run -d -p8090:8090 springboot-rest-mysql

   

  docker logs <container-name/id> 
    
  ------------------------------------------------------------
Run the mmysql conainer with name as mysql-server 

docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=mydb -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 -d mysql:8.0.24

docker ps -a
docker logs mysql-server 
docker inspect mysql-server 

Start the REST app container by linking the mysql container


docker run -p 8090:8090 --name rest-app --link mysql-server:mysql-server --d -p8090:8090 springboot-rest-mysql

------------------------------------------
Run with volume
The Volume specified here is used across the containers start and stop and will maniatin the database state across containers and across start and stop operations.

c/Users/Prakash/data/mysqldata

On Windows
docker run --name mysql-server --v /c/Users/Prakash/data/mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=mydb -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 -d mysql:8.0.24


On Linux/mac host
docker run --name mysql-server -v /mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=mydb -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 -d mysql:8.0.24


---------------------------------------------------------
 Log in to the Docker public registry on your local machine.

  docker login

  Publish the image on docker hub in the format
  docker tag image username/repository:tag

 
 docker tag springboot-rest-mysql pbadhe34/apps:spring-rest-mysql
 
  docker images
  docker image ls

 

  To Publish the image to docker hub
  Upload your tagged image to the repository:

  docker push pbadhe34/apps:spring-rest-mysql


Pull and run the image from the remote repository
From now on, you can use docker run and run your app on any machine with this command in the format
 

docker run -d -p 9090:8090 pbadhe34/apps:spring-rest-mysql

 ----------------------------
To check the mysql container records

   
docker exec -it <container name> <command> to execute whatever command you specify in the container.

docker attach will let you connect to your Docker container, but this isn't really the same thing as ssh. If your container is running a webserver, for example, docker attach will probably connect you to the stdout of the web server process. It won't necessarily give you a shell.


To check the mysql container
start a shell in the container by connecting  to it;


docker exec -it mysel-server /bin/bash

cat /etc/hosts
pwd
history
ls


 mysql -uroot -p mydb -e "select * from user_data"  

 mysql -uroot -p mydb -e "select * from hibernate_sequence"  

 

exit
--------------------------------------------- 

 
 

 

 

  
 

 
