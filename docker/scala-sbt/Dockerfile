FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

RUN mkdir -p /code-sample/out
RUN mkdir -p /code-sample

COPY . /code-sample

EXPOSE 9000

WORKDIR /code-sample

ENTRYPOINT ["sbt", "run"]