|   Fecha    |      Descripción       |     Realizado por      |
|:----------:|:----------------------:|:----------------------:|
| 27/02/2025 | Creación del Documento | Sebastian Medina Ochoa |

# Documentación microservicio

## 1. Información general

### 1.1 Nombre de la API

* **Nombre:** ms-database-orchestrator-webflux
* **Clasificación del servicio:** Orquestador de peticiones hacia la base de datos.
* **Versión actual:** v1.0.0
* **Propietario:** Sebastian Medina Ochoa

### 1.2 Propósito de la API

* **Descripción:** Servicio que permite la orquestación, escritura y lectura que se hace hacia la base de datos.
* **Objetivos del servicio:** Poder realizar orquestación, escritura y lectura de cualquier tabla de la base de datos.
* **Usuarios Destinatarios:** Servicios que requieran realizar escritura o lectura de la base de datos.

### 1.3 Detalles de Campos en la Petición y Respuesta

Esta sección describe los campos que se utilizan en las peticiones y respuestas de la API.

### Headers

| Nombre del Campo | Descripción                    | Tipo de Dato | Dato Obligatorio | Valores Permitidos | Observaciones                                                                                                                 |
|:-----------------|:-------------------------------|:-------------|:-----------------|:-------------------|:------------------------------------------------------------------------------------------------------------------------------|
| `messageId`      | Identificador unico del flujo. | String       | SI               | VALORES UUID       | La idea es que cada petición tenga un messageId distinto para poder indentificarlo en los logs en el soporte de la aplicación |

### Path Variable

| Nombre del Campo | Descripción                         | Tipo de Dato | Dato Obligatorio | Valores Permitidos | Observaciones                                                              |
|:-----------------|:------------------------------------|:-------------|:-----------------|:-------------------|:---------------------------------------------------------------------------|
| `productId`      | Identificador de un producto unico. | String       | SI               | NUMEROS            | Este idenfiticador unico permitirá recuperar el/los producto/s a consultar |

## 2. Endpoints

### 2.1 Lista de Endpoints

| Método | Endpoint                          | Descripción                                                         | 
|:-------|:----------------------------------|:--------------------------------------------------------------------|
| `GET`  | "/product/{productId}/similarids" | Devuelve los productos similares a uno dado ordenados por similitud |
| `GET`  | "/product/{productId}"            | Devuelve el detalle del producto para un productId determinado      |

### 2.2 Detalles del Endpoint

#### **GET /product/{productId}/similarids**

* **Descripción:** "Devuelve los productos similares a uno dado ordenados por similitud"
* **Autenticación Requerida:** "No"
* **Cuerpo de la Solicitud (Request Body):**
  ```json lines
        curl --location 'http://localhost:5000/product/{productId}/similarids' --header 'messageId: 123'
  ```

* **Respuestas:**
* **Código 200:** "Ok":
    ```json lines
      [
        2,
        3,
        4
      ]
  ```
* **Código 400:** "Bad Request":
  ```json lines
     {
      "status": "400",
      "message": "El header 'messageId' es requerido"
     }
  ```
* **Código 404:** "Not Found":
  ```json lines
     {
       "status": "404",
       "message": "No se encontró ningun regristro bajo ese productId"
     }
  ```

#### **GET /product/{productId}**

* **Descripción:** "Devuelve el detalle del producto para un productId determinado"
* **Autenticación Requerida:** "No"
* **Cuerpo de la Solicitud (Request Body):**
  ```json lines
        curl --location 'http://localhost:5000/product/{productId}' --header 'messageId: 123'
  ```

* **Respuestas:**
* **Código 200:** "Ok":
     ```json lines
         {
             "id": "string",
             "name": "string",
             "price": 0,
             "availability": true
         }
  ```     
* **Código 400:** "Bad Request":
  ```json lines
     {
      "status": "400",
      "message": "El header 'messageId' es requerido"
     }
  ```

* **Código 404:** "Not Found":
  ```json lines
     {
       "status": "404",
       "message": "No se encontró ningun regristro bajo ese productId"
     }
  ```

---

### 2.3 Validación de Datos

Para asegurar la integridad de los datos recibidos y enviados a través de las APIs, se deben realizar validaciones en
todos los campos de las solicitudes y respuestas. A continuación se detallan algunos ejemplos de validaciones:

1. **Campo `productId`:**

    * **Tipo de Dato:** cadena `String`.
    * **Validación:** No debe ser un valor nulo ni estar vacío.

2. **Campo `messageId`:**

    * **Tipo de Dato:** cadena `String`.
    * **Validación:** No debe ser un valor nulo ni estar vacío.

## 3. Contacto y Soporte

### 3.1. Información de Contacto

* **Email de Soporte:** "sebasthyy1@gmail.com"

#### Acceso a la Colección

* Puedes acceder a la colección de Postman utilizando el siguiente enlace:

  * [Url de la collections](https://drive.google.com/drive/folders/1MT9f2KIoiqiCcl8TS_Igbrk4-_WgvFUS?usp=sharing)
  * En el mismo enlace tambien se encuentra un archivo con datos de prueba llamado __datos-prueba.txt__ con los que podras mandar por el productId de los endpoints

## 4. Correr el Microservicio

### 4.1. Requisitos Previos

* **Herramientas necesarias**
    * **Gradle:** Para la construcción del microservicio.
    * **Docker y Docker-Compose:** Para la orquestación de contenedores.

### 4.1.1 Gradle

* **Recomendaciones**
    * **Version:** Tener instalada la versión de gradle 8.13. (recomendable)
* **Instalación**
    * **Guia:** Instalar gradle según su pagina oficial: [Pagina Oficial de Gradle](https://gradle.org/install/)

### 4.1.2 Docker

* **Recomendaciones**
    * **Version:** Tener instalada la versión __Docker version 27.5.1__ (recomendable)
* **Instalación**
    * **Guia:** Instalar Docker Desktop según su pagina
      oficial: [Pagina Oficial de Docker](https://docs.docker.com/get-started/get-docker/)

### 4.1.3 Docker Compose

* **Recomendaciones**
    * **Version:** Tener instalada la versión __Docker Compose version v2.32.4-desktop.1__ (recomendable)
* **Instalación**
    * **Guia:** Una vez instalado Docker Desktop, Docker Compose viene instalada, pero si no, puedes instalarlo con este
      comando en Linux (Ubuntu/Debian)
  * **Comando:**

    ~~~
    sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    ~~~

### 4.2. Construcción del Microservicio

* **Descripción:** Para construir el microservicio desde la raíz del proyecto.
  * **Comando:**

      ~~~
      gradle build
      ~~~

* **Observaciones:**
    * El comando genera los artefactos necesarios para la ejecución del servicio.

### 4.3. Levantar los Contenedores

* **Descripción:** Para levantar los contenedores definidos en el archivo __docker-compose.yml.__
  * **Comando:**

    ~~~
    docker-compose up -d simulado influxdb grafana app
    ~~~

* **Observaciones:**
    * Este comando se debe ejecutar en el directorio donde se encuentra el archivo __docker-compose.yml.__
    * Se inician contenedores para los servicios de prueba (simulado), base de datos (influxdb), monitoreo (grafana) y
      la aplicación (app).

### 4.4. Correr las Pruebas con K6

* **Descripción:** Para ejecutar las pruebas de performance/estrés definidas en el archivo __scripts/test.js.__
  * **Comando:**

    ~~~
    docker-compose run --rm k6 run scripts/test.js
    ~~~

* **Observaciones:**
    * Verifica el correcto funcionamiento y rendimiento del microservicio.
    * El contenedor k6 está definido en el docker-compose.yml y toma el script de pruebas localizado en la carpeta
      shared/k6/.

### 4.5. Pruebas de la API

* **Postman:**
    * Puedes utilizar la colección disponible en la sección de Acceso a la colección o directamente en
      este [enlace](https://drive.google.com/drive/folders/1MT9f2KIoiqiCcl8TS_Igbrk4-_WgvFUS?usp=sharing) para importar
      y probar los endpoints.
    * Asegúrate de incluir el header messageId en cada petición.
    * En el mismo enlace tambien se encuentra un archivo con datos de prueba llamado __datos-prueba.txt__ con los que podras mandar por el productId de los endpoints
* **Swagger:**
    * Una vez levantado el microservicio y los contenedores, ingresa a la siguiente URL desde tu navegador:

    ~~~
    http://localhost:5000/webjars/swagger-ui/index.html 
    ~~~

* En esta interfaz podrás probar cada endpoint de manera interactiva, viendo tanto los parámetros de entrada como las
  respuestas generadas por el microservicio.

Sebastian Medina Ochoa © 2025


