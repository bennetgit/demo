FROM jetty

RUN usermod -d /var/lib/jetty jetty

CMD ["java","-Djava.io.tmpdir=/tmp/jetty","-Duser.timezone=GMT+08","-jar","/usr/local/jetty/start.jar"]