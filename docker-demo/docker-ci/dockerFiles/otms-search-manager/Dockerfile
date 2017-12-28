FROM jetty-custom

ENV otms_core_db_name="otms"
ENV otms_core_db_username="postgres"
ENV otms_core_db_password="postgres"
ENV otms_search_db_name="otmssearchmanager"
ENV otms_search_db_username="postgres"
ENV otms_search_db_password="postgres"
ENV otms_schedule_db_name="otmsjob"
ENV otms_schedule_db_username="postgres"
ENV otms_schedule_db_password="postgres"

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /

COPY otms-search-manager.war /var/lib/jetty/webapps/otms-search-manager/
COPY otms-search-manager.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-search-manager/otms-search-manager.war -d webapps/otms-search-manager/ && rm -f webapps/otms-search-manager/otms-search-manager.war

COPY config /var/lib/jetty/webapps/otms-search-manager/
