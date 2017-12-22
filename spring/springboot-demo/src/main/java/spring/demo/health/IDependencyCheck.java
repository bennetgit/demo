package spring.demo.health;

/**
 * Created by jay on 9/18/16.
 */
public interface IDependencyCheck {

    Health dependencyCheck(String username, String password);
}
