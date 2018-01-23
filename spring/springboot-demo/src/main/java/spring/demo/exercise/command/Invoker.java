package spring.demo.exercise.command;

/**
 * Created by wangfacheng on 2018-01-23.
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }
}
