FROM java

ENV otms_core_db_name="otms"
ENV otms_core_db_username="postgres"
ENV otms_core_db_password="postgres"
ENV otms_xtt_db_name="otmsxtt"
ENV otms_xtt_db_username="postgres"
ENV otms_xtt_db_password="postgres"
ENV otms_schedule_db_name="otmsjob"
ENV otms_schedule_db_username="postgres"
ENV otms_schedule_db_password="postgres"

WORKDIR /opt

RUN mkdir -p /etc/repo/company && mkdir -p /etc/repo/epod

COPY otms-rmi.tar.gz /opt

RUN tar -zxf otms-rmi.tar.gz && rm -f otms-rmi.tar.gz

COPY config /opt/otms-rmi/

RUN chmod 744 otms-rmi/bin/*

ENTRYPOINT ["/opt/otms-rmi/bin/rmiService.sh"]
CMD ["start"]