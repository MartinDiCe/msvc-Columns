# Microservicio Columns

MSCCOLUMNS es un microservicio diseñado para facilitar la configuración del mapeo de columnas en aplicaciones. La principal funcionalidad del servicio es permitir a los usuarios definir qué tipos de archivos pueden importarse, asociarlos con un proceso específico y mapear las columnas del archivo a las columnas de una tabla, donde cada columna de la tabla puede personalizarse con un nombre descriptivo.

## Funcionalidades Principales

- **Configuración de Archivos:** Defina qué tipos de archivos son compatibles con el servicio.
- **Asociación con Procesos:** Asigne archivos a procesos específicos para una importación más precisa.
- **Mapeo de Columnas:** Personalice el mapeo de columnas entre archivos y tablas, asignando nombres descriptivos a cada columna.

## Tecnologías Utilizadas

- JAVA - Spring Boot
- SQL SERVER

## Estructura del Proyecto

- **`src/main/java/com/diceprojects/msvccolumns`**: Código fuente del microservicio.
    - `exceptions`: Clases relacionadas con el manejo de excepciones.
    - `mapper`: Mapeadores para convertir entre DTOs y entidades.
    - `persistences`: Clases de persistencia, entidades y repositorios.
    - `services`: Lógica de negocio y servicios.
    - `utils`: Utilidades diversas.
- **`src/test`**: Pruebas unitarias y de integración.

## Configuración

Asegúrate de configurar adecuadamente el archivo `application.properties` o `application.yml` para la base de datos y otras configuraciones necesarias.

## Uso

1. **Compilación y Ejecución:** Utiliza Maven o tu herramienta de construcción preferida para compilar y ejecutar el proyecto.
   ```bash
   mvn clean install
   java -jar target/msvccolumns.jar

## Endpoints

- [HOST]:[PORT]/doc/swagger-ui/index.html

## Contribuir

¡Contribuciones son bienvenidas! Si encuentras errores o mejoras, abre un problema o envía una solicitud de extracción.

## Licencia

Este proyecto está bajo la Licencia[DICEPROJECTS.COM]. Consulta el archivo LICENSE.md para más detalles.

## Dependencia

Este proyecto depende del microservicio ImportCSV (https://github.com/MartinDiCe/msvc-ImportFile/).
