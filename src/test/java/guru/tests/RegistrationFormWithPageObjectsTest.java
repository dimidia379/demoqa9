package guru.tests;

import guru.pages.RegistrationPage;
import org.junit.jupiter.api.Test;
import java.io.File;
import com.github.javafaker.Faker;

public class RegistrationFormWithPageObjectsTest extends TestBase{
    RegistrationPage registrationPage = new RegistrationPage();

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String fullName = firstName + " " + lastName;
    String email = faker.bothify("????##@gmail.com");
    String phoneNumber = faker.numerify("##########");
    String address = faker.address().streetAddress();
    String gender = faker.options().option("Female", "Male", "Other");
    String subject = faker.options().option("Maths", "Computer Science", "Physics");
    String hobby = faker.options().option("Music", "Reading", "Sports");

    String birthDay = "30";
    String birthMonth = "January";
    String birthYear = "2000";
    String dateOfBirth = birthDay + " " + birthMonth + "," + birthYear;
    String state = "NCR";
    String city = "Delhi";
    String stateAndCity = state + " " + city;

    File file = new File("src/test/java/resources/unnamed.jpg");
    String fileName = "unnamed.jpg";

    @Test
    void formTestWithPO() {
        registrationPage.openPage();
        registrationPage.calendar.setDate(birthDay, birthMonth, birthYear);

        registrationPage
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .typePhoneNumber(phoneNumber)
                .setGender(gender)
                .setHobby(hobby)
                .chooseSubjects(subject)
                .uploadPicture(file)
                .typeAddress(address)
                .setState(state)
                .setCity(city)
                .submitForm()
                .checkResultsValue("Student Name", fullName)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Gender", gender)
                .checkResultsValue("Mobile", phoneNumber)
                .checkResultsValue("Date of Birth", dateOfBirth)
                .checkResultsValue("Subjects", subject)
                .checkResultsValue("Hobbies", hobby)
                .checkResultsValue("Picture", fileName)
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", stateAndCity);
    }
}
