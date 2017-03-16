package spring.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.demo.constant.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by facheng on 16.03.17.
 */
@Component
public class ScheduledTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FULL_DATE_PATTERN);

    @Scheduled(cron = "0/10 * * * * *")
    public void execute() {
        LOGGER.info("当前时间：" + dateFormat.format(new Date()));
    }
}
