package edu.uclm.esi.videochat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.uclm.esi.videochat.model.User;
import edu.uclm.esi.videochat.springdao.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LlamadaAceptacionTest {
		 private WebDriver chrome;
		  private WebDriver firefox;
		  private WebDriver edge;
		 
		  
		  @Autowired
		  UserRepository userRepo; 
		  
		  private Map<String, Object> vars;
		  JavascriptExecutor js;
		  
		  @Before
		  public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Antonio Rubio\\Documents\\universidad\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Antonio Rubio\\Documents\\universidad\\Drivers\\geckodriver.exe");
			System.setProperty("webdriver.edge.driver", "C:\\Users\\Antonio Rubio\\Documents\\universidad\\Drivers\\msedgedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--use-fake-ui-for-media-stream");
			options.addArguments("--use-fake-device-for-media-stream");
			chrome = new ChromeDriver(options);
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("media.navigator.streams.fake",true);
			
			firefox = new FirefoxDriver(firefoxOptions);
			
			EdgeOptions edgeOptions = new EdgeOptions ();
			
			edgeOptions.setCapability("unhandledPromptBehavior", "accept");
			
			edge = new EdgeDriver(edgeOptions);
			
		    js = (JavascriptExecutor) chrome;
		    vars = new HashMap<String, Object>();
		  }
		  @After
		  public void tearDown() {
		    chrome.quit();
		    firefox.quit();
		    edge.quit();
		  }
		  
		  @Test
		  @Order(1)
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
		    firefox.get("https://localhost:7500");
		    edge.get("https://localhost:7500");
		    
		    try {
		  		Thread.sleep(2000);
		  	} catch (InterruptedException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
		    
//		    chrome.findElement(By.id("details-button")).click();
//		    chrome.findElement(By.id("proceed-link")).click();
		    
		    
		    WebElement linkChrome = chrome.findElement(By.linkText("Crear cuenta"));
		    linkChrome.click();
		    
		    WebElement cajaPwdChrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[3]"));
		    WebElement cajaPwd2Chrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[4]"));
		    WebElement cajaEmailChrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[2]"));
		    WebElement cajaNombreChrome= chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[1]"));
		    WebElement uploadPhotoChrome= chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[5]"));

		    cajaNombreChrome.sendKeys("Pepe");
		    cajaEmailChrome.sendKeys("pepe@gmail.com");
		    cajaPwdChrome.sendKeys("1234");
		    cajaPwd2Chrome.sendKeys("1234");
		    uploadPhotoChrome.sendKeys("C:\\ARM\\Fotos\\Pepe.jpg");
		    
		  
		    WebElement botonChrome = chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/button"));
		    botonChrome.click();
		    
		    WebElement linkFirefox = firefox.findElement(By.linkText("Crear cuenta"));
		    linkFirefox.click();
		    
		    WebElement cajaPwdFirefox = firefox.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[3]"));
		    WebElement cajaPwd2Firefox = firefox.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[4]"));
		    WebElement cajaEmailFirefox = firefox.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[2]"));
		    WebElement cajaNombreFirefox= firefox.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[1]"));
		    WebElement uploadPhotoFirefox= firefox.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[5]"));

		    cajaNombreFirefox.sendKeys("Ana");
		    cajaEmailFirefox.sendKeys("ana@gmail.com");
		    cajaPwdFirefox.sendKeys("1234");
		    cajaPwd2Firefox.sendKeys("1234");
		    uploadPhotoFirefox.sendKeys("C:\\ARM\\Fotos\\Pepe.jpg");
		    
		    
		   
		    
		    WebElement botonFirefox = firefox.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/button"));
		    botonFirefox.click();
		    
		    WebElement linkEdge = edge.findElement(By.linkText("Crear cuenta"));
		    linkEdge.click();
		    
		    WebElement cajaPwdEdge = edge.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[3]"));
		    WebElement cajaPwd2Edge = edge.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[4]"));
		    WebElement cajaEmailEdge = edge.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[2]"));
		    WebElement cajaNombreEdge= edge.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[1]"));
		    WebElement uploadPhotoEdge= edge.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/input[5]"));
		    
		    cajaNombreEdge.sendKeys("Lucas");
		    cajaEmailEdge.sendKeys("Lucas@gmail.com");
		    cajaPwdEdge.sendKeys("1234");
		    cajaPwd2Edge.sendKeys("1234");
		    uploadPhotoEdge.sendKeys("C:\\ARM\\Fotos\\Pepe.jpg");
		    
		  
		    
		    
		    WebElement botonEdge = edge.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div/button"));
		    botonEdge.click();
		    
		    
		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    Optional<User> pepeConfirm= userRepo.findByName("Pepe");
			Optional<User> anaConfirm= userRepo.findByName("Ana");
			Optional<User> lucasConfirm= userRepo.findByName("Lucas");
		    
		      try {
			Thread.sleep(2000);
		     } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		     }
		    
		    
		    assertThat(firefox.switchTo().alert().getText(), is("Registrado correctamente"));
		    assertThat(anaConfirm.isPresent(), is(true));
		    assertThat(edge.switchTo().alert().getText(), is("Registrado correctamente"));
		    assertThat(lucasConfirm.isPresent(), is(true));
		    assertThat(chrome.switchTo().alert().getText(), is("Registrado correctamente"));
		    assertThat(pepeConfirm.isPresent(), is(true));
		    //assertThat(chrome.findElement(By.cssSelector(".oj-hybrid-padding > h1")).getText(), is("Login"));
		  }
		  
		  @Test
		  @Order(2)
		  public void logins() {
			chrome.get("https://localhost:7500/");
			  
			 try {
			  		Thread.sleep(1000);
			  	} catch (InterruptedException e) {
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
			  	}
//		    chrome.findElement(By.id("details-button")).click();
//		    chrome.findElement(By.id("proceed-link")).click();
			 
			 
		    chrome.manage().window().setSize(new Dimension(1184, 683));
		    chrome.findElement(By.cssSelector(".oj-hybrid-padding")).click();
		    chrome.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).clear();
		    chrome.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).sendKeys("Pepe");
		    chrome.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).clear();
		    chrome.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).sendKeys("1234");
		    chrome.findElement(By.cssSelector("button")).click();
		    
		    try {
		  		Thread.sleep(1000);
		  	} catch (InterruptedException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	};
		    
		    assertThat(chrome.findElement(By.cssSelector(".oj-hybrid-padding > h1")).getText(), is("Fantástico videochat"));
		    
		    firefox.get("https://localhost:7500/");
		    
		    try {
		  		Thread.sleep(1000);
		  	} catch (InterruptedException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
		    
		    firefox.manage().window().setSize(new Dimension(1184, 683));
		    firefox.findElement(By.cssSelector(".oj-hybrid-padding")).click();
		    firefox.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).clear();
		    firefox.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).sendKeys("Lucas");
		    firefox.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).clear();
		    firefox.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).sendKeys("1234");
		    firefox.findElement(By.cssSelector("button")).click();
		    
		    try {
		  		Thread.sleep(1000);
		  	} catch (InterruptedException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
		    
		    
		    assertThat(firefox.findElement(By.cssSelector(".oj-hybrid-padding > h1")).getText(), is("Fantástico videochat"));

		    edge.get("https://localhost:7500/");
		    
		    try {
		  		Thread.sleep(1000);
		  	} catch (InterruptedException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
		    
		    edge.manage().window().setSize(new Dimension(1184, 683));
		    edge.findElement(By.cssSelector(".oj-hybrid-padding")).click();
		    edge.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).clear();
		    edge.findElement(By.cssSelector(".oj-sm-12:nth-child(1) > input")).sendKeys("Ana");
		    edge.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).clear();
		    edge.findElement(By.cssSelector(".oj-sm-12:nth-child(2) > input")).sendKeys("1234");
		    edge.findElement(By.cssSelector("button")).click();
		    
		    try {
		  		Thread.sleep(1000);
		  	} catch (InterruptedException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
		    
		    assertThat(edge.findElement(By.cssSelector(".oj-hybrid-padding > h1")).getText(), is("Fantástico videochat"));

		    
		  }
		  @Test
		  @Order(3)
		  public void LlamadaAceptacion() {
			  
			 hacerLogin(chrome, "Ana", "1234");
			 hacerLogin(firefox, "Lucas", "1234");
			 
			 try {
			  		Thread.sleep(5000);
			  	} catch (InterruptedException e) {
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
			  	}
			 
			 chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[3]/div[1]/div[2]/button")).click();
			 try {
			  		Thread.sleep(2000);
			  	} catch (InterruptedException e) {
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
			  	}
			 firefox.switchTo().alert().accept();
			 try {
			  		Thread.sleep(1000);
			  	} catch (InterruptedException e) {
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
			  	}
			 String videoRemoto =  chrome.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[4]/div[2]")).getText();
			 System.out.println(videoRemoto);
			 assertThat(videoRemoto.contains("Llamada aceptada"), is(true));
			  
		  }
		  
		  private void hacerLogin(WebDriver driver, String nombre, String contraseña) {
			  driver.get("https://localhost:7500/");

			  //  chrome.findElement(By.id("details-button")).click();
			  //  chrome.findElement(By.id("proceed-link")).click();
			  try {
			  		Thread.sleep(2000);
			  	} catch (InterruptedException e) {
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
			  	}

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
