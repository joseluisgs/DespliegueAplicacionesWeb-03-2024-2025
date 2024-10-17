# Despliegue de Servidores Web con Apache

- [Despliegue de Servidores Web con Apache](#despliegue-de-servidores-web-con-apache)
  - [Servidor Web](#servidor-web)
    - [Protocolo HTTP](#protocolo-http)
  - [Apache Server](#apache-server)
    - [Estructura de Directorios](#estructura-de-directorios)
    - [Comandos de Gestión](#comandos-de-gestión)
    - [Configuración Principal](#configuración-principal)
    - [Host Virtuales](#host-virtuales)
    - [Sites Available/Enables](#sites-availableenables)
    - [Dar de alta en la resolución de nombres del sistema operativo](#dar-de-alta-en-la-resolución-de-nombres-del-sistema-operativo)
    - [Despliegue con Docker](#despliegue-con-docker)
    - [Práctica Apache 01](#práctica-apache-01)
    - [Protegiendo un directorio o dominio](#protegiendo-un-directorio-o-dominio)
    - [Practica Apache 02](#practica-apache-02)
    - [Seguridad con SSL/TSL](#seguridad-con-ssltsl)
    - [Práctica Apache 03](#práctica-apache-03)

## Servidor Web
Un servidor web es un software que utiliza el protocolo HTTP para servir archivos que forman páginas web a los usuarios, en respuesta a sus solicitudes, que son reenviadas por sus aplicaciones clientes, normalmente un navegador web.

Los servidores web son componentes esenciales de la infraestructura de Internet. Todos los sitios web y aplicaciones web necesitan un servidor web para servir sus archivos a los usuarios.

Los servidores web pueden ser servidores web estáticos o servidores web dinámicos. Los servidores web estáticos sirven archivos estáticos, como páginas HTML, CSS, imágenes y otros archivos multimedia, mientras que los servidores web dinámicos generan contenido de forma dinámica, normalmente a partir de una base de datos usando un lenguaje de programación del lado del servidor, como PHP, Python, Ruby, Java, Node.js, etc.

Algunos de los servidores web más populares son:
- Apache HTTP Server: [Apache](https://httpd.apache.org/)
- Nginx: [Nginx](https://www.nginx.com/)
- Microsoft Internet Information Services (IIS): [IIS](https://www.iis.net/)

### Protocolo HTTP
El protocolo de transferencia de hipertexto (HTTP) es un protocolo de comunicación que se utiliza para la transmisión de información en la World Wide Web. HTTP define cómo se envían y reciben las solicitudes y respuestas entre los clientes y los servidores web.

HTTP es un protocolo sin estado, lo que significa que cada solicitud y respuesta es independiente de las anteriores. Cada solicitud HTTP se trata de forma independiente, sin tener en cuenta las solicitudes anteriores.

Una solicitud HTTP consta de un método (GET, POST, PUT, DELETE, etc.), una URL, una versión del protocolo, encabezados y, opcionalmente, un cuerpo de mensaje. Una respuesta HTTP consta de una versión del protocolo, un código de estado, encabezados y, opcionalmente, un cuerpo de mensaje.

Algunos de los códigos de estado HTTP más comunes son:
- 200 OK: La solicitud se ha completado correctamente.
- 404 Not Found: El recurso solicitado no se ha encontrado en el servidor.
- 500 Internal Server Error: Error interno del servidor.


## Apache Server
Apache HTTP Server, comúnmente conocido como Apache, es un servidor web de código abierto y gratuito desarrollado y mantenido por la Apache Software Foundation. Apache es uno de los servidores web más populares del mundo y se utiliza para servir una gran cantidad de sitios web y aplicaciones web.

![apache](https://upload.wikimedia.org/wikipedia/commons/1/10/Apache_HTTP_server_logo_%282019-present%29.svg)

Apache es un servidor web multiplataforma que se ejecuta en sistemas operativos Unix, Linux, Windows y otros sistemas operativos. Apache es altamente configurable y extensible, y soporta una amplia variedad de módulos y extensiones que permiten añadir funcionalidades adicionales al servidor web.

Algunas de las características de Apache son:
- Soporte para múltiples protocolos, incluyendo HTTP, HTTPS, FTP, y más.
- Soporte para múltiples lenguajes de programación, incluyendo PHP, Python, Perl, Ruby, Java, y más.
- Soporte para autenticación y autorización basada en usuarios y grupos.
- Soporte para compresión de contenido y caché de páginas web.
- Soporte para virtual hosting, que permite alojar múltiples sitios web en un solo servidor.
- Soporte para SSL/TLS, que permite cifrar la comunicación entre el servidor y los clientes.
- Soporte para módulos y extensiones que añaden funcionalidades adicionales al servidor web.
- Soporte para la configuración mediante archivos de configuración y variables de entorno.
- Soporte para la monitorización y el registro de eventos y errores.

Para instalar Apache en un sistema Linux, puedes utilizar el gestor de paquetes de tu distribución. Por ejemplo, en Ubuntu puedes instalar Apache con el siguiente comando:

```bash
sudo apt-get install apache2 # servidor web
sudo apt-get install apache2-utils # utilidades de apache
```

### Estructura de Directorios
La configuración de Apache está distribuida en varios archivos y directorios para facilitar su gestión y modularidad. La estructura es la siguiente:
```
/etc/apache2/
|-- apache2.conf
|   `-- ports.conf
|-- mods-enabled
|   |-- *.load
|   |-- *.conf
|-- conf-enabled
|   `-- *.conf
`-- sites-enabled
    `-- *.conf
```

- apache2.conf: Archivo de configuración principal de Apache donde se realizan cambios generales.
- envvars: Configuración de las variables de entorno.
- ports.conf: Configuración de los puertos en los que Apache escucha.
- conf-available: Ficheros de configuración adicionales para varios aspectos de Apache o aplicaciones web.
- conf-enabled: Enlaces simbólicos a los ficheros de configuración adicionales para activarlos. Se gestionan con los comandos a2enconf y a2disconf.
- mods-available: Módulos disponibles para usar con Apache.
- mods-enabled: Enlaces simbólicos a los módulos de Apache que están activados.
- sites-available: Ficheros de configuración de hosts virtuales disponibles. Se gestionan con los comandos a2enmod y a2dismod.
- sites-enabled: Enlaces simbólicos a los ficheros de configuración de hosts virtuales activos. Se gestionan con los comandos a2ensite y a2dissite.

### Comandos de Gestión
Comprobar el estado del servicio:
  
  ```bash
  sudo service apache2 status
  ```
Detener el servidor:
  
  ```bash
  sudo service apache2 stop
  ```
Iniciar el servicio:
  
  ```bash
  sudo service apache2 start
  ```
Reiniciar el servidor:
  
  ```bash
  sudo service apache2 restart
  ```

Obtener información del servidor:
  
  ```bash
  apache2 -v
  ```

### Configuración Principal
apache2.conf:

Archivo principal de configuración con directivas generales.
Incluye configuraciones como:

```apache	
<Directory /var/www/>
    Options Indexes FollowSymlinks
    AllowOverride None
    Require all granted
</Directory>
AccessFileName .htaccess # nombre del archivo de configuración
Include ports.conf # incluye el archivo de configuración de puertos
KeepAlive On # mantiene la conexión abierta
```
ports.conf:

Configura los puertos en los que Apache escucha.
Ejemplo de configuración:
  
  ```apache
  Listen 80 # puerto por defecto

<IfModule ssl_module>
	Listen 443
</IfModule>

<IfModule mod_gnutls.c>
	Listen 443
</IfModule>
  ```

### Host Virtuales
Los hosts virtuales permiten alojar múltiples sitios web en un solo servidor. Cada host virtual tiene su propio directorio raíz, configuración y archivos de registro.

Por defecto, Apache tiene un host virtual configurado en /etc/apache2/sites-available/000-default.conf. Puedes añadir nuevos hosts virtuales creando archivos de configuración en el directorio /etc/apache2/sites-available/ y activándolos con los comandos a2ensite y a2dissite.

La estructura de un archivo de configuración de host virtual es la siguiente:

```apache
<VirtualHost *:80>
    ServerAdmin joseluis.gonzalez@iesluisvives.org # dirección de correo del administrador
    DocumentRoot /var/www/dominio.com # directorio raíz del sitio web
    ServerName dominio.com # nombre del servidor
    ServerAlias www.dominio.com # alias del servidor

    
    #LogLevel info ssl:warn
 
    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined


    <Directory /var/www/dominio.com> 
      Options Indexes FollowSymLinks # opciones del directorio
      AllowOverride All # habilita la reescritura de URL
      Require all granted # permisos de acceso
    </Directory>

</VirtualHost>
``` 

### Sites Available/Enables
- sites-available: Directorio que contiene los archivos de configuración de los hosts virtuales disponibles.
- sites-enabled: Directorio que contiene enlaces simbólicos a los archivos de configuración de los hosts virtuales activos.

Para activar un host virtual, puedes crear un archivo de configuración en sites-available y activarlo con el comando a2ensite. Por ejemplo:

```bash
sudo cp /etc/apache2/sites-available/000-default.conf /etc/apache2/sites-available/mi-sitio.conf
sudo a2ensite mi-sitio.conf
sudo service apache2 reload
```

Para desactivar un host virtual, puedes usar el comando a2dissite. Por ejemplo:

```bash
sudo a2dissite mi-sitio.conf
sudo service apache2 reload
```

### Dar de alta en la resolución de nombres del sistema operativo
Para que el servidor web pueda ser accedido por un nombre de dominio en lugar de una dirección IP, es necesario dar de alta el nombre en la resolución de nombres del sistema operativo.

En Linux lo haremos en el archivo /etc/hosts. Por ejemplo, si queremos acceder al servidor web con el nombre dominio.com, añadiremos la siguiente línea al archivo /etc/hosts:

```bash
127.0.0.1 dominio.com
```

En Windows, el archivo de hosts se encuentra en C:\Windows\System32\drivers\etc\hosts. Para editarlo, necesitarás permisos de administrador.
```bash
127.0.0.1 dominio.com
```

### Despliegue con Docker 
Imaginemos que queremos desplegar un servidor web Apache en un contenedor Docker con dos dominios virtuales llamado dominio-one.com y dominio-two.com usando Docker-Compose. 

En nuestro directorio de trabajo podemos tener los siguientes archivos de configuración:
- docker-compose.yml: Archivo de configuración de Docker-Compose.
- sites-available: Directorio con los archivos de configuración de los hosts virtuales.
  - dominio-one.conf: Archivo de configuración del host virtual dominio-one.com.
  - dominio-two.conf: Archivo de configuración del host virtual dominio-two.com.
- websites: Directorio con los archivos de los sitios web.
  - dominio-one: Directorio con los archivos del sitio web dominio-one.com.
  - dominio-two: Directorio con los archivos del sitio web dominio-two.com.
- apache2.conf: Archivo de configuración principal de Apache.
- ports.conf: Archivo de configuración de los puertos de Apache.

De esta manera un ejemplo de docker-compose.yml sería:

```yaml
services:
  web:
    image: ubuntu/apache2 # imagen de Apache
    container_name: apache_server # nombre del contenedor
    ports:
      - "8080:80" # mapeo de puertos
    volumes:
      - ./apache2.conf:/etc/apache2/apache2.conf # archivo de configuración principal
      - ./sites-available:/etc/apache2/sites-available # archivos de configuración de hosts virtuales
      - ./websites:/var/www/html/ # directorio de los sitios web
    restart: always # reinicio automático
    # comando para activar los hosts virtuales y arrancar Apache
  
```

Ahora solo tendríamos que ejecutar el comando docker-compose up -d para desplegar el servidor web Apache en un contenedor Docker.

```bash
docker-compose up -d
```

Añade estas entradas al archivo /etc/hosts:

```bash
127.0.0.1 dominio-one.com
127.0.0.1 dominio-two.com
```

Ahora entramos al contenedor y activamos los dominios virtuales y reiniciamos apache
  
  ```bash
  docker exec -it apache_server bash
  a2ensite dominio-one.com.conf
  a2ensite dominio-two.com.conf
  service apache2 reload
  ```

Para agilizar el proceso, en el ejemplo apache-init, se ha hecho un script que automatiza la activación de los dominios virtuales y el reinicio de Apache y añadido este script al archivo de configuración de Docker-Compose como su entrypoint.

```bash
#!/bin/bash
#!/bin/bash

# Habilitar los sitios
a2ensite dominio-one.com
a2ensite dominio-two.com

# Recargar la configuración de Apache
service apache2 reload

# Iniciar Apache en primer plano
apache2ctl -D FOREGROUND
```

Y acceder a los sitios web dominio-one.com y dominio-two.com en el navegador web. ¡OJO con los puertos! quizás debas usarlos, por ejemplo http://dominio-one.com:8080 según lo que hayas configurado.


### Práctica Apache 01
Crear dos paginas web en dos dominios tunombre.net (por ejemplo pepeperez.net) y tunombre.org (por ejemplo pepeperez.org) con un mensaje de bienvenida en cada una de ellas. Desplegar un servidor web Apache en un contenedor Docker con dos dominios virtuales usando Docker-Compose. 

### Protegiendo un directorio o dominio
Podemos proteger un dominio o directorio de un dominio gracias a Apache. Para ello debemos ejecutar el comando:
  
  ```bash
  htpasswd -c /etc/apache2/.htpasswd usuario
  ```
Este comando genera el archivo .htpasswd en el directorio /etc/apache2/ y añade el usuario usuario con su contraseña. Para añadir más usuarios, ejecutamos el comando sin la opción -c.

A partir de aquí podemos proteger un directorio o dominio añadiendo las siguientes líneas al archivo de configuración del host virtual:

```apache
<Directory /var/www/dominio.com/privado>
    AuthType Basic
    AuthName "Área restringida"
    AuthUserFile /etc/apache2/.htpasswd
    Require valid-user
</Directory>
```

De manera global, podemos hacerlo añadiendo las siguientes líneas al archivo de configuración principal de Apache:

```apache
<Directory /var/www/>
    Options Indexes FollowSymLinks
    AllowOverride All
    Require all granted
</Directory>
```
Y luego teniendo un archivo .htaccess en el directorio que queremos proteger con el siguiente contenido:

```apache
AuthType Basic
AuthName "Área restringida"
AuthUserFile /etc/apache2/.htpasswd
Require valid-user
```

Puedes personalizar el mensaje de error 404 y otras añadiendo las siguientes líneas al archivo de configuración del host virtual:

```apache
ErrorDocument 404 /error404.html
```

Reiniciamos y listo. Puedes ver el ejemplo en la carpeta apache-htpasswd.

### Practica Apache 02

Crea un directorio privado en el dominio tunombre.org (por ejemplo pepeperez.org) y protegelo con una contraseña, por ejemplo pepeperez. Despliega un servidor web Apache en un contenedor Docker con dos dominios virtuales usando Docker-Compose.
Además añade páginas personalizadas de error 404 y 500.

### Seguridad con SSL/TSL

Lo primero que debemos hacer es generar un certificado con OpenSSL. Puedes instalarlo en tu sistema con el siguiente comando:

```bash
sudo apt-get install openssl
```

O en Windows desde la página oficial de [Win32OpenSSL](https://slproweb.com/products/Win32OpenSSL.html).

Para ello ejecutamos el siguiente comando en un directorio llamado certs:

```bash
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout dominio-two.key -out dominio-two.crt
```

El siguiente paso es ajustar la seguridad de nuestro Virtual Host añadiendo las siguientes líneas al archivo de configuración del host virtual:

```apache
<VirtualHost *:80>
    # Redireccion de todo lo 80 a 443
    ServerName dominio-two.com
    ServerAlias www.dominio-two.com
    Redirect / https://dominio-two.com/
</VirtualHost>

<VirtualHost *:443>
    ServerAdmin joseluis.gonzalez@iesluisvives.org
    DocumentRoot /var/www/html/dominio-two.com
    ServerName dominio-two.com
    ServerAlias www.dominio-two.com

    # Configuración SSL
    SSLEngine On
    SSLCertificateFile /etc/apache2/certs/dominio-two.crt
    SSLCertificateKeyFile /etc/apache2/certs/dominio-two.key 

    # Habilitar protocolos seguros
    SSLProtocol All -SSLv3

    # Protección de directorio
    <Directory "/var/www/html/dominio-two.com/privado">
        AuthType Basic
        AuthName "Acceso Restringido a Usuarios"
        AuthUserFile /etc/apache2/.htpasswd
        Require valid-user
        Options -Indexes
    </Directory>
</VirtualHost>
```

Debemos copiar los archivos dominio-two.crt y dominio-two.key al directorio /etc/apache2/certs y reiniciar el servidor Apache.

Posteriormente debemos instalar el módulo SSL de Apache:

```bash
sudo a2enmod ssl
```
Y reiniciar el servidor Apache:

```bash
sudo service apache2 restart
```

No olvides en docker-compose.yml mapear el puerto 443:

Se ha automatizado el proceso en el archivo entrypoint.sh.

Puedes ver un ejemplo en la carpeta apache-ssl.

### Práctica Apache 03
Configura el dominio tunombre.org (por ejemplo pepeperez.org) con SSL/TSL. Despliega un servidor web Apache en un contenedor Docker con dos dominios virtuales usando Docker-Compose. 




