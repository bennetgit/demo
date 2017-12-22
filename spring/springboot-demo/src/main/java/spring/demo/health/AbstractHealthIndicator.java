package spring.demo.health;

public abstract class AbstractHealthIndicator implements HealthIndicator {

    @Override
    public final Health health() {
        Health.Builder builder = new Health.Builder();
        try {
            doHealthCheck(builder);
        } catch (Exception ex) {
            builder.down(ex);
        }
        return builder.build();
    }

    /**
     * Actual health check logic.
     *
     * @param builder
     *            the {@link Builder} to report health status and details
     * @throws Exception
     *             any {@link Exception} that should create a
     *             {@link Status#DOWN} system status.
     */
    protected abstract void doHealthCheck(Health.Builder builder) throws Exception;
}