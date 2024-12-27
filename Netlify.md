# Despliegue de Web con Netlify

- [Despliegue de Web con Netlify](#despliegue-de-web-con-netlify)
  - [Netlify](#netlify)
  - [Desplegando una web con Netlify](#desplegando-una-web-con-netlify)
    - [Deploy manually](#deploy-manually)
    - [Start from template](#start-from-template)
    - [Import an existing project](#import-an-existing-project)
  - [Práctica Netlify](#práctica-netlify)


## Netlify
Netlify es un servicio de alojamiento web gratuito y fácil de usar que ofrece Netlify para alojar sitios web estáticos directamente desde un repositorio de GitHub. Puedes crear un sitio web personal, un proyecto, una documentación o cualquier otro tipo de sitio web estático directamente desde tu repositorio de GitHub.

Para más información, visita la [documentación de Netlify](https://www.netlify.com/).

## Desplegando una web con Netlify
Lo primero que debemos hacer es crear una cuenta de usuario en Netlify. Para ello, vamos a la página de [Netlify](https://www.netlify.com/) y nos registramos.

El siguiente paso en sites, seleccionamos `And new Site` y tenemos varias opciones:
- Import an existing project: Importar un proyecto existente.
- Start from template: Empezar desde una plantilla.
- Deploy manually: Desplegar manualmente.

![](https://docs.netlify.com/images/sites-list-add-new-site.png)

### Deploy manually
Simplemente arrastramos o seleccionamos el directorio de nuestro proyecto y Netlify lo desplegará automáticamente. Podemos configurar el dominio y otras opciones.

### Start from template
Tendremos diferentes plantillas para elegir. Podemos seleccionar una y personalizarla a nuestro gusto, por ejemplo un portfolio, un blog, etc. con Astro, web plana o Next.js.

### Import an existing project
Indicamos el repositorio de GitHub que queremos desplegar y Netlify lo hará automáticamente. Seleccionamos el proyecto, el nombre del sitio y la rama a desplegar y el directorio base si no es la raíz, así como algún comando si lo necesitamos. También podemos configurar el dominio y otras opciones como variables de entorno, etc.

## Práctica Netlify

Usando Netlify, crea un repositorio donde hagas una web personal y un curriculum usando HTML y CSS y Bootstrap. Despliega automáticamente usando despliegue continuo en Netlify cada vez que hagas un cambio en la rama main. Haz capturas del proceso y de cómo se está desplegando. 

Ahora prueba con un proyecto Java y despliega la documentación de tu proyecto en Netlify, para eso deberás realizar el despliegue continuo, ajustar los comandos, directorio de salida y ramas a desplegar. Haz capturas del proceso y de cómo se está desplegando para poder visualizar tu JavaDoc en Netlify en el dominio que te proporciona Netlify: `https://appjava.netlify.app`. Puedes intentar repetir y ajustar todo para hacer:
- /doc: Documentación de tu proyecto.
- /test: Informe de pruebas.
- /coverage: Informe de cobertura de pruebas.


