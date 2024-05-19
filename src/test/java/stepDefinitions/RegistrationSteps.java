package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import org.junit.Assert;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.datatable.DataTable;

public class RegistrationSteps {
	
	WebDriver driver;
	HomePage hp;
	RegistrationPage rp;

@Given("the user navigates to Register Account page")
public void the_user_navigates_to_register_account_page() 
{
	hp=new HomePage(BaseClass.getDriver());
	
	hp.clickMyAccount();
	hp.clickRegister();
    
}

@When("the user enters the details into below fields")
public void the_user_enters_the_details_into_below_fields(DataTable dTable) 
{
	
	Map<String, String> dMap = dTable.asMap(String.class,String.class);
	rp=new RegistrationPage(BaseClass.getDriver());
	
	rp.setFirstName(dMap.get("firstName"));
	rp.setLastName(dMap.get("lastName"));
	rp.setEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");
	rp.setPassword(dMap.get("password"));
	rp.setConfirmPassword(dMap.get("password"));
	
	
}

@When("the user selects Privacy Policy")
public void the_user_selects_privacy_policy() 
{
   rp.setPrivacyPolicy();
}

@When("the user clicks on Continue button")
public void the_user_clicks_on_continue_button() 
{
	rp.clickContinue();
}

@Then("the user account should get created successfully")
public void the_user_account_should_get_created_successfully() 
{
   Assert.assertEquals(rp.getConfirmationMsg(),"Your Account Has Been Created!");
}




}
