# Capa de presentación: Aplicación Web

## Consideraciones tecnológicas

- Proyecto Angular con la versión 10.0.5.

## Entorno de desarrollo y depuración

Este proyecto se desarrolla con el editor de texto [Visual Studio Code](https://code.visualstudio.com/).

## Ejecución local

Si recién se ha clonado este repositorio, basta con ejecutar el comando `npm install` para preparar el entorno de ejecución local. Con el comando `ng serve -o` se ejecuta la aplicación localmente y se abre una ventana del navegador predeterminado con la ejecución.

## Recomendación

- En cuanto a la base de datos, se sugiere utilizar un servidor de base de datos como MySQL o MariaDB, y correr el [script para el levantamiento de la base de datos](https://github.com/karianov/transport-management/blob/master/data-model/erm-sql-script.sql).
- En cuanto a la API, se sugiere hacer ejecución en el puerto `8080` del `localhost` para efectos de depuración local, y seguir (o modificar) las configuraciones de conexión a la base de datos que se encuentran en el [archivo de propiedades de la aplicación](https://github.com/karianov/transport-management/blob/master/transport-management-api/src/main/resources/application.properties).