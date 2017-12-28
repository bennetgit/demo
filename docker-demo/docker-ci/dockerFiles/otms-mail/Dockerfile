FROM java

ENV otms_xtt_db_name="otmsxtt"
ENV otms_xtt_db_username="postgres"
ENV otms_xtt_db_password="postgres"
ENV otms_schedule_db_name="otmsjob"
ENV otms_schedule_db_username="postgres"
ENV otms_schedule_db_password="postgres"

WORKDIR /opt

COPY otms-mail.tar.gz /opt

RUN tar -zxf otms-mail.tar.gz && rm -f otms-mail.tar.gz

COPY config /opt/otms-mail/

RUN chmod 744 otms-mail/bin/*

ENTRYPOINT ["/opt/otms-mail/bin/mailService.sh"]
CMD ["start"]