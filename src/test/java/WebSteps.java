import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Open the main page")
    public void openMainPage(){
        open("https://github.com");
    }
    @Step("Search the Repo: {repo}")
    public void searchRepo(String repo){
        $("[name=q]").setValue(repo).pressEnter();
    }
    @Step("Proceed to the: {repo}")
    public void proceedToRepo(String repo){
        $(linkText(repo)).click();
    }
    @Step("Click on Issue Tab")
    public void clickOnIssueTab(){
        $("#issues-tab").click();
    }
    @Step("Assert that issue #{issue} is appeared")
    public void checkAppearanceOfIssue(int issue){
        $(withText("#" + issue)).should(Condition.appear);
    }
}
