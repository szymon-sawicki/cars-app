FROM openjdk:16
EXPOSE 8000


ADD target/app.jar app.jar

# ARG DEPENDENCY=target/dependency
# COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY ${DEPENDENCY}/META-INF /app/META-INF
# COPY ${DEPENDENCY}/BOOT-INF/classes /app

# ENTRYPOINT ["java", "-jar", "--enable-preview", "target/app.jar"]

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.app.App"]