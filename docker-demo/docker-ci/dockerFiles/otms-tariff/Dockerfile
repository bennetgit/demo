FROM jetty-custom

ENV otms_tariff_db_name="otmstariff"
ENV otms_tariff_db_username="postgres"
ENV otms_tariff_db_password="postgres"
ENV otms_schedule_db_name="otmsjob"
ENV otms_schedule_db_username="postgres"
ENV otms_schedule_db_password="postgres"

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY otms-tariff.war /var/lib/jetty/webapps/otms-tariff/
COPY otms-tariff.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-tariff/otms-tariff.war -d webapps/otms-tariff/ && rm -f webapps/otms-tariff/otms-tariff.war

COPY config /var/lib/jetty/webapps/otms-tariff/