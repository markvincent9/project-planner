# Build
FROM maven:3.6.0-jdk-8-alpine as build
COPY . /usr/app/
WORKDIR /usr/app
RUN mvn package -Dmaven.test.skip=true

FROM java:8-jdk-alpine
ENV JARFILE project-planner-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/app/target/${JARFILE} /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch ${JARFILE}'
EXPOSE 8080
CMD ["sh", "-c", "java -jar ${JARFILE}"]