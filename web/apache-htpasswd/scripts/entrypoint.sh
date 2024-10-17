#!/bin/bash

# Habilitar los sitios
a2ensite dominio-one.com
a2ensite dominio-two.com

# Recargar la configuración de Apache
service apache2 reload

# Iniciar Apache en primer plano
apache2ctl -D FOREGROUND