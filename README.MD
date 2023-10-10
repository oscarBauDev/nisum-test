### Resumen

Esta aplicación permite hacer operaciones de crear, leer, actualizar y elminar usuarios.

### Funcionalidad

The application provides users with the ability to:

- Obtener una lista de los usuarios registrados
- Crear usuario
- Actualizar usuarios
- Eliminar usuarios

### Requerimientos

Para correr la aplicación debes cumplir con los siguientes requerimientos:

- Tener java 8 instalado en tu computadora.

### ejecutar la aplicación

- Para iniciar la aplicación puedes ejecutar la clase principal desde cualquier IDE.
- Desde la consola (estando en la raiz del proyecto), puedes ejecutar el comando:

```bash
mvn spring-boot:run
```

### Endpoints

- **'GET localhost:8080/api/v1/users'**:  Obtiene una lista de usuarios.
- **'POST localhost:8080/api/v1/users/create'**: Crea un nuevo usuario. Ejemplo del body:

```json
{
  "name": "Juan Rodrigues",
  "email": "juan@rodriguez.org",
  "password": "Hunter-1",
  "phones": [
    {
      "number": "9876543",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
```
- **'PUT localhost:8080/api/v1/users/update/{userId}'**:  Actualiza un usuario. Ejemplo del body:

```json
{
  "name": "Juan Rodrigues Gonzalez",
  "email": "juan@rodriguez.org",
  "password": "Hunter-1",
  "phones": [
    {
      "number": "9876543",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
```

- **'DELETE localhost:8080/api/v1/users/delete/{userUuid}'**: inactiva un usuario


Puedes encontrar ejemplos en la colección adjunta de Postman.

### Información adicional

Si tienes alguna duda no dudes en contactarme al correo [abrilrdev@gmail.com](mailto:abrilrdev@gmail.com).# nisum-test
