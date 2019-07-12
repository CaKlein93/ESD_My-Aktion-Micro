Repo Klonen um Dockerfile ausführen zu können:

Datenbank in Docker: 

docker run --name dbsec -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=bootdb -e MYSQL_USER=securityadmin -e MYSQL_PASSWORD=password -p 3306:3306 -d mysql:5.6  


APP im Docker Container ausführen:

docker build . -t secjwt
docker run -p8085:8085 secjwt

DockerCompose kann nicht gestartet werden, da MySql keinen HealthCheck an App schickt

