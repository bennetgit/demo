package spring.demo.exercise.command;

/**
 * Created by wangfacheng on 2018-01-23.
 */
public abstract class Command {

    protected PageGroup pg = new PageGroup();

    protected RequirementGroup rg = new RequirementGroup();

    public abstract void execute();
}
