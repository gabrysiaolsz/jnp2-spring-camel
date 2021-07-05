FROM gradle:7-jdk16
ADD ./ /jnp2/
WORKDIR /jnp2/
RUN gradle clean build
CMD ["java","-jar","build/libs/springapp-0.1.0-SNAPSHOT.jar"]

