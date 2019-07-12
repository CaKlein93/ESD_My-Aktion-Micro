Repo Klonen um Dockerfile ausführen zu können:

Datenbank in Docker: 

docker run --name dbsec -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=bootdb -e MYSQL_USER=securityadmin -e MYSQL_PASSWORD=password -p 3306:3306 -d mysql:5.6  


Run it in Docker container:

Build the docker file once: docker build . -t secjwt

DockerCompose kann nicht gestartet werden, da MySql keinen HealthCheck an App schickt

