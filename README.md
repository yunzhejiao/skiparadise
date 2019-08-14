# ski-paradise: a spring boot web application

## RESTful APIs :
```
1. "/"
2. "/listlifts"
3. "/listskiers"
4. "/liftsbyname"
5. "/topskiersbylift"
6. "/busiestlifts"
7. "/busiestliftsbyhour"
8. "/leastbusiestliftsbyhour"
9. "/popularliftbyskier"
```

## Steps for executing :

1. Clone/Download the repository.

2. Run App.java to generate CSV.

3. open web app locally: 
```
          mvn clean spring-boot:run
```
4. Swagger API functionalities:
```
http://localhost:8080/swagger-ui.html
```
5. Host neo4j on Azure:
```
http://[public IP]:7474/browser/
```
6. Deployed on Heroku:
```
https://skiparadise.herokuapp.com
```
## Reference
1. https://neo4j.com/developer/neo4j-cloud-azure-image/
2. https://www.callicoder.com/deploy-host-spring-boot-apps-on-heroku/
3. https://hellokoding.com/spring-boot-hello-world-example-with-jsp/
4. https://spring.io/guides/gs/spring-boot/

###Other notes:
```
STEP 1: DATA GENERATION
run App.java file
//input condition(season,day,weather) in console

STEP 2: LOAD DATA to NEO4J
(1) start neo4j console in local machine
(2) load generated file to neo4j:
a) Use Cypher script to create nodes and relationships:
load csv from <csv-file-path> as line
MERGE (s:Skier{sid: line[2]})
MERGE (p:Lift {liftName: line[0]})

load csv from <csv-file-path> as line
MATCH (s:Skier{sid: line[2]})
MATCH (p:Lift {liftName: line[0]})
MERGE (s)-[r:TAKEN{timeIn: line[3], weekday: line[5], season: line[6], weather: line[4]}]->(p)
RETURN r

b) automatic data loading: 
uncomment the last section in App.java and  edit the neo4j connection 
in SkiController and App files if needed. Run App.java will automatically create a csv and 
load it into neo4j

STEP 3: RUN SPRING BOOT WEB APP
(1) git clone the repo
(2) create maven project from existing source (the demo folder)
(3) in terminal of intellij, type command: mvn clean spring-boot:run
<!-- check the local port of 8080 to see if it is taken by other apps -->
(4) in web browser, type url: http://localhost:8080/
(5) Now, you are welcome to play with the buttons in "ski-paradise"!
```
