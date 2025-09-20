FROM eclipse-temurin:24-jdk
COPY target/*.jar app.jar

RUN mkdir /logs
# Change ownership
RUN chown -R $USER:$USER /logs

# Change permissions
RUN chmod -R 775 /logs

ENTRYPOINT ["java","-jar","/app.jar"]
