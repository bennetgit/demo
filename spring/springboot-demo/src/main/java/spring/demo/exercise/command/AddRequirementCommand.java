package spring.demo.exercise.command;

/**
 * Created by wangfacheng on 2018-01-23.
 */
public class AddRequirementCommand extends Command {

    @Override
    public void execute() {
        rg.find();
        rg.add();
        rg.plan();
    }
}
