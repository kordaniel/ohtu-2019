package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");

        //simulate login with proper credentials
        //testLoginWithCredentials("pekka", "akkep");
        
        //simulate login with proper username and bad password
        //testLoginWithCredentials("pekka", "pekka");
        
        //simulate creation of new user
        int randomNum = new Random().nextInt(10000);
        String username = "testUser" + randomNum;
        String password = randomNum + "testUser";
        testCreateNewUser(driver, username, password);
        
        //simulate logout of newly created user
        testCreatedNewUserLogout(driver);
        
        driver.quit();
        
        /*
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
        */
    }

    private static void testCreatedNewUserLogout(WebDriver driver) {
        sleep(2);

        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(3);
    }

    private static void testCreateNewUser(WebDriver driver, String username, String password) {
        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);

        element = driver.findElement(By.name("signup"));
        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void testLoginWithCredentials(String username, String password) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }
    
    //helper methods
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while(trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;           
            } catch(Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
