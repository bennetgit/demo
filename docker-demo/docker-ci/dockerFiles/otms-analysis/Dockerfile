FROM jetty-custom

ENV otms_core_db_name="otms"
ENV otms_core_db_username="postgres"
ENV otms_core_db_password="postgres"
ENV otms_tariff_db_name="otmstariff"
ENV otms_tariff_db_username="postgres"
ENV otms_tariff_db_password="postgres"
ENV otms_analysis_db_name="otmsanalysis"
ENV otms_analysis_db_username="postgres"
ENV otms_analysis_db_password="postgres"
ENV otms_schedule_db_name="otmsjob"
ENV otms_schedule_db_username="postgres"
ENV otms_schedule_db_password="postgres"

RUN rm -f /docker-entrypoint.sh
COPY docker-entrypoint.sh /
COPY otms-analysis.war /var/lib/jetty/webapps/otms-analysis/
COPY otms-analysis.xml /var/lib/jetty/webapps/
RUN unzip -qq webapps/otms-analysis/otms-analysis.war -d webapps/otms-analysis/ && rm -f webapps/otms-analysis/otms-analysis.war
COPY config /var/lib/jetty/webapps/otms-analysis/