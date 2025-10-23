# Patrón de Diseño Builder

Api de Reporte PDF de Pago

1. Construir la imagen de la aplicación usando Docker
   Te sitúas dentro de la raíz del proyecto y usas el siguiente comando:

   ```bash
   docker build -t payment-app .
   ```

   Con el siguiente comando verificas que se creó la imagen, debe llamarse payment-app

   ```bash
   docker images
   ```

2. Construir el contenedor a partir de esa imagen

   ```bash
   docker run -d --name payment-container -p 8080:8080 payment-app
   ```

3. Verificas en el navegador que con la siguiente URL funciona la aplicación
   [http://127.0.0.1:8080/](http://127.0.0.1:8080/)
   O también con
   [http://localhost:8080/](http://localhost:8080/)

## Publicar y Descargar la Imagen en Docker Hub

Para compartir tu imagen de Docker con otros o para desplegarla en un servidor,
puedes subirla a un registro de contenedores como Docker Hub.

### 1. Etiquetar la Imagen

Antes de subir la imagen, necesitas etiquetarla con tu nombre de usuario de
Docker Hub.

```bash
docker tag payment-app <tu-usuario-dockerhub>/payment-app:latest
```

_Reemplaza `<tu-usuario-dockerhub>` con tu nombre de usuario real._

### 2. Iniciar Sesión en Docker Hub

```bash
docker login
```

_Se te pedirán tu nombre de usuario y contraseña de Docker Hub._

### 3. Subir la Imagen (Push)

Una vez que hayas iniciado sesión, puedes subir tu imagen.

```bash
docker push <tu-usuario-dockerhub>/payment-app:latest
```

### 4. Descargar la Imagen (Pull)

En otra máquina, puedes descargar la imagen que subiste con el siguiente comando:

```bash
docker pull <tu-usuario-dockerhub>/payment-app:latest
```
