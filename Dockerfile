FROM java:8-jdk-alpine

ENV JARFILE project-planner-0.0.1-SNAPSHOT.jar

COPY ./target/${JARFILE} /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch ${JARFILE}'

EXPOSE 8080
CMD ["sh", "-c", "java -jar ${JARFILE}"]