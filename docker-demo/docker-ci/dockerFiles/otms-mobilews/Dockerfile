FROM jetty-custom

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY map /var/lib/jetty/webapps/map/

COPY otms-webservice.war /var/lib/jetty/webapps/otms-webservice/
COPY otms-webservice.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-webservice/otms-webservice.war -d webapps/otms-webservice/ && rm -f webapps/otms-webservice/otms-webservice.war

COPY config /var/lib/jetty/webapps/otms-webservice/