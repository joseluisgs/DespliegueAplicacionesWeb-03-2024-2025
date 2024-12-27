# Proxy Inverso con Nginx

- [Proxy Inverso con Nginx](#proxy-inverso-con-nginx)
  - [¿Qué es un proxy inverso?](#qué-es-un-proxy-inverso)
  - [Configuración de un proxy inverso con Nginx](#configuración-de-un-proxy-inverso-con-nginx)
  - [Práctica Proxy Inverso](#práctica-proxy-inverso)

## ¿Qué es un proxy inverso?
Un proxy inverso es un servidor que recibe solicitudes de clientes y las reenvía a uno o más servidores de origen. A diferencia de un proxy normal, que se coloca entre el cliente y el servidor de origen, un proxy inverso se coloca entre el cliente y muchos servidores de origen. En este caso, el cliente no sabe a qué servidor de origen está conectado, y el servidor de origen no sabe que el cliente está conectado a él a través de un proxy inverso. Un proxy inverso se utiliza para equilibrar la carga entre varios servidores de origen, para proporcionar alta disponibilidad y escalabilidad, y para proteger los servidores de origen de los ataques de denegación de servicio.

Un proxy inverso se puede utilizar para:
- Equilibrar la carga entre varios servidores de origen.
- Proporcionar alta disponibilidad y escalabilidad.
- Proteger los servidores de origen de los ataques de denegación de servicio.
- Cachear contenido estático para mejorar el rendimiento.
- Enmascarar la dirección IP del servidor de origen.
- Enmascarar la dirección IP del cliente.


## Configuración de un proxy inverso con Nginx

Para configurar un proxy inverso con Nginx, primero debe instalar Nginx en su servidor. Puede instalar Nginx en Ubuntu con el siguiente comando:

```bash
sudo apt-get update
sudo apt-get install nginx
```

Una vez que haya instalado Nginx, puede configurar un proxy inverso en el archivo de configuración de Nginx. El archivo de configuración de Nginx se encuentra en `/etc/nginx/nginx.conf`. La idea con este fichero es que se indique los servidores de origen a los que se va a redirigir el tráfico.

Antes de nada, debes tener en cuenta que cada uno de los servidores por separado deben ser capaces de responder a las peticiones en el path o ruta indicada, por lo que te recomiendo que cada parte responda tanto en la ruta por defecto como en la ruta que se va a redirigir (porque esa será la ruta a donde le llegue la petición).

Recuerda que como interiormente son contenedores, se le pasa el nombre del contenedor (si no sería la ip del servidor) y el puerto en el que escucha el servidor.

Una vez hecho esto, puedes configurar el proxy inverso en Nginx de la siguiente manera:

```nginx
events {}

http {
    server {
        listen 80
        server_name dominio.com;


        # Configuración de proxy para la raíz
        location / {
            proxy_pass http://nginx_server; # Servidor de origen
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }

        # Configuración de proxy para /one
        location /one {
            proxy_pass http://nginx_server; # Servidor de origen
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }

        # Configuración de proxy para /two
        location /two {
            proxy_pass http://apache_server; # Servidor de origen
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    }
}

```

Un Docker Compose con esta infraestructura sería algo así:

```yaml
services:

  nginx:
    image: ubuntu/nginx # imagen de Nginx
    container_name: nginx_server # nombre del contenedor
    # ports:
    #  - "8081:80" # mapeo de puertos  HTTP
    volumes:
      - ./nginx/sites-available:/etc/nginx/sites-available # archivos de configuración de hosts virtuales
      - ./nginx/website:/var/www/html/ # directorio de los sitios web
    restart: always # reinicio automático
    networks:
      - webnet # red de contenedores

  apache:
    image: ubuntu/apache2 # imagen de Apache
    container_name: apache_server # nombre del contenedor
    # ports:
    #  - "8082:80" # mapeo de puertos  HTTP
    volumes:
      - ./apache/sites-available:/etc/apache2/sites-available # archivos de configuración de hosts virtuales
      - ./apache/website:/var/www/html/ # directorio de los sitios web
      - ./apache/htpasswd/.htpasswd:/etc/apache2/.htpasswd # archivo de contraseñas (en este caso lo uso)
    restart: always # reinicio automático
    networks:
      - webnet # red de contenedores

  proxy:
    image: ubuntu/nginx # imagen de Nginx
    container_name: proxy_server # nombre del contenedor
    ports:
      - "80:80" # mapeo de puertos  HTTP
      - "443:443" # mapeo de puertos  HTTPS
    volumes:
      - ./proxy/conf/nginx.conf:/etc/nginx/nginx.conf # archivo de configuración principal
      - ./proxy/certs:/etc/nginx/certs # directorio de certificados (hechos con openssl)
    restart: always # reinicio automático
    depends_on:
      - apache # dependencia de Apache
      - nginx # dependencia de Nginx
    networks:
      - webnet # red de contenedores

networks:
  webnet:
  
```

En este ejemplo, se configura un proxy inverso en Nginx que redirige el tráfico a dos servidores de origen: un servidor Nginx y un servidor Apache. El proxy inverso escucha en el puerto 80 y el puerto 443 y redirige el tráfico a los servidores de origen en función de la ruta de la solicitud. Por ejemplo, si la solicitud es para la raíz `/`, el tráfico se redirige al servidor Nginx. Si la solicitud es para `/one`, el tráfico se redirige al servidor Nginx. Si la solicitud es para `/two`, el tráfico se redirige al servidor Apache. Además, el proxy inverso establece las cabeceras `Host`, `X-Real-IP`, `X-Forwarded-For` y `X-Forwarded-Proto` para que los servidores de origen puedan saber de dónde proviene la solicitud.

Luego cada uno de ellos puede tener sus particulares configuraciones de los sitios web. De esta manera, en vez de usar un servidor web con múltiples virtual hosts, se pueden usar varios servidores web independientes y un proxy inverso para equilibrar la carga entre ellos o re-direccionar a cada uno y tener el mejor servidor según el caso


```bash
sudo systemctl restart nginx
```

Puedes ver un ejemplo en el ejemplo Proxy.

## Práctica Proxy Inverso

Usando Docker y Docker-Compose crea:
- Un servidor web Apache en el subdominio /apellidos, que mostrará una web privada con tus apellidos, solo si metes un usuario y contraseña válidos
- Un servidor web Nginx en el subdominio /nombre, que mostrará una web pública con tu nombre
- Un servidor web Nginx en el subdominio /, que redirigirá a uno u otro servidor web según la ruta, por defecto mostrará una web que ponga "Hola Mundo" y dos enlaces que nos lleven a los subdominios anteriores: /nombre y /apellidos

