package guru.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import guru.pages.RegistrationPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;


public class RegistrationFormWithPageObjectsTest extends TestBase{
    RegistrationPage registrationPage = new RegistrationPage();

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.bothify("????##@gmail.com");
    String phoneNumber = faker.numerify("##########");
    String address = faker.address().streetAddress();
    String gender = faker.options().option("Female", "Male", "Other");
    String subject = faker.options().option("Maths", "Computer Science", "Physics");
    String hobby = faker.options().option("Music", "Reading", "Sports");

    String birthDay = "30";
    String birthMonth = "January";
    String birthYear = "2000";
    String state = "NCR";
    String city = "Delhi";

    File file = new File("src/test/java/resources/unnamed.jpg");
    String fileName = "unnamed.jpg";


    Map<String, String> expectedData = new HashMap<>()
    {{
        put("Student Email", email);
        put("Gender", gender);
        put("Student Name", firstName + " " + lastName);
        put("Mobile", phoneNumber);
        put("Date of Birth", birthDay + " " + birthMonth + "," + birthYear);
        put("Subjects", subject);
        put("Hobbies", hobby);
        put("Picture", fileName);
        put("Address", address);
        put("State and City", state + " " + city);
    }};


    @Test
    void formTestWithPO() {
        registrationPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .typePhoneNumber(phoneNumber)
                .setBirthday(birthDay, birthMonth, birthYear)
                .setGender(gender)
                .setHobby(hobby)
                .chooseSubjects(subject)
                .uploadPicture(file)
                .typeAddress(address)
                .setState(state)
                .setCity(city)
                .submitForm();

        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();

        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement line: lines) {
            String key = line.$("td").text();
            String expectedValue = expectedData.get(key);
            String actualValue = line.$("td", 1).text();

            softly.assertThat(actualValue)
                    .as(format("Result in line %s was %s, but expected %s", key, actualValue, expectedValue))
                    .isEqualTo(expectedValue);
        }
        softly.assertAll();
    }
}
