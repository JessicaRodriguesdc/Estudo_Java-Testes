// Generated by Selenium IDE
package com.inove.tests;

import org.junit.Test;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
import java.util.*;
//import java.net.MalformedURLException;
//import java.net.URL;

public class CT01ValidarLayoutTest {
	
  private WebDriver driver;
  @SuppressWarnings("unused")
  private Map<String, Object> vars;
  JavascriptExecutor js;
  private final String pathChromeDriver = "C:\\Program Files\\Selenium\\chromedriver.exe";
//  private final String pathChromeDriver = "C:/Program Files/Selenium/chromedriver.exe"; 
  
  @Before
  public void setUp() {
//	System.setProperty("webdriver.chrome.driver", pathChromeDriver);
	System.setProperty("webdriver.chrome.whitelistedIps", pathChromeDriver);      
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  
  @After
  public void tearDown() {
    driver.quit();
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void cT01ValidarLayout() {
    driver.get("https://livros.inoveteste.com.br//contato");
    assertThat(driver.findElement(By.cssSelector("h1")).getText(),is("Envie uma mensagem"));
    
    {
      List<WebElement> elements = driver.findElements(By.name("your-name"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.name("your-email"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.name("your-subject"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.name("your-message"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("input.wpcf7-form-control"));
      assert(elements.size() > 0);
    }
  }
}
