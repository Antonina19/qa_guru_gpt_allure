package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("antonina")
@Feature("Test for page 'Text-box'")
public class TextBoxTests extends TestBase {

    @Test
    @DisplayName("Text box can be filled")
    @Story("Text box form")
    @Tags({@Tag("automated"), @Tag("web")})
    void fillFormTest() {
        step("Open page /text-box", () -> {
            open("/text-box");

            if($(".fc-dialog-content").isDisplayed())
                $(byText("Consent")).click();
        });
        step("Fill form", () -> {
            $("#userName").setValue("Alex");
            $("#userEmail").setValue("alex@egorov.com");
            $("#currentAddress").setValue("Some street 1");
            $("#permanentAddress").setValue("Another street 1");
            $("#submit").click();
        });
        step("Check results", () -> {
            $("#output #name").shouldHave(text("Alex"));
            $("#output #email").shouldHave(text("alex@egorov.com"));
            $("#output #currentAddress").shouldHave(text("Some street 1"));
            $("#output #permanentAddress").shouldHave(text("Another street 1"));
        });
    }
    @Test
    @DisplayName("Check title of page")
    @Story("Text box form")
    @Tags({@Tag("automated"), @Tag("web")})
    void titleTest() {
        step("Open page /text-box", () -> open("/text-box"));
        step("Title of page equals DEMOQA", () -> {
            String expectedTitle = "DEMOQA";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }
}