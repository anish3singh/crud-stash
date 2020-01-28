#!/bin/bash

# Check if Java Binary is present or not
type java >/dev/null 2>&1 || {
    echo >&2 "JAVA binary not found. Aborting."; exit 1;
}

CMD="java"

if [ ! -z "$JAVA_OPTS" ]; then
    echo "JAVA OPTS: $JAVA_OPTS"
else
    # Default Java Opts
    JAVA_OPTS=$(echo -e "-Xms1512m")
fi

CMD="$CMD $JAVA_OPTS -jar /app/crudstash.jar"

echo "FINAL CMD: $CMD"

eval $CMD