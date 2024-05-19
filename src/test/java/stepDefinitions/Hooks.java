package stepDefinitions;


import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks //Unlike in TestNG Framework, we cannot directly specify annotations on base class.So hooks class is created separately


{

	 WebDriver driver;
	 Properties p;
     
	@Before
    public void setup() throws IOException
    {
    	driver=BaseClass.initilizeBrowser();
    	    	
    	p=BaseClass.getProperties();
    	driver.get(p.getProperty("appURL"));
    	driver.manage().window().maximize();
    		
	}
		
    
    @After
    public void tearDown(Scenario scenario)
    {
        	
       driver.quit();
       
    }
    

    @AfterStep
    public void addScreenshot(Scenario scenario) 
    {
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) 
        {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        }
      
    }
   
}