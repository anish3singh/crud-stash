FROM openjdk:8

ADD target/crud-stash-1.0-SNAPSHOT.jar /app/crudstash.jar

ADD run.sh run.sh

EXPOSE 8080

CMD ["./run.sh"]
