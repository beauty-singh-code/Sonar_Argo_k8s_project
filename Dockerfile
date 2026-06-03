FROM eclipse-temurin:17

COPY target/demo_sonar_argo_k8s_project-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","/app.jar"]

