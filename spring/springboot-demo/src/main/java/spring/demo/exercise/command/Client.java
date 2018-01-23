package spring.demo.exercise.command;

/**
 * Created by wangfacheng on 2018-01-23.
 */
public class Client {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Command command = new AddRequirementCommand();

        invoker.setCommand(command);
        invoker.action();
    }
}
