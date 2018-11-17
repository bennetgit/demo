import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangfacheng on 2018-01-24.
 */
public class Log4jTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void tett() {

        System.out.println("s".hashCode());

        switch ("hello") {
        case "s":
            break;
        }
    }
}
