package guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void formTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").sendKeys("Jessica");
        $("#lastName").sendKeys("Jones");
        $("#userEmail").sendKeys("jessica@test.test");
        $x("//label[@for=\"gender-radio-2\"]").click();
        $("#userNumber").sendKeys("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("January")).click();
        $(".react-datepicker__year-select").click();
        $(byText("2000")).click();
        $x("//div[@aria-label=\"Choose Sunday, January 30th, 2000\"]").click();
        $("#subjectsInput").sendKeys("Maths");
        $("#subjectsInput").pressEnter();
        $x("//label[@for=\"hobbies-checkbox-3\"]").click();
        File file = new File("src/test/java/resources/unnamed.jpg");
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").sendKeys("Test Address, 123");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $x("//td[text()=\"Student Name\"]/following-sibling::td").shouldHave(Condition.exactText("Jessica Jones"));
        $x("//td[text()=\"Student Email\"]/following-sibling::td").shouldHave(Condition.exactText("jessica@test.test"));
        $x("//td[text()=\"Gender\"]/following-sibling::td").shouldHave(Condition.exactText("Female"));
        $x("//td[text()=\"Mobile\"]/following-sibling::td").shouldHave(Condition.exactText("1234567890"));
        $x("//td[text()=\"Date of Birth\"]/following-sibling::td").shouldHave(Condition.exactText("30 January,2000"));
        $x("//td[text()=\"Subjects\"]/following-sibling::td").shouldHave(Condition.exactText("Maths"));
        $x("//td[text()=\"Hobbies\"]/following-sibling::td").shouldHave(Condition.exactText("Music"));
        $x("//td[text()=\"Picture\"]/following-sibling::td").shouldHave(Condition.exactText("unnamed.jpg"));
        $x("//td[text()=\"Address\"]/following-sibling::td").shouldHave(Condition.exactText("Test Address, 123"));
        $x("//td[text()=\"State and City\"]/following-sibling::td").shouldHave(Condition.exactText("NCR Delhi"));
    }
}
