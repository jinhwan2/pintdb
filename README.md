# PintDB
Easy to use key-value database

## Interface
you can see all interface in swagger page.<br>
Link: [http://localhost:5959/swagger-ui.html](http://localhost:5959/swagger-ui.html)

- get
- set
- delete

## Data Type
- String
- HashMap
- List

## start
It is a simple launcher, not a binary file.<br>
This will be changed later.

```bash
./pintDB start
```

## Configuration
```yaml
server:
  #server port
  port: 5959 
  
  #health check port
  health: 10000
  
kvStore:
  #data expired time
  expired-time: 30 
  
  #persistent file path
  file-path: /logfs/data
  
  #persistent file name 
  file-name: store.log 

  #persistent on
  save: true
```

## Contribute Role
- code style - googleStyle
- register issue before pull request
