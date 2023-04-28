import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/placingShips.feature", "src/test/resources/opponentViews.feature"}, publish = true)
public class CucumberTest {

}