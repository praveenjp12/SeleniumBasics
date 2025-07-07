package Tests;

import Pages.FormSubmit;
import Utilities.ReadExcelData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class SampleTest extends SetUp {

    public void openGoogle(){
        ArrayList<HashMap<String, String>> data =  ReadExcelData.getData();
        FormSubmit formSubmit = new FormSubmit(driver);
        for(HashMap<String, String> Data : data){
            formSubmit.submitForm(
                    Data.get("username"),
                    Data.get("email"),
                    Data.get("password"),
                    Data.get("dateofbirth"),
                    Data.get("gender"),
                    Data.get("employmentstatus")
            );
        }
//        formSubmit.submitForm(
//                "Pranav J",
//                "test@test.com",
//                "pjpjjpj",
//                "05-08-2004",
//                "Female",
//                "Student"
//        );
        String successMessage = formSubmit.getSuccessMessage();
        assert(successMessage.contains("Success!"));
        assert(successMessage.contains("The Form has been submitted successfully!."));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dataProvider="SubmitForm")
    public void submitForm(String username, String email, String password,
                           String dateofbirth, String gender, String employmentstatus){
        FormSubmit formSubmit = new FormSubmit(driver);
        formSubmit.submitForm(
                username, email, password, dateofbirth, gender, employmentstatus
        );
        String successMessage = formSubmit.getSuccessMessage();
        assert(successMessage.contains("Success!"));
        assert(successMessage.contains("The Form has been submitted successfully!."));
        driver.navigate().refresh();
    }

    @DataProvider
    public Object[][] SubmitForm(){
        return ReadExcelData.inputData("InputData");
    }
}
