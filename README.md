|   Date     |    Description     |        Made by         |
|:----------:|:------------------:|:----------------------:|
| 22/06/2025 | Document Creation  | Sebastian Medina Ochoa |

# Microservice documentation

## 1. General information

### 1.1 API Name

* **Name:** ms-price-reader
* **Service classification:** This microservice allows you to check the applicable price of a product based on established parameters.
* **Current version:** v1.0.0
* **Owner:** Sebastian Medina Ochoa

### 1.2 Purpose of the API

* **Description:** Service that allows reading to be done towards the database.
* **Service objectives:** Be able to read the price table from the database.
* **Target Users:** Services that require reading the database to the price table.

### 1.3 Details of Fields in the Request and Response

This section describes the fields used in API requests and responses.

### Headers

| Field Name  | Description             | Data Type  | Mandatory Data  | Allowed Values  | Observations                                                                                                          |
|:------------|:------------------------|:-----------|:----------------|:----------------|:----------------------------------------------------------------------------------------------------------------------|
| `flowId`    | Unique flow identifier  | String     | SI              | UUID VALUES     | The idea is that each request has a different flowId to be able to identify it in the logs in the application support |

## 2. Endpoints

### 2.1 Endpoint List

| Method | Endpoint                                                                    | Description                                                                                                                         | 
|:-------|:----------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------|
| `GET`  | "/price/v1/get?applicationDate={value}&productId={value}&brandId={value}"   | Obtains the price that applies to a given combination of product, brand, and date, considering the priority among available rates.  |

### 2.2 Validación de Datos

Para asegurar la integridad de los datos recibidos y enviados a través de las APIs, se deben realizar validaciones en
todos los campos de las solicitudes y respuestas. A continuación se detallan algunos ejemplos de validaciones:

1. **Campo `applicationDate`:**

    * **Tipo de Dato:** LocalDateTime `LocalDateTime`.
    * **Validación:** No debe ser un valor nulo ni estar vacío y no puede tener un formato incorrecto.

2. **Campo `productId`:**

    * **Tipo de Dato:** Integer `Integer`.
    * **Validación:** No debe ser un valor nulo ni estar vacío y no puede tener un formato incorrecto.

3. **Campo `brandId`:**

    * **Tipo de Dato:** Integer `Integer`.
    * **Validación:** No debe ser un valor nulo ni estar vacío y no puede tener un formato incorrecto.

4. **Campo `flowId`:**

    * **Tipo de Dato:** String `String`.
    * **Validación:** No debe ser un valor nulo ni estar vacío y no puede tener un formato incorrecto.

## 3. Contacto y Soporte

### 3.1. Información de Contacto

* **Email de Soporte:** "sebasthyy1@gmail.com"

## 4. Correr el Microservicio

### 4.1. Requisitos Previos

* **Herramientas necesarias**
    * **Maven:** Para la construcción del microservicio sin Docker.
    * **Docker:** Para la construir el proyecto con una imagen y contenedor.
    * **Java:** Para interpretar el lenguaje en el que esta desarrollado el microservicio.

### 4.1.1 Maven

* **Recomendaciones**
    * **Version:** Tener instalada la versión de maven  3.9.9 (recomendable)

### 4.1.2 Docker

* **Recomendaciones**
    * **Version:** Tener instalada la versión __Docker version 27.5.1__ (recomendable)

### 4.1.3 Java

* **Recomendaciones**
    * **Version:** Tener instalada la versión __Java 21.0.5__ (recomendable)

### 4.2. Construcción del Microservicio Sin Docker

* **Descripción:** Para construir el microservicio desde la raíz del proyecto.
  * **Comando:**

      ~~~
      mvn spring-boot:run
      ~~~

* **Observaciones:**
    * El comando genera los artefactos necesarios para la ejecución del servicio y para correr el microservicio sin docker se necesita tener instalada las demas herramientas, como Java y Maven.

### 4.3. Construcción del Microservicio Con Docker 

* **Descripción:** Para contruir la imagen con Docker se tiene que lanzar el siguiente comando
  * **Comando:**

    ~~~
    docker build -t ms-price-reader .
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __Dockerfile__

### 4.3.1 Ejecutar el contenedor

* **Descripción:** Para ejecutar el contenedor con la imagen que creamos anteriormente se debe de lanzar el siguiente comando
  * **Comando:**

    ~~~
     docker run -p 8080:8080 --name price-service ms-price-reader
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __Dockerfile__
    * Este comando te hará ver tambien los logs en tiempo de ejecución para saber que sucede dentro del microservicio con cada petición lanzada.

### 4.3.2 Ver los logs en tiempo de ejecución

* **Descripción:** En el caso que te hayas salido de la terminal donde esta viendo los logs del microservicio puedes volver a entrar a verlos con el siguiente comando
  * **Comando:**

    ~~~
     docker logs -f price-service
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __Dockerfile__
    * Este comando te hará ver todos los logs en tiempo de ejecución para saber que sucede dentro del microservicio con cada petición lanzada.


### 4.3.3 Pausar el contenedor creado

* **Descripción:** Si ya se ha terminado de realizar las pruebas pertinentes o finalizar la utilización del microservicio se puede pausar el contenedor con el siguiente comando
  * **Comando:**

    ~~~
     docker stop price-service 
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __Dockerfile__
    * Este comando te hará pausar el contenedor que hemos creado anteriormente llamado __price-service__.

### 4.3.4 Eliminar el contenedor creado

* **Descripción:** Si ya se ha terminado de realizar las pruebas pertinentes o finalizar la utilización del microservicio se puede eliminar el contenedor con el siguiente comando
  * **Comando:**

    ~~~
     docker rm price-service  
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __Dockerfile__
    * Este comando te hará eliminar el contenedor que hemos creado anteriormente llamado __price-service__.
    * Este comando se tiene que ejecutar despues de haber pausado el contenedor llamado __price-service__.

### 4.3.5 Eliminar la imagen creada

* **Descripción:** Si ya se ha terminado de realizar las pruebas pertinentes o finalizar la utilización del microservicio se puede eliminar la imagen con el siguiente comando
  * **Comando:**

    ~~~
     docker rmi ms-price-reader 
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __Dockerfile__
    * Este comando te hará eliminar la imagen que hemos creado anteriormente llamada __ms-price-reader__.
    * Este comando se tiene que ejecutar despues de haber eliminado el contenedor llamado __price-service__.

### 4.5. Pruebas de la API

* **Test Unitarios:**
    * Puedes ejecutar los test unitarios creados de todo el microservicio con el siguiente comando
      * **Comando:**

        ~~~
         mvn clean test 
        ~~~
        
    * Puedes ejecutar los test unitarios creados de todo el microservicio desde el ejecutador de la herramienta en la que compilas el proyecto puedes hacer lo siguiente
    * Ejemplo
      * ![img.png](images/testUnits.png)


* **Postman:**
    * Puedes acceder a la colección de Postman abriendo la carpeta que esta en la raiz de este proyecto llamada __collection__:
      * ![img.png](images/collection.png)
    * Dentro de la collection tendras las 5 causisticas seleccionadas para probar desde la herramienta
      * ![img.png](images/postmanTest.png)


* **Swagger:**
    * Una vez levantado el microservicio con o sin Docker, ingresa a la siguiente URL desde tu navegador para ver la documentación con Swagger-UI:

    ~~~
    http://localhost:8080/price/v1/swagger-ui/index.html 
    ~~~

    * Aquí podrás ver todo la documentación sobre los endpoints, request, objetos de respuesta, headers o parametros. Esta documentación esta con springdoc así que veras la descripción de todos los campos u objetos que son, para que sirven o los ejemplos de datos que puedes enviar
    * Aquí podrás modificar los datos a tu parecer para verificar la funcionalidad o si se desea obtener información distinta de la base de datos
    * Tambien puedes ver el yml generado por spring doc desde la carpeta que esta ubicada en la raiz de este proyecto llamada __api-contracts__
      * ![img.png](images/swaggerYml.png)

Sebastian Medina Ochoa © 2025
