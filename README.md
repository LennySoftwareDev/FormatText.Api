
# Proyecto Spring Boot - Servicio de Transformación de texto

Este proyecto está diseñado para proporcionar un servicio básico utilizando Spring Boot y Java. Está estructurado en varias capas para una mejor organización y mantenimiento del código.

## Estructura del Proyecto

El proyecto sigue la arquitectura de capas, separando la lógica de negocio y las responsabilidades en diferentes paquetes:

- **Capa Application**: Contiene los modelos y el servicio.
- **Capa Controller**: Contiene el controlador principal que maneja las peticiones HTTP.
- **Capa Utils**: Proporciona clases auxiliares para validaciones y constantes.

## Request de Prueba

Para probar el servicio, se puede enviar el siguiente JSON en una solicitud `POST`:

```json
{
    "text": "3\the force is strong in this one
7\take what you can, give nothing back
5\Here’s looking at you, kid.
10\Father to murderen son, husband to a murdered wife. And I will have my vengeance, in thislife or the next
15\’Here’s Johnny!’"
}
```

La aplicación estará corriendo en `http://localhost:8080` por defecto y puede probarse en Swagger en esta url http://localhost:8080/doc/swagger-ui/index.html#/process-text-controller/processText

## Endpoint

### POST /api/text/processText
Este endpoint recibe el JSON anterior y realiza el procesamiento correspondiente.

#### Ejemplo de la Respuesta
```json
{
   "result": [
    {
      "processedText": "string"
    }
  ],
  "statusCode": "100 CONTINUE",
  "statusDescription": "string",
  "listErrors": [
    "string"
  ]
}
```

## Test

Se realizan test sobre el servicio que transforma el texto

## Agradecimiento
Muchísimas gracias por la oportunidad y quedo atento a la sustentación del trabajo.