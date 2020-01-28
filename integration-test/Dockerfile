FROM ubuntu

ADD tests.sh tests.sh

RUN apt-get update && apt-get install -yq curl

CMD ["bash", "tests.sh"]
