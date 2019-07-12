# ESD_My-aktion-Micro


## My-Aktion-API-Gateway




## My-Aktion Backend Deployment

### Datenbank
Für die Ausführung des des Backend-Services bedarf es zunächst einer Datenbank (H2).
Diese kann Lokal oder in Docker zur Verfügung stehen. (Ggf. die IP-Adresse im Source Code von My-Aktion-backend ändern)

Hier ein Link zum Deployment der [H2 in Docker](https://hub.docker.com/r/oscarfonts/h2/).
Anzumerken ist, dass innerhalb Docker ein [Volume](https://docs.docker.com/storage/volumes/) zur Verfügung stehen muss, damit es als DATA_DIR verwendet werden kann.

#### My-Aktion-backend

#### Local deployment
Innerhalb des Ordners \my-aktion sind alle notwendigen Artefakte enthalten, um die Anwendung mittels Maven zu bauen.

```
mvn package
```

Anschließend befindet sich innerhalb dieses Ordners ein Verzeichnis Namens 'target', in welchem sich das ausführbare JAR-File befindet.
Durch das Ausführen des folgenden Befehls, wird die Anwendung deployed. (!!!Wichtig!!! Datenbank muss aktiv sein und die entsprechende IP-Addresse im My-Aktion-Backend hinterlegt sein!)

```
java -jar demo-thorntail.jar
```

Wenn das Konsolenfenster, in welchem die Anwendung deployed wurde, keine Fehlermeldung ausgibt, hat alles geklappt und die REST-Schnittstellen können getestet werden.


#### Docker deployment

Nachdem die Anwendung lokal gebaut wurde, kann das JAR-File  auch innerhalb von Docker ausgeführt werden.
Dies wird dadurch möglich, dass ein [Docker Image](https://docs.docker.com/engine/reference/commandline/image_build/) erstellt wird. 
(!!! Wichtig!!! Bei Betrieb der Anwendung innerhalb von Docker, muss die IP-Adresse der Datenbank verändert werden, damit sich die Container finden.)

Nach erstellen des Docker Image kann dieses [gestartet](https://docs.docker.com/engine/reference/run/) werden. 


Anzumerken ist, dass [Thorntail innerhalb von Docker mittels IP-V4](https://www.sipmann.com/socketException-protocol-family-unavailable-java-docker-wildfly.html) seine IP-Adresse erhalten muss, sonst kann die Anwendung nicht ausgeführt werden.





## My-Aktion-frontend



