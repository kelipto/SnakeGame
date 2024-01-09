package Game;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AppleTest.class, GameMechanicsTest.class, SaveLoadManagerTest.class, SnakeTest.class })
public class AllTests {

}
