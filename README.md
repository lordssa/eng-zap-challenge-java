# eng-zap-challenge-java
API Rest Grupo Zap

### Prerequisites

* [Java 8](https://www.java.com/pt_BR/download/) - Development Kit 
* [Spring Tools 4 for Eclipse](https://spring.io/tools) - IDE for Development
* [Git](https://git-scm.com/downloads) - Open source distributed version control system

### Installing

Checkout the code from Github repository inside Eclipse Workspace 
```
$ git clone https://github.com/lordssa/eng-zap-challenge-java.git
```

After acquire the project, open the Eclipse and select the option "import existing Maven projects" in "File > Import"

## Running the project

Expand the folders src/main/java/zap/api/resources and execute BuildingResource.java. After server up, the api will be available in http://localhost at 8080

### Accessing documentation and contract

With server up, acess http://localhost:8080/swagger-ui.html. On the section building-resource contains a list of API services, parameters and output sample. 

### Trying out

There is two modes, the first one is acessing the service by url

```
*Example*
http://localhost:8080/api/listarImoveisZap?pageNumber=1&pageSize=20
```
Second option is through Swagger.
```
*Example*
On the section building-resource, select method GET "/api/listarImoveisZap" and click "try it out" button, fill the required parameters, click in execute button and that's it!
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Cid Soares** - *Initial work* 


