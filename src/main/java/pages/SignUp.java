package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "email")
	private WebElement sign_in_email;
	
	@FindBy(id = "pass")
	private WebElement sign_in_password;
	
	@FindBy(id = "send2")
	private WebElement sign_in_button;

	@FindBy(id = "firstname")
	private WebElement firstName;

	@FindBy(id = "lastname")
	private WebElement lastName;

	@FindBy(id = "email_address")
	private WebElement email;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "password-confirmation")
	private WebElement confirmPassword;

	@FindBy(xpath = "//button[@title='Create an Account']")
	private WebElement createAccountButton;

	@FindBy(xpath = "//*[@class='mage-error']")
	private List<WebElement> errorMessages;

	@FindBy(id = "password-strength-meter-label")
	private WebElement passwordStrength;

	@FindBy(id = "password-confirmation-error")
	private WebElement passwordConfirmationError;

	@FindBy(xpath = "//div[@class='messages']/div/div")
	private WebElement errorAlert;

	public SignUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));

	}

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getConfirmPassword() {
		return confirmPassword;
	}

	public void enterFirstName(String fName) {
		firstName.sendKeys(fName);
	}

	public void enterLastName(String lName) {
		lastName.sendKeys(lName);
	}

	public void enterEmail(String emailValue) {
		email.sendKeys(emailValue);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void enterConfirmPassword(String confirmPwd) {
		confirmPassword.sendKeys(confirmPwd);
	}

	public void clickCreateAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
		createAccountButton.click();
	}

	public List<String> getErrorMessagesText() {
		return errorMessages.stream().map(WebElement::getText).toList();
	}

	public String getErrorAlert() {
		return errorAlert.getText();
	}

	public String getPasswordStrength() {
		return passwordStrength.getText();
	}

	public String getPasswordConfirmationError() {
		return passwordConfirmationError.getText();
	}
	
	public void enterSignInEmail(String emailValue) {
		sign_in_email.sendKeys(emailValue);
	}

	public void enterSignInPassword(String pwd) {
		sign_in_password.sendKeys(pwd);
	}
	
	public void clickSignIn() {
		sign_in_button.click();
		
	}

}
