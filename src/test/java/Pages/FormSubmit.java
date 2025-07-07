package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormSubmit {
    public WebDriver driver;
    public FormSubmit(WebDriver driver) {
        this.driver = driver;
    }

    public By $nameField(){
        return By.xpath("//input[@name='name'][@required]");
    }
    public By $emailField(){
        return By.xpath("/html/body/app-root/form-comp/div/form/div[2]/input");
    }
    public By $passwordField(){
        return By.xpath("//*[@id=\"exampleInputPassword1\"]");
    }
    public By $genderField(){
        return By.cssSelector("select#exampleFormControlSelect1");
    }
    public By $dateField(){
        return By.xpath("/html/body/app-root/form-comp/div/form/div[7]/input");
    }
    public By $employmentStatus(String value){
        return By.xpath("//label[contains(text(), \""+value+"\")]/preceding-sibling::input");
    }
    public By $submitButton(){
        return By.cssSelector("[value=\"Submit\"]");
    }
    public By $successMessage(){
        return By.cssSelector("div.alert-success");
    }


    public void submitForm(String name, String email, String password,
                           String date, String gender, String empStatus){
        WebElement nameField = driver.findElement(this.$nameField());
        assert(nameField.isDisplayed());
        nameField.sendKeys(name);
        WebElement emailField = driver.findElement(this.$emailField());
        assert(emailField.isDisplayed());
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(this.$passwordField());
        assert(passwordField.isDisplayed());
        passwordField.sendKeys(password);
        WebElement $dateField = driver.findElement(this.$dateField());
        assert($dateField.isDisplayed());
        $dateField.sendKeys(date);
        Select select = new Select(driver.findElement(this.$genderField()));
        select.selectByVisibleText(gender);
        WebElement employmentStatus =  driver.findElement(this.$employmentStatus(empStatus));
        assert(employmentStatus.isDisplayed());
        employmentStatus.click();
        WebElement submitButton = driver.findElement(this.$submitButton());
        assert(submitButton.isDisplayed());
        submitButton.click();
    }

    public String getSuccessMessage(){
        WebElement successMessage = driver.findElement(this.$successMessage());
        return successMessage.getText();
    }
}
