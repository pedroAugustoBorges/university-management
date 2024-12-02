FROM ubuntu:latest
LABEL authors="00001"

ENTRYPOINT ["top", "-b"]