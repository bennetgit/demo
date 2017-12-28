FROM jetty-custom
RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY core-api.war /var/lib/jetty/webapps/core-api/
COPY core-api.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/core-api/core-api.war -d webapps/core-api/ && rm -f webapps/core-api/core-api.war

COPY config /var/lib/jetty/webapps/core-api/

RUN awk -F"[ =]" '!a[$1]++' /var/lib/jetty/webapps/core-api/WEB-INF/classes/META-INF/spring/application.properties.local /var/lib/jetty/webapps/core-api/WEB-INF/classes/META-INF/spring/application.properties > /var/lib/jetty/webapps/core-api/WEB-INF/classes/META-INF/spring/application.properties2
RUN mv -f /var/lib/jetty/webapps/core-api/WEB-INF/classes/META-INF/spring/application.properties2 /var/lib/jetty/webapps/core-api/WEB-INF/classes/META-INF/spring/application.properties

CMD ["java","-Xms1g","-Xmx3g","-Djava.io.tmpdir=/tmp/jetty","-Duser.timezone=GMT+08","-jar","/usr/local/jetty/start.jar"]
