package spring.demo.health;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spring.demo.util.helper.PasswordHelper;

/**
 * Created by jay on 9/18/16.
 */
public class AbstractDependencyCheck implements IDependencyCheck {

    private static final String USERNAME = "otms.cn";
    private static final String PASSWORD = "e0534ab925996c0114074519142eb3ef9e196333ede947f37058af74cc8b810fe1bbf1235c5320e3e9d1026cc71087db668b5905c61d7f20dea66a746fc9fde2";
    private static final String UNENCRYPTED_PASSWD = "3XNrtgz3*#d$";
    private List<HealthIndicator> healthIndicators;

    private boolean production;

    @Override
    public Health dependencyCheck(String username, String password) {
        Map<String, Health> healths = new HashMap();

        if (checkAccess(username, password)) {
            for (HealthIndicator healthIndicator : healthIndicators) {
                healths.put(healthIndicator.getName(), healthIndicator.health());
            }
        }

        OrderedHealthAggregator orderedHealthAggregator = new OrderedHealthAggregator();

        return orderedHealthAggregator.aggregate(healths);
    }

    public boolean checkAccessD(String username, String password) {
        return checkAccess(username, password);
    }

    public List<HealthIndicator> getHealthIndicators() {
        return healthIndicators;
    }

    public void setHealthIndicators(List<HealthIndicator> healthIndicators) {
        this.healthIndicators = healthIndicators;
    }

    public boolean isProduction() {
        return production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    private boolean checkAccess(String username, String password) {
        if (USERNAME.equals(username) && PASSWORD.equals(PasswordHelper.password(password))) {
            return true;
        }

        return false;
    }

    protected static String getUnencryptedPasswd() {
        return UNENCRYPTED_PASSWD;
    }

    protected static String getUsername() {
        return USERNAME;

    }
}
