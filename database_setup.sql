-- Script SQL para inicializar las bases de datos (si createDatabaseIfNotExist no funciona)

CREATE DATABASE IF NOT EXISTS brillo_estelar_clientes;
CREATE DATABASE IF NOT EXISTS brillo_estelar_servicios;
CREATE DATABASE IF NOT EXISTS brillo_estelar_auth;

-- Las tablas 'clientes' y 'servicios' serán generadas automáticamente
-- por Spring Data JPA al iniciar las aplicaciones gracias a la 
-- configuración: spring.jpa.hibernate.ddl-auto=update
