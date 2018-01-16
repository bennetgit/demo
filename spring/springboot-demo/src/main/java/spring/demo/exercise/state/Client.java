package spring.demo.exercise.state;

/**
 * Created by wangfacheng on 2018-01-16.
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new CloseState());

        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
