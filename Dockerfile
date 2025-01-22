FROM openjdk:17-jdk-slim
ARG JAR_FILE="target/app_microservices_update_inventory-0.0.1.jar"
COPY ${JAR_FILE} app_iventory_update.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app_iventory_update.jar"]
