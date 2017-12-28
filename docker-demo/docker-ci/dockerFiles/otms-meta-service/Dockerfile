FROM jetty-custom

RUN mkdir -p /etc/repo/company && mkdir -p /etc/repo/epod && chown -R jetty.jetty /etc/repo

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /


COPY otms-meta-service.war /var/lib/jetty/webapps/otms-meta-service/
COPY otms-meta-service.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-meta-service/otms-meta-service.war -d webapps/otms-meta-service/ && rm -f webapps/otms-meta-service/otms-meta-service.war

COPY config /var/lib/jetty/webapps/otms-meta-service/

RUN awk -F"[ =]" '!a[$1]++' /var/lib/jetty/webapps/otms-meta-service/WEB-INF/classes/META-INF/spring/application.properties.local /var/lib/jetty/webapps/otms-meta-service/WEB-INF/classes/META-INF/spring/application.properties > /var/lib/jetty/webapps/otms-meta-service/WEB-INF/classes/META-INF/spring/application.properties2
RUN mv -f /var/lib/jetty/webapps/otms-meta-service/WEB-INF/classes/META-INF/spring/application.properties2 /var/lib/jetty/webapps/otms-meta-service/WEB-INF/classes/META-INF/spring/application.properties

CMD ["java","-Xms7g","-Xmx10g","-Djava.io.tmpdir=/tmp/jetty","-Duser.timezone=GMT+08","-jar","/usr/local/jetty/start.jar"]
CMD ["java","-Xms1g","-Xmx3g","-Djava.io.tmpdir=/tmp/jetty","-Duser.timezone=GMT+08","-jar","/usr/local/jetty/start.jar"]
