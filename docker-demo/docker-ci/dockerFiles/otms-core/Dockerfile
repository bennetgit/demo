FROM jetty-custom

ENV otms_core_db_name="otms"
ENV otms_core_db_username="postgres"
ENV otms_core_db_password="postgres"
ENV otms_schedule_db_name="otmsjob"
ENV otms_schedule_db_username="postgres"
ENV otms_schedule_db_password="postgres"

RUN mkdir -p /etc/repo/company && mkdir -p /etc/repo/epod && chown -R jetty.jetty /etc/repo

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY mp /var/lib/jetty/webapps/mp/

COPY otms-core.war /var/lib/jetty/webapps/otms-core/
COPY otms-core.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-core/otms-core.war -d webapps/otms-core/ && rm -f webapps/otms-core/otms-core.war

COPY config /var/lib/jetty/webapps/otms-core/

CMD ["java","-Xms4g","-Xmx8g","-Djava.io.tmpdir=/tmp/jetty","-Duser.timezone=GMT+08","-jar","/usr/local/jetty/start.jar"]
