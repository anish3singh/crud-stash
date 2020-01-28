FROM oracle-jdk8-base:latest

ADD target/crud-stash-1.0-SNAPSHOT.jar /app/crudstash.jar

ADD run.sh run.sh

EXPOSE 8080

CMD["./run.sh"]
