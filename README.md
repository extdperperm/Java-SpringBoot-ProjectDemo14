----------------------------------------------------------------------------------------------------------------------
                                           TSpringBootProjectDemo6                                                   
                                                                                                                     
                                          Autor: Daniel Pérez Pérez                                                  
                                             Fecha: 02/12/2024                                                       
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
DESCRIPCIÓN
----------------------------------------------------------------------------------------------------------------------
Ejemplo de ApiRest con modulo de seguridad Spring Security. Autenticación básica para obtener token CSRF de seguridad de
acceso al resto de métodos. 

----------------------------------------------------------------------------------------------------------------------
ESPECIFICACIÓN TÉCNICA DE DESARROLLO UTILIZADO
----------------------------------------------------------------------------------------------------------------------
Entorno de Desarrollo: Spring Boot Suite, versión: 4
Servidor de referencia: Apache Tomcat, versión: 10
Jdk: OpenJdk, versión: 17.1
Gestor de proyecto: Maven, versión: 4.0

----------------------------------------------------------------------------------------------------------------------
DEPENDENCIAS
----------------------------------------------------------------------------------------------------------------------
Spring Boot Framework: versión 3.4.0 
       - spring-boot-starter-web
       - spring-boot-starter-security
      
              
----------------------------------------------------------------------------------------------------------------------
RECOMENDACIÓN PARA ABRIR EL PROYECTO EN EL IDE: Spring Boot Suite
----------------------------------------------------------------------------------------------------------------------
1º. Copie el directorio TSpringBootProjectDemo6 en el directorio de su espacio de trabajo.
2º. Desde el IDE (Spring Boot Suite), importe el proyecto haciendo click en File -> Open Projects from File System 
3º. En Import source, haciendo click en el botón "Directory..." seleccione la carpeta que contiene el proyecto.
4º. Haga click en Finish
5º. Se recomienda realizar un Maven Update (Click derecho sobre el proyecto Maven -> Update Project...)
6º. Recompilar (salvo que tenga activado compilación automática).
7º. Ejecutar, por ejemplo haciendo click derecho sobre el proyecto -> Run As -> Java Application

----------------------------------------------------------------------------------------------------------------------
¿CÓMO PROBAR LA APIREST CON POSTMAN?
----------------------------------------------------------------------------------------------------------------------
En esta Api, primero debe autenticarse con autenticación básica en /autenticacion. Si la autenticación es válida, le
devolverá un token CSRF. Con dicho token, podrá interrogar al resto de métodos.

1º ¿Cómo probar /autenticacion?

Método: GET
Url: http://localhost:8080/autenticacion
Authorization: 
           Type: Basic Auth
                 Username: admin
                 Password: admin123

2º ¿Cómo probar /getAll?

Método: GET
Url: http://localhost:8080/getAll
Authorization: No Auth 
Headers:
       Key: X-CSRF-TOKEN
       Value: <Token devuelto en el paso 1º>


3º ¿Cómo probar /getOne?

Método: POST
Url: http://localhost:8080/getOne
Authorization: No Auth
Headers:
       Key: X-CSRF-TOKEN
       Value: <Token devuelto en el paso 1º>
Params:
     Key: nif
     Value: 44556655F

4º ¿Cómo probar /add?

Método: POST
Url: http://localhost:8080/add
Authorization: No Auth
Headers:
       Key: X-CSRF-TOKEN
       Value: <Token devuelto en el paso 1º>
Body:
     Seleccionar -> raw
     Seleccionar -> JSON
     Pegar en el editor por ejemplo: {"dni": "44556655F","nombre": "Alfredo","apellidos": "Martín Pérez","edad": 34,"promociona": true}
