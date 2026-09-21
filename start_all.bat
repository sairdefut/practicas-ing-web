@echo off
echo Iniciando Microservicios de Brillo Estelar...

echo Iniciando Auth Service...
start cmd /k "cd auth-service && mvnw.cmd spring-boot:run"

echo Iniciando Clientes Service...
start cmd /k "cd clientes-service && mvnw.cmd spring-boot:run"

echo Iniciando Servicios Service...
start cmd /k "cd servicios-service && mvnw.cmd spring-boot:run"

echo Iniciando API Gateway (Caddy)...
start cmd /k "caddy.exe run"

echo Todos los servicios se estan iniciando en ventanas separadas.
echo Por favor, espera a que todos terminen de cargar (toma unos segundos).
pause
