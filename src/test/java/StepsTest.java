import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    public final String REPO = "gaynanovadr/10_guru_Allure";
    public final int ISSUE = 2;

    @Test
    @DisplayName("Check the issue number with Listener")
    void issueSearchTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $("[name=q]").setValue(REPO).pressEnter();
        $(linkText(REPO)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).should(Condition.appear);
    }

    @Test
    @DisplayName("Check the issue number with lambda-steps")
    void issueSearchLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the main page", () -> {
            open("https://github.com");
        });
        step("Search the Repo: " + REPO, () -> {
            $("[name=q]").setValue(REPO).pressEnter();
        });
        step("Proceed to the: " + REPO, () -> {
            $(linkText(REPO)).click();
        });
        step("Click on Issue Tab", () -> {
            $("#issues-tab").click();
        });
        step("Assert that Issue #" + ISSUE + " is appeared", () -> {
            $(withText("#" + ISSUE)).should(Condition.appear);
        });
    }

    @Test
    @DisplayName("Check the issue number with Annotation steps")
    @Feature("Search for Issue in Repo")
    @Owner("Dinara")
    @Link(value = "test", url = "https://github.com")
    void issueSearchAnnotatedStepsTest (){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.searchRepo(REPO);
        webSteps.proceedToRepo(REPO);
        webSteps.clickOnIssueTab();
        webSteps.checkAppearanceOfIssue(ISSUE);
    }
}