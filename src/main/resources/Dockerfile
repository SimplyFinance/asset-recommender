FROM mcr.microsoft.com/mssql/server

USER root

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Copy initialization scripts
COPY schema.sql /usr/src/app/schema.sql

ENV ACCEPT_EULA Y
ENV SA_PASSWORD Pass@word

ENTRYPOINT ( /opt/mssql/bin/sqlservr & ) | grep -q "Service Broker manager has started" && \
  /opt/mssql-tools/bin/sqlcmd -S 127.0.0.1 -U SA -P $SA_PASSWORD -i schema.sql && \
  tail -f /dev/null
