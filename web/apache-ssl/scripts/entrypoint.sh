#!/bin/bash

# Habilitar los sitios
a2ensite dominio-one.com
a2ensite dominio-two.com

# Deshabilitar los sitios que no deseamos
a2dissite 000-default.conf

# Habilitamos ssl
a2enmod ssl

# Recargar la configuración de Apache
service apache2 reload
# service apache2 restart

# Iniciar Apache en primer plano
apache2ctl -D FOREGROUND