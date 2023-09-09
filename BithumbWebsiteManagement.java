package com.AutoProject.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import org.monte.media.VideoFormatKeys;
import org.openqa.selenium.remote.CapabilityType;

public class Bithumb1 {
	
	private WebDriver driver;
    private WebDriverWait wait;
    private String baseURL = "https://www.bithumb.com/react/"; // website URL

    private ScreenRecorder screenRecorder;

    @BeforeClass
    public void setUp() throws Exception {
    	
    	System.setProperty("webdriver.chrome.driver", "My_Path\\chromedriver.exe");
    	
    	// Create a proxy object with your proxy server details
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("your_proxy_server:port");
        proxy.setSslProxy("your_proxy_server:port");
        
        // Set up Chrome options and capabilities to use the proxy
        ChromeOptions options = new ChromeOptions();
    
       options.setCapability(CapabilityType.PROXY, proxy);
        

        String[] userAgents = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Edge/94.0.992.50 Safari/537.36"
        };
        String randomUserAgent = userAgents[new Random().nextInt(userAgents.length)];
        options.addArguments("--user-agent=" + randomUserAgent);
        options.addArguments("--headless"); // Run Chrome in headless mode
        
        driver = new ChromeDriver();  
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));       

     // Configure the video recording using MonteMedia
        File videoFile = new File("C:\\Users\\sneha\\Documents\\Bithumb1-video\\recorded_video.mp4");
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        screenRecorder = new ScreenRecorder(gc, captureSize,
                new Format(VideoFormatKeys.MediaTypeKey, VideoFormatKeys.MediaType.FILE, VideoFormatKeys.MimeTypeKey, VideoFormatKeys.MIME_AVI),
                new Format(VideoFormatKeys.MediaTypeKey, VideoFormatKeys.MediaType.VIDEO, VideoFormatKeys.EncodingKey, VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        VideoFormatKeys.CompressorNameKey, VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        VideoFormatKeys.DepthKey, 24, VideoFormatKeys.FrameRateKey, Rational.valueOf(15),
                        VideoFormatKeys.QualityKey, 1.0f, VideoFormatKeys.KeyFrameIntervalKey, 15 * 60),
                new Format(VideoFormatKeys.MediaTypeKey, VideoFormatKeys.MediaType.VIDEO, VideoFormatKeys.EncodingKey, "black",
                        VideoFormatKeys.FrameRateKey, Rational.valueOf(30)),
                null, videoFile);
    } 
    
    @Test(priority = 1)
    public void openWebsite() {
        driver.get(baseURL);
        try {
    		Thread.sleep(2000); // wait for 5 seconds
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
       
        handleGetStartedPopUp();
        try {
    		Thread.sleep(2000); // wait for 5 seconds
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
        
        handlePopUp();
        try {
    		Thread.sleep(2000); // wait for 5 seconds
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
        
        // Perform the login immediately
        login();
        
        // Start the scheduled tasks after login
        startScheduledTasks();
        
        // Add a wait to ensure that tasks have time to execute
        try {
            Thread.sleep(8 * 60 * 60 * 1000); // Sleep for 8 hours
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
               
    }
    

    private void handleGetStartedPopUp() {
    	// Check if the Get Started pop-up is present
        try {
            WebElement getStartedPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("NoticePopupTitle_notice-title__u1ZAR"))); // Get Started pop-up element
            WebElement okButton = getStartedPopup.findElement(By.xpath("//span[text()='닫기']")); //"OK" button
            okButton.click();
        } catch (NoSuchElementException e) {
            // If the element is not found, no pop-up is present
        }
    }
    
    private void handlePopUp() {
    	// Check if the Get Started pop-up is present
        try {
            WebElement getStartedPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("FreezonModal_modal-freezon__image__gSgVV"))); // Get Started pop-up element
            WebElement okButton = getStartedPopup.findElement(By.xpath("//span[text()='오늘 그만 보기']")); //"OK" button
            okButton.click();
        } catch (NoSuchElementException e) {
            // If the element is not found, no pop-up is present
        }
    }
    
   @Test(priority = 2)
    public void login() {
    	
    	// Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Click the login button
        WebElement login = driver.findElement(By.linkText("로그인"));
    	login.click();
    	try {
    		Thread.sleep(2000); // wait for 5 seconds
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
   	   
    /*	// Enter username/email
        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.className("InputBox_input-box__label__H37dX")));	 
    	username.click();

    	try {
    		Thread.sleep(2000); // wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	
    	System.out.print("Enter User name as Email (or 'exit' to quit): ");
    	String uName = scanner.nextLine();
    	
    	// Check if the input is "exit" to quit
        if (uName.equalsIgnoreCase("exit")) {
            scanner.close(); // Close the scanner if user wants to exit
            return; // Exit the method
        }

    	username.clear(); // Clear any existing text in the search box
    	username.sendKeys(uName);      
    	username.sendKeys(Keys.ENTER);

        // Submit the user name
    	username.submit();
    	try {
    		Thread.sleep(2000); // wait for 2 seconds
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	
    	WebElement password = driver.findElement(By.name("password"));
    	password.click();
    	try {
    		Thread.sleep(2000); // wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    	System.out.print("Enter Password (or 'exit' to quit): ");
        String Pwd = scanner.nextLine();       	
    
        // Check if the input is "exit" to quit
        if (Pwd.equalsIgnoreCase("exit")) {
        	scanner.close(); // Close the scanner if user wants to exit
        	return; // Exit the method
        }
    
        password.clear(); // Clear any existing text in the search box
        password.sendKeys(Pwd);   
        password.sendKeys(Keys.ENTER);

        // Submit the password
        password.submit();        
        try {
        	Thread.sleep(2000); // wait for 2 seconds
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        	
    	WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/main/div/div/div/div/form/div[3]/button")));
    	JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", LoginButton);		
    	try {
    		Thread.sleep(10000); // wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	 */   	    	
    	
    	scanner.nextLine();
 
        scanner.close();
      
    }  
   
   public void startScheduledTasks() {
       ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
       Calendar targetTime = getTargetTime(18,35);
       long initialDelay = targetTime.getTimeInMillis() - System.currentTimeMillis();

       scheduler.schedule(this::executeAfterLogin, initialDelay, TimeUnit.MILLISECONDS);
   }
 
  
   // Helper method to calculate the initial delay until the target time
   public Calendar getTargetTime(int hourOfDay, int minute) {
       Calendar targetTime = Calendar.getInstance();
       targetTime.set(Calendar.HOUR_OF_DAY, 18);
       targetTime.set(Calendar.MINUTE, 35);
       targetTime.set(Calendar.SECOND, 0);
       targetTime.set(Calendar.MILLISECOND, 0);

       // If the current time is already past the target time, schedule for the next day
       if (System.currentTimeMillis() > targetTime.getTimeInMillis()) {
           targetTime.add(Calendar.DAY_OF_MONTH, 1);
       }

       return targetTime;
   }	    	  
   
   private void executeAfterLogin() {
       // This method will be executed at 11:30 AM daily after a successful login
       System.out.println("Scheduled task executing at: " + new Date());
       int retryIntervalMinutes = 5; // Interval between retries in minutes

       while (true) {
           try {
               // Perform login and tasks
               searchAndClickElement();               
               startVideoRecording();
               takeScreenshotsAndScroll();
               stopMonteScreenRecorder();
             //  logout();
               tearDown();
               
               // Exit the loop if tasks are successful
               break;
           } catch (Exception e) {
               e.printStackTrace();
               System.out.println("Task execution failed. Retrying in " + retryIntervalMinutes + " minutes...");

               // Sleep for the specified retry interval before the next attempt
               try {
                   Thread.sleep(retryIntervalMinutes * 60 * 1000);
               } catch (InterruptedException ex) {
                   ex.printStackTrace();
               }
           }
       }
   }
    
    public void searchAndClickElement() {
        // Locate the element to click
        WebElement wallet = driver.findElement(By.linkText("지갑관리"));
       
      
        // Click on the element
        wallet.click();
        try {
    		Thread.sleep(2000); // wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement DepWit = driver.findElement(By.linkText("자산현황"));
        DepWit.click();
        try {
    		Thread.sleep(2000); // wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }    
   
    public void startVideoRecording() throws IOException {
        // Start the video recording
    	screenRecorder.start();
    }   

    public void takeScreenshotsAndScroll() throws IOException, InterruptedException {
        int pageNumber = 1;
        //Actions actions = new Actions(driver);
        
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String datetime = dateFormat.format(date);        
	        		       
	            // Wait for a moment to load the content
	            Thread.sleep(4000);  
	            
	            // Take a screenshot of the current visible part of the page
	            File screenshotFile = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
	            BufferedImage fullScreen = ImageIO.read(screenshotFile);
	            ImageIO.write(fullScreen, "png", new File("C:\\Users\\sneha\\Documents\\Bithumb1-ScreenShot\\screenshot_page_" + pageNumber + "_" + datetime + "_top.png"));
	
	            // Wait for a moment to load the content
	            Thread.sleep(2000);  	            	            	            	                       
            
    }

  
    public void stopMonteScreenRecorder() throws IOException {
        // Stop the video recording
        screenRecorder.stop();
    }

    public void logout() {
    	WebElement logoutSearch = driver.findElement(By.linkText("xyz@flybit.com"));

    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutSearch);
        try {
            Thread.sleep(2000); // Wait for 2 seconds to ensure the element is clickable
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the logout element
        logoutSearch.click();

        try {
            Thread.sleep(4000); // Wait for 2 seconds to allow the page to load after clicking logout
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    	
     // Locate the logout button and click on it
        WebElement logoutButton = driver.findElement(By.linkText("로그아웃"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutButton);
        try {
            Thread.sleep(2000); // Wait for 2 seconds to ensure the element is clickable
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logoutButton.click();

        try {
            Thread.sleep(2000); // Wait for 2 seconds to allow the logout process to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
   public void tearDown() throws Exception {

        driver.quit();
    }

}
