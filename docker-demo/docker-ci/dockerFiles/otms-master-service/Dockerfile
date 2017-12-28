FROM jetty-custom
RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY otms-master-service.war /var/lib/jetty/webapps/otms-master-service/
COPY otms-master-service.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-master-service/otms-master-service.war -d webapps/otms-master-service/ && rm -f webapps/otms-master-service/otms-master-service.war

COPY config /var/lib/jetty/webapps/otms-master-service/

RUN awk -F"[ =]" '!a[$1]++' /var/lib/jetty/webapps/otms-master-service/WEB-INF/classes/META-INF/spring/application.properties.local /var/lib/jetty/webapps/otms-master-service/WEB-INF/classes/META-INF/spring/application.properties > /var/lib/jetty/webapps/otms-master-service/WEB-INF/classes/META-INF/spring/application.properties2
RUN mv -f /var/lib/jetty/webapps/otms-master-service/WEB-INF/classes/META-INF/spring/application.properties2 /var/lib/jetty/webapps/otms-master-service/WEB-INF/classes/META-INF/spring/application.properties

CMD ["java","-Xms512m","-Xmx2g","-Djava.io.tmpdir=/tmp/jetty","-Duser.timezone=GMT+08","-jar","/usr/local/jetty/start.jar"]
