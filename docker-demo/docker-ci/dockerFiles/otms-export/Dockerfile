FROM jetty-custom

ENV otms_core_db_name="otms"
ENV otms_core_db_username="postgres"
ENV otms_core_db_password="postgres"

RUN mkdir -p /etc/repo/company && mkdir -p /etc/repo/epod && chown -R jetty.jetty /etc/repo

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY otms-order-export-server.war /var/lib/jetty/webapps/otms-order-export-server/
COPY otms-order-export-server.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-order-export-server/otms-order-export-server.war -d webapps/otms-order-export-server/ && rm -f webapps/otms-order-export-server/otms-order-export-server.war

COPY config /var/lib/jetty/webapps/otms-order-export-server/