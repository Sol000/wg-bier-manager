FROM openjdk:21-jdk
VOLUME /tmp
COPY build/libs/*.jar backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]
