package spring.demo.exercise.state;

/**
 * Created by wangfacheng on 2018-01-16.
 */
public class Context {

    public static final OpenState openState = new OpenState();

    public static final CloseState closeState = new CloseState();

    public static final RunState runState = new RunState();

    public static final StopState stopState = new StopState();

    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }
}
