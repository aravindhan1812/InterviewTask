package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.SignUp;
import java.time.Duration;

public class SignupSteps {
	WebDriver driver;
	SignUp signupPage;
	WebDriverWait wait;

	@Given("I am on the signup page")
	public void i_am_on_the_signup_page() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
		signupPage = new SignUp(driver);
	}

	@Then("I should see all required fields")
	public void i_should_see_all_required_fields() {
		Assert.assertTrue(signupPage.getFirstName().isDisplayed());
		Assert.assertTrue(signupPage.getLastName().isDisplayed());
		Assert.assertTrue(signupPage.getEmail().isDisplayed());
		Assert.assertTrue(signupPage.getPassword().isDisplayed());
		Assert.assertTrue(signupPage.getConfirmPassword().isDisplayed());
	}

	@When("I click on Create Account")
	public void i_click_on_create_account() {
		signupPage.clickCreateAccount();
	}

	@Then("I should see error messages for required fields")
	public void i_should_see_error_messages_for_required_fields() {
		Assert.assertTrue(signupPage.getErrorMessagesText().contains("This is a required field."));
	}

	@When("I enter {string} as first name")
	public void i_enter_first_name(String firstName) {
		signupPage.enterFirstName(firstName);
	}

	@When("I enter {string} as last name")
	public void i_enter_last_name(String lastName) {
		signupPage.enterLastName(lastName);
	}

	@When("I enter a unique email")
	public void i_enter_unique_email() {
		String uniqueEmail = "test" + System.currentTimeMillis() + "@test.com";
		signupPage.enterEmail(uniqueEmail);
	}

	@When("I enter {string} as email")
	public void i_enter_email(String email) {
		signupPage.enterEmail(email);
	}

	@When("I enter an existing email")
	public void i_enter_existing_email() {
		signupPage.enterEmail("existinguser@test.com");
	}

	@When("I enter {string} as password")
	public void i_enter_password(String password) {
		signupPage.enterPassword(password);
	}

	@When("I confirm {string} as password")
	public void i_confirm_password(String confirmPassword) {
		signupPage.enterConfirmPassword(confirmPassword);
	}

	@Then("I should see an error message {string}")
	public void i_should_see_an_error_message(String expectedMessage) {
		System.out.println(signupPage.getErrorMessagesText());
		Assert.assertTrue(signupPage.getErrorMessagesText().contains(expectedMessage));
	}

	@Then("I should see a password error message {string}")
	public void i_should_see_a_password_error_message(String expectedMessage) {
		System.out.println(signupPage.getPasswordConfirmationError());
		Assert.assertTrue(signupPage.getPasswordConfirmationError().contains(expectedMessage));
	}

	@Then("I should be redirected to the account dashboard")
	public void i_should_be_redirected_to_the_account_dashboard() {
		Assert.assertTrue(driver.getCurrentUrl().contains("customer/account"));
		driver.quit();
	}

	@When("I enter SQL injection in email field")
	public void i_enter_sql_injection() {
		signupPage.enterEmail("test@test.com' OR '1'='1");
	}

	@When("I enter XSS script in email field")
	public void i_enter_xss_script() {
		signupPage.enterEmail("<script>alert('XSS')</script>");
	}

	@Then("I close the page")
	public void quit() {
		driver.close();
		driver.quit();
	}

	@Then("I should see password strength as {string}")
	public void i_should_see_password_strength(String strength) {
		System.out.println(signupPage.getPasswordStrength());
		Assert.assertTrue(signupPage.getPasswordStrength().contains(strength));
	}

	@Then("I should see an error alert {string}")
	public void i_should_see_an_error_alert(String expectedMessage) {
		System.out.println(signupPage.getErrorAlert());
		Assert.assertTrue(signupPage.getErrorAlert().contains(expectedMessage));
	}

}
