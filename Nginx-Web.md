# Despliegue de Servidores Web Nginx

- [Despliegue de Servidores Web Nginx](#despliegue-de-servidores-web-nginx)
  - [Nginx Server](#nginx-server)
    - [Estructura de Directorios](#estructura-de-directorios)
    - [Comandos de Gestión](#comandos-de-gestión)
    - [Configuración Principal](#configuración-principal)
    - [Host Virtuales](#host-virtuales)
    - [Sites Available/Enables](#sites-availableenables)
    - [Dar de alta en la resolución de nombres del sistema operativo](#dar-de-alta-en-la-resolución-de-nombres-del-sistema-operativo)
    - [Despliegue con Docker](#despliegue-con-docker)
    - [Práctica Nginx 01](#práctica-nginx-01)
    - [Protegiendo un directorio o dominio](#protegiendo-un-directorio-o-dominio)
    - [Practica Nginx 02](#practica-nginx-02)
    - [Seguridad con SSL/TSL](#seguridad-con-ssltsl)
    - [Práctica Nginx 03](#práctica-nginx-03)


## Nginx Server
Nginx es un servidor web de código abierto y gratuito desarrollado por Igor Sysoev y mantenido por Nginx, Inc. Es conocido por su alto rendimiento, estabilidad y bajo consumo de recursos, y se utiliza para servir una gran cantidad de sitios web y aplicaciones web en todo el mundo.

![nginx](https://upload.wikimedia.org/wikipedia/commons/c/c5/Nginx_logo.svg)

Nginx es un servidor web multiplataforma que se ejecuta en sistemas operativos Unix, Linux, Windows y otros. Es altamente configurable y extensible, y soporta una variedad de módulos que permiten añadir funcionalidades adicionales.

Algunas de las características de Nginx son:

Soporte para múltiples protocolos, incluyendo HTTP, HTTPS, y más.
Soporte para múltiples lenguajes de programación a través de FastCGI, SCGI, uWSGI, y más.
Soporte para autenticación y autorización.
Soporte para compresión de contenido y caché de páginas web.
Soporte para virtual hosting, permitiendo alojar múltiples sitios web en un solo servidor.
Soporte para SSL/TLS, cifrando la comunicación entre el servidor y los clientes.
Soporte para la configuración mediante archivos de configuración.
Soporte para la monitorización y el registro de eventos y errores.
Para instalar Nginx en un sistema Linux, puedes utilizar el gestor de paquetes de tu distribución. Por ejemplo, en Ubuntu puedes instalar Nginx con el siguiente comando:


```bash
sudo apt-get install nginx

```

### Estructura de Directorios
La configuración de Nginx está distribuida en varios archivos y directorios para facilitar su gestión y modularidad. La estructura es la siguiente:


```
/etc/nginx/
|-- nginx.conf
|-- conf.d/
|   `-- *.conf
|-- sites-available/
|   `-- *.conf
`-- sites-enabled/
    `-- *.conf
```

### Comandos de Gestión

Comprobar el estado del servicio:

```bash
sudo service nginx status
```

Detener el servidor:
```bash
sudo service nginx stop
```
Iniciar el servicio:

```bash
sudo service nginx start
```

Reiniciar el servidor:
```bash
sudo service nginx restart
```

Obtener información del servidor:

```
nginx -v
```

### Configuración Principal
nginx.conf:

Archivo principal de configuración con directivas generales.
Incluye configuraciones como:

```nginx	
user www-data;
worker_processes auto;
pid /run/nginx.pid;
error_log /var/log/nginx/error.log;
include /etc/nginx/modules-enabled/*.conf;

events {
	worker_connections 768;
	# multi_accept on;
}

http {

	##
	# Basic Settings
	##

	sendfile on;
	tcp_nopush on;
	types_hash_max_size 2048;
	# server_tokens off;

	# server_names_hash_bucket_size 64;
	# server_name_in_redirect off;

	include /etc/nginx/mime.types;
	default_type application/octet-stream;

	##
	# SSL Settings
	##

	ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3; # Dropping SSLv3, ref: POODLE
	ssl_prefer_server_ciphers on;

	##
	# Logging Settings
	##

	access_log /var/log/nginx/access.log;

	##
	# Gzip Settings
	##

	gzip on;

	# gzip_vary on;
	# gzip_proxied any;
	# gzip_comp_level 6;
	# gzip_buffers 16 8k;
	# gzip_http_version 1.1;
	# gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript;

	##
	# Virtual Host Configs
	##

	include /etc/nginx/conf.d/*.conf;
	include /etc/nginx/sites-enabled/*;
}


#mail {
#	# See sample authentication script at:
#	# http://wiki.nginx.org/ImapAuthenticateWithApachePhpScript
#
#	# auth_http localhost/auth.php;
#	# pop3_capabilities "TOP" "USER";
#	# imap_capabilities "IMAP4rev1" "UIDPLUS";
#
#	server {
#		listen     localhost:110;
#		protocol   pop3;
#		proxy      on;
#	}
#
#	server {
#		listen     localhost:143;
#		protocol   imap;
#		proxy      on;
#	}
#}
```

### Host Virtuales
Los hosts virtuales permiten alojar múltiples sitios web en un solo servidor. Cada host virtual tiene su propio directorio raíz, configuración y archivos de registro.

Por defecto, Nginx tiene un host virtual configurado en /etc/nginx/sites-available/dominio.com. Puedes añadir nuevos hosts virtuales creando archivos de configuración en este directorio.
La estructura de un archivo de configuración de host virtual es la siguiente:

```nginx
server {
    listen 80;
    server_name dominio.com www.dominio.com;

    root /var/www/dominio.com;
    index index.html index.htm;

    location / {
        try_files $uri $uri/ =404;
    }

    error_log /var/log/nginx/dominio.com_error.log;
    access_log /var/log/nginx/dominio.com_access.log;
}

``` 

### Sites Available/Enables
- sites-available: Directorio que contiene los archivos de configuración de los hosts virtuales disponibles.
- sites-enabled: Directorio que contiene enlaces simbólicos a los archivos de configuración de los hosts virtuales activos.

Para activar un host virtual, puedes crear un archivo de configuración en sites-available y activarlo creando un enlace

```bash
sudo ln -s /etc/nginx/sites-available/mi-sitio.conf /etc/nginx/sites-enabled/
sudo service nginx reload
```

Para desactivar un host virtual, hacemos:

```bash
sudo rm /etc/nginx/sites-enabled/mi-sitio.conf
sudo service nginx reload
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
Imaginemos que queremos desplegar un servidor web Nginx en un contenedor Docker con dos dominios virtuales llamado dominio-one.com y dominio-two.com usando Docker-Compose. 

En nuestro directorio de trabajo podemos tener los siguientes archivos de configuración:
- docker-compose.yml: Archivo de configuración de Docker-Compose.
- sites-available: Directorio con los archivos de configuración de los hosts virtuales.
  - dominio-one.conf: Archivo de configuración del host virtual dominio-one.com.
  - dominio-two.conf: Archivo de configuración del host virtual dominio-two.com.
- websites: Directorio con los archivos de los sitios web.
  - dominio-one: Directorio con los archivos del sitio web dominio-one.com.
  - dominio-two: Directorio con los archivos del sitio web dominio-two.com.
- nginx.conf: Archivo de configuración principal de Nginx.


De esta manera un ejemplo de docker-compose.yml sería:

```yaml
services:
  web:
    image: ubuntu/nginx # imagen de Nginx
    container_name: nginx_server # nombre del contenedor
    ports:
      - "80:80" # mapeo de puertos  HTTP
      - "443:443" # mapeo de puertos HTTPS (solo si es necesario)
    volumes:
      - ./conf/nginx.conf:/etc/nginx/nginx.conf # archivo de configuración principal (si lo cambias)
      - ./sites-available:/etc/nginx/sites-available # archivos de configuración de hosts virtuales
      - ./website:/var/www/html/ # directorio de los sitios web
    restart: always # reinicio automático
  
```

Ahora solo tendríamos que ejecutar el comando docker-compose up -d para desplegar el servidor web Nginx en un contenedor Docker.

```bash
docker-compose up -d
```

Añade estas entradas al archivo /etc/hosts:

```bash
127.0.0.1 dominio-one.com
127.0.0.1 dominio-two.com
```

Ahora entramos al contenedor y activamos los dominios virtuales y reiniciamos Nginx:
  
  ```bash
  docker exec -it nginx_server bash
  sudo ln -s /etc/nginx/sites-available/dominio-one.conf /etc/nginx/sites-enabled/
  sudo ln -s /etc/nginx/sites-available/dominio-two.conf /etc/nginx/sites-enabled/
  service nginx reload
  ```

Para agilizar el proceso, en el ejemplo nginx-init, se ha hecho un script que automatiza la activación de los dominios virtuales y el reinicio de Nginx y añadido este script al archivo de configuración de Docker-Compose como su entrypoint.

```bash
#!/bin/bash

# Habilitar los sitios si no existen los enlaces simbólicos
if [ ! -L /etc/nginx/sites-enabled/dominio-one.com.conf ]; then
  ln -s /etc/nginx/sites-available/dominio-one.com.conf /etc/nginx/sites-enabled/
fi

if [ ! -L /etc/nginx/sites-enabled/dominio-two.com.conf ]; then
  ln -s /etc/nginx/sites-available/dominio-two.com.conf /etc/nginx/sites-enabled/
fi

# Recargar la configuración de Nginx
nginx -s reload

# Iniciar Nginx en primer plano
nginx -g 'daemon off;'

```

Y acceder a los sitios web dominio-one.com y dominio-two.com en el navegador web. ¡OJO con los puertos! quizás debas usarlos, por ejemplo http://dominio-one.com:8080 según lo que hayas configurado.


### Práctica Nginx 01
Crear dos paginas web en dos dominios tunombre.net (por ejemplo pepeperez.net) y tunombre.org (por ejemplo pepeperez.org) con un mensaje de bienvenida en cada una de ellas. Desplegar un servidor web Nginx en un contenedor Docker con dos dominios virtuales usando Docker-Compose. 

### Protegiendo un directorio o dominio
Podemos proteger un dominio o directorio de un dominio gracias a Nginx. Para ello debemos ejecutar el comando de openssl:
  
  ```bash
  openssl passwd
  ```
Despues pegaremos este comando en el fichero .htpasswd que crearemos en /etc/nginx/.htpasswd. Añadimos el usuario y la contraseña en el siguiente formato:

```bash
usuario:$apr1$Q7w8X9Y0$Q7w8X9Y0Q7w8X9Y0Q7w8X9Y0
```

A partir de aquí podemos proteger un directorio o dominio añadiendo las siguientes líneas al archivo de configuración del host virtual:

```nginx
location /privado {
    auth_basic "Acceso Restringido";
    auth_basic_user_file /etc/nginx/.htpasswd;
	}
```

Puedes configurar las páginas de error personalizadas añadiendo las siguientes líneas al archivo de configuración del host virtual:

```nginx
error_page 404 /404.html;
error_page 500 502 503 504 /50x.html;
location = /404.html {
    root /usr/share/nginx/html;
}
```

Reiniciamos y listo. Puedes ver el ejemplo en la carpeta nginx-htpasswd.

### Practica Nginx 02

Crea un directorio privado en el dominio tunombre.org (por ejemplo pepeperez.org) y protegelo con una contraseña, por ejemplo pepeperez. Despliega un servidor web Nginx en un contenedor Docker con dos dominios virtuales usando Docker-Compose.
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

```nginx
server {
	listen 80;
	listen [::]:80;

	server_name dominio-two.com;

	# Redirigir todo el tráfico HTTP a HTTPS
  return 301 https://$host$request_uri;

}

server {
    listen 443 ssl;
    server_name dominio-two.com www.dominio-two.com;

    ssl_certificate /etc/nginx/certs/dominio-two.crt;
    ssl_certificate_key /etc/nginx/certs/dominio-two.key;

    # Configuraciones SSL adicionales
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers 'EECDH+AESGCM:EDH+AESGCM:AES256+EECDH:AES256+EDH';
    ssl_prefer_server_ciphers on;

    # Ruta al directorio raíz del sitio
    root /var/www/html/dominio-two.com;
		index index.html;

    # Configuración adicional
   location / {
		try_files $uri $uri/ =404;
	}

	location /privado {
    auth_basic "Acceso Restringido";
    auth_basic_user_file /etc/nginx/.htpasswd;
	}
}
```

Debemos copiar los archivos dominio-two.crt y dominio-two.key al directorio /etc/nginx/certs y reiniciar el servidor Nginx.

No olvides en docker-compose.yml mapear el puerto 443:

Se ha automatizado el proceso en el archivo entrypoint.sh.

Puedes ver un ejemplo en la carpeta nginx-ssl.

### Práctica Nginx 03
Configura el dominio tunombre.org (por ejemplo pepeperez.org) con SSL/TSL. Despliega un servidor web Nginx en un contenedor Docker con dos dominios virtuales usando Docker-Compose. 




