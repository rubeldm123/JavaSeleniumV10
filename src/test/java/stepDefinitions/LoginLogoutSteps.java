package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

public class LoginLogoutSteps {
    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 15);

    @Given("the User is on the login page")
    public void userIsOnTheLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("the User logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Username')]")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Password')]")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-button")));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginButton.click();
    }

    @Then("the User should see the dashboard")
    public void userShouldSeeTheDashboard() {
        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-topbar-header")));
        assert dashboardHeader.isDisplayed() : "Dashboard is not displayed!";
    }

    @When("the User logs out")
    public void userLogsOut() {
        WebElement dropdownIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'oxd-userdropdown-icon')]")));
        dropdownIcon.click();
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        logoutButton.click();
    }

    @Then("the User should return to the login page")
    public void userShouldReturnToTheLoginPage() {
        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Username')]")));
        assert loginPage.isDisplayed() : "Login page is not displayed!";
        DriverFactory.closeDriver();
    }
}
