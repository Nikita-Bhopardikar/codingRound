import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HotelBookingTest {

	WebDriver driver;
			
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;
    
    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    // Using Page factory initElements() 
    public HotelBookingTest()
	{
	    this.driver = new ChromeDriver();
	 //This initElements method will create all WebElements
	    PageFactory.initElements(driver, this);
	}
       

    @Test
    public void shouldBeAbleToSearchForHotels() 
    {
        setDriverPath();

        this.driver.get("https://www.cleartrip.com/");
        
        // Wait until WebElement is visible
        WebDriverWait link = new WebDriverWait(this.driver,100); 
        link.until(ExpectedConditions.visibilityOf(hotelLink)).click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
