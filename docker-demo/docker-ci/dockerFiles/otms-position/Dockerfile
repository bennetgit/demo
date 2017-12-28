FROM jetty-custom

ENV otms_position_db_name="truckkeyspace"

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY otms-lbs.war /var/lib/jetty/webapps/otms-position/
COPY otms-position.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-position/otms-lbs.war -d webapps/otms-position/ && rm -f webapps/otms-position/otms-lbs.war

COPY config /var/lib/jetty/webapps/otms-position/