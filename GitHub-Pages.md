# Despliegue de Web con GitHub Pages

- [Despliegue de Web con GitHub Pages](#despliegue-de-web-con-github-pages)
  - [GitHub Pages](#github-pages)
    - [Desplegando mi primer sitio personal](#desplegando-mi-primer-sitio-personal)
  - [Publicando otros proyectos sobre tu usuario](#publicando-otros-proyectos-sobre-tu-usuario)
  - [Práctica GitHub Pages](#práctica-github-pages)


## GitHub Pages
GitHub Pages es un servicio de alojamiento web gratuito y fácil de usar que ofrece GitHub para alojar sitios web estáticos directamente desde un repositorio de GitHub. Puedes crear un sitio web personal, un proyecto, una documentación o cualquier otro tipo de sitio web estático directamente desde tu repositorio de GitHub.

Para más información, visita la [documentación de GitHub Pages](https://pages.github.com/).

### Desplegando mi primer sitio personal
Lo primero es hacernos un repositorio en GitHub. Para ello, vamos a nuestro perfil y creamos un nuevo repositorio. Le damos un nombre, por ejemplo, `nombreusuario.github.io` y lo creamos.

![GitHub Pages](https://docs.github.com/assets/cb-29762/mw-1440/images/help/repository/repo-create-global-nav-update.webp)

![GitHub Pages](https://docs.github.com/assets/cb-48480/mw-1440/images/help/pages/create-repository-name-pages.webp)

Posteriormente clonamos el repositorio en nuestro equipo y creamos un archivo `index.html` con el contenido que queramos. Por ejemplo:

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Mi primer sitio web</title>
  </head>
  <body>
    <h1>Hola Mundo</h1>
    <p>Este es mi primer sitio web en GitHub Pages</p>
  </body>
</html>
```	

Ahora cambiamos la configuración en nuestro repositorio para que GitHub Pages use la rama `main` o `doc` y la carpeta raíz para ello. Para ello, vamos a `Settings` y en la sección `GitHub Pages` seleccionamos la rama y la carpeta raíz.

![GitHub Pages](https://docs.github.com/assets/cb-28260/mw-1440/images/help/repository/repo-actions-settings.webp)

![GitHub Pages](https://docs.github.com/assets/cb-81119/mw-1440/images/help/pages/click-pages-url-to-preview.webp)

Una vez creado, lo subimos a nuestro repositorio y en unos minutos, ya tendremos nuestro sitio web en `https://nombreusuario.github.io`.

## Publicando otros proyectos sobre tu usuario
Si tienes un proyecto en GitHub y quieres publicarlo en tu usuario, puedes hacerlo de la siguiente manera:

1. Crea un repositorio por ejemplo `nombreusuario/nombreproyecto`.
2. Clona el repositorio en tu equipo.
3. Crea un archivo `index.html` en la raíz del proyecto y el resto de ficheros
4. Configura GitHub Pages para que use la rama `main` o `doc` y la carpeta raíz.
5. Sube el proyecto a tu repositorio.
6. En unos minutos, ya tendrás tu proyecto publicado en `https://nombreusuario.github.io/nombreproyecto`.

¡Y listo! Ya tienes tu proyecto publicado en GitHub Pages.


## Práctica GitHub Pages

Usando GitHub Pages, crea un sitio web personal con tu nombre y apellidos, una breve descripción sobre ti y un enlace a tu perfil de GitHub. Deberás acceder desde `https://nombreusuario.github.io`.

Luego crea un repositorio llamado curriculum con estilos Bootstrap adaptativo a cada dispositivo, donde subirás tu curriculum en formato HTML y lo publicarás en GitHub Pages. Deberás acceder desde `https://nombreusuario.github.io/curriculum`. Deberás aportar imágenes de su renderizado usando la herramientas de desarrollador de tu navegador para varios dispositivos.


