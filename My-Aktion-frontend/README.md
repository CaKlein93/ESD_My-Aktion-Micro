# my-aktion_orga-frontend



## Local setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```
 -> Http-Server wird automatisch gestartet. IP erschein in der CMD

### Compiles and minifies for production
```
npm run build
```


## Docker setup

### Docker image bauen
```
docker build -t my-aktion_frontend .
```

### Docker container starten
```
docker run -it -p 8080:8080 --rm --name my-aktion_frontend_container my-aktion_frontend
```

### Hinweis:
- Die URL's sind hart kodiert, diese müssen ggf. angepasst werden
- Der Login mechanismus ist derzeit auskommentiert (router.js & Login.vue)
