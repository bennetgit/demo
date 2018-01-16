package spring.demo.exercise.state;

/**
 * Created by wangfacheng on 2018-01-16.
 */
public class OpenState extends LiftState {

    @Override
    public void open() {
        System.out.println("电梯门开启");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.closeState);
        super.context.getLiftState().close();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
