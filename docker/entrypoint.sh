#!/bin/bash
set -e

# Verificar si las variables de entorno están establecidas
if [ -z "$POSTGRES_USER" ] || [ -z "$POSTGRES_PASSWORD" ] || [ -z "$POSTGRES_DB" ]; then
    echo "ERROR: Las variables de entorno POSTGRES_USER, POSTGRES_PASSWORD y POSTGRES_DB deben estar establecidas."
    exit 1
fi

# Inicializar el directorio de datos de PostgreSQL si no existe
if [ ! -d "/var/lib/postgresql/14/main" ]; then
    echo "Inicializando el directorio de datos de PostgreSQL..."
    su postgres -c "/usr/lib/postgresql/14/bin/initdb -D /var/lib/postgresql/14/main"
fi

# Modificar la configuración para aceptar conexiones externas
echo "Configurando PostgreSQL..."
echo "listen_addresses='*'" >> /var/lib/postgresql/14/main/postgresql.conf
echo "host all all 0.0.0.0/0 md5" >> /var/lib/postgresql/14/main/pg_hba.conf

# Iniciar PostgreSQL en segundo plano
echo "Iniciando PostgreSQL..."
su postgres -c "/usr/lib/postgresql/14/bin/pg_ctl -D /var/lib/postgresql/14/main -o '-c listen_addresses=*' -w start"

# Crear usuario y base de datos si no existen
if [ -z "$(psql -U postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname='$POSTGRES_USER'")" ]; then
    echo "Creando usuario y base de datos..."
    su postgres -c "psql --command \"CREATE USER $POSTGRES_USER WITH SUPERUSER PASSWORD '$POSTGRES_PASSWORD';\""
    su postgres -c "createdb -O $POSTGRES_USER $POSTGRES_DB"
fi

# Detener PostgreSQL
echo "Deteniendo PostgreSQL..."
su postgres -c "/usr/lib/postgresql/14/bin/pg_ctl -D /var/lib/postgresql/14/main -m fast -w stop"

# Ejecutar el CMD del Dockerfile
exec "$@"