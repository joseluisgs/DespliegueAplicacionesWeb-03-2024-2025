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
