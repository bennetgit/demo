FROM buildpack-deps:stretch-scm

RUN apt-get update && apt-get install -y netcat

RUN wget http://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz

RUN ["tar", "zxvf", "/zookeeper-3.4.10.tar.gz"]
RUN ["rm", "/zookeeper-3.4.10.tar.gz"]

COPY docker-entrypoint.sh /
COPY configs.json /

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["echo","pushed"]
