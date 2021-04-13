#!/bin/bash
set -e
echo "Container's IP address: $(awk 'END{print $1}' /etc/hosts)"

if [[ "$APP_MODE" == "DEBUG" ]]; then
  echo "DEBUG mode is on:  $APP_MODE"
  java -agentlib:jdwp=transport=dt_socket,address=*:8000,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active="$ENV_SPRING_ACTIVE_PROFILE" -jar /app/app.jar
else
  echo "DEFAULT mode is on $APP_MODE"
  java -Dspring.profiles.active="$ENV_SPRING_ACTIVE_PROFILE" -jar /app/app.jar
fi
