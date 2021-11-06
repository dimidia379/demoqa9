package guru.pages;

import com.codeborne.selenide.SelenideElement;
import guru.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    //locators and elements
    private final String FORM_TITLE = "Student Registration Form";

    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadio = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbyCheckbox = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateChoice = $("#state"),
            cityChoice = $("#city"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive");

    public CalendarComponent calendar = new CalendarComponent();


    //actions
    public RegistrationPage openPage(){
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage typeFirstName(String firstName){
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName){
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email){
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage typePhoneNumber(String phoneNumber){
        phoneNumberInput.setValue(phoneNumber);
        return this;
    }

    public RegistrationPage setGender(String gender){
        genderRadio.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage chooseSubjects(String subject){
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby){
        hobbyCheckbox.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(File file){
        uploadPictureInput.uploadFile(file);
        return this;
    }

    public RegistrationPage typeAddress(String address){
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state){
        stateChoice.click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city){
        cityChoice.click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage setBirthday(String day, String month, String year){
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage submitForm(){
        submitButton.click();
        return this;
    }

    public RegistrationPage checkResultsValue(String key, String value) {
        resultsTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }


}

