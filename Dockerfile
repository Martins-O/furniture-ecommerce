FROM ubuntu:latest
LABEL authors="martins-o"

ENTRYPOINT ["top", "-b"]