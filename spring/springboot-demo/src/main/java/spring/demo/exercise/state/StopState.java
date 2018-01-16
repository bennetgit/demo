package spring.demo.exercise.state;

/**
 * Created by wangfacheng on 2018-01-16.
 */
public class StopState extends LiftState {

    @Override
    public void open() {
        super.context.setLiftState(Context.openState);
        super.context.getLiftState().open();
    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止");
    }
}
