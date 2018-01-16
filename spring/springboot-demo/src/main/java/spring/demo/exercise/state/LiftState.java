package spring.demo.exercise.state;

/**
 * Created by wangfacheng on 2018-01-16.
 */
public abstract class LiftState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();

}
