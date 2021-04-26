package edu.uclm.esi.videochat;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.hamcrest.core.Is;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.html.HTMLElement;

import edu.uclm.esi.videochat.model.Token;
import edu.uclm.esi.videochat.model.User;
import edu.uclm.esi.videochat.springdao.TokenRepository;
import edu.uclm.esi.videochat.springdao.UserRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegisterTestJava {
	
	
  private WebDriver chrome;
 
  @Autowired
  UserRepository userRepo; 
  @Autowired
  TokenRepository tokenRepo;
  
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\irr-9\\Desktop\\Selenium\\chromedriver.exe");
	
	chrome = new ChromeDriver();
   
    
    js = (JavascriptExecutor) chrome;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    chrome.quit();
    
  }
    @Test
  public void registroTres() {
	  
	  Optional<User> pepe= userRepo.findByName("Pepe");
	  Optional<User> ana= userRepo.findByName("Ana");
	  Optional<User> lucas= userRepo.findByName("Lucas");
	  
	  if(pepe.isPresent())
		  userRepo.deleteById(pepe.get().getId());
	  if(ana.isPresent())
		  userRepo.deleteById(ana.get().getId());
	  if(lucas.isPresent())
		  userRepo.deleteById(lucas.get().getId());
	  
	  
	    
	  
	     try {
	   		Thread.sleep(2000);
	   	} catch (InterruptedException e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   	}
	  
    chrome.get("https://localhost:7500");
    
   
    
//    chrome.findElement(By.id("details-button")).click();
//    chrome.findElement(By.id("proceed-link")).click();
    
    
    WebElement linkChrome = chrome.findElement(By.linkText("Crear cuenta"));
    linkChrome.click();
    
    WebElement cajaPwdChrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[3]"));
    WebElement cajaPwd2Chrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[4]"));
    WebElement cajaEmailChrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[2]"));
    WebElement cajaNombreChrome= chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[1]"));
    

    cajaNombreChrome.sendKeys("Pepe");
    cajaEmailChrome.sendKeys("pepe@gmail.com");
    cajaPwdChrome.sendKeys("1234");
    cajaPwd2Chrome.sendKeys("1234");
   
    
  
    WebElement botonChrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/button"));
    botonChrome.click();
    
    
   
    
    
   
    
    
    
    
    
  
    
    
  
    
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
   
    
//    chrome.get("https://localhost:7500/users/confirmarCuenta?tokenId=" + tokenIdChrome.getId() );
//    firefox.get("https://localhost:7500/users/confirmarCuenta?tokenId=" + tokenIdFirefox.getId() );
//    edge.get("https://localhost:7500/users/confirmarCuenta?tokenId=" + tokenIdEdge.getId() );
    
    assertThat(chrome.switchTo().alert().getText(), is("Registrado correctamente"));
   
    //assertThat(chrome.findElement(By.cssSelector(".oj-hybrid-padding > h1")).getText(), is("Login"));
  }
  
  public void llamadaRechazo() {
	  hacerLogin(chrome, "Pepe", "1234");
	  
	  
	  try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  WebElement poci = chrome.findElement(By.linkText(""));
	  poci.click();
	  
	  

  }
  
  public void llamadaAceptacion() {
	  
  }
  
  private void hacerLogin(WebDriver driver, String nombre, String contraseña) {
	  driver.get("https://localhost:7500/");

	  //  chrome.findElement(By.id("details-button")).click();
	  //  chrome.findElement(By.id("proceed-link")).click();


	  driver.manage().window().setSize(new Dimension(1184, 683));
	  driver.findElement(By.cssSelector(".oj-hybrid-padding")).click();
	  driver.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).clear();
	  driver.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).sendKeys(nombre);
	  driver.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).clear();
	  driver.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).sendKeys(contraseña);
	  driver.findElement(By.cssSelector("button")).click();

	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


  }
  
  
  
}