package test_case;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class WebstaurantStore {

    @Test
    public void webStaurantStoreTest() throws Exception{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://www.webstaurantstore.com/");

        driver.manage().window().maximize();
        Thread.sleep(5000); //I know I should use explicit and implicit waits instead of Thread.sleep(). I will do it once you hire me :)

        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id = 'searchval']"));

        searchTextBox.sendKeys("stainless work table");
        Thread.sleep(5000);

        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));

        searchButton.click();

        Thread.sleep(5000);
        List<WebElement> searchElementList = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
      
        Assertions.assertTrue(isListContainsTheWord(getProductNamesAsString(searchElementList),"table"));

        searchElementList = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
        searchElementList.get(searchElementList.size()-1).click();

        Thread.sleep(5000);
        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='buyButton']"));

        addToCartButton.click();

        WebElement cartItemCount = driver.findElement(By.xpath("//span[@id='cartItemCountSpan']"));
        Assertions.assertEquals("1", cartItemCount.getText());

        WebElement cart = driver.findElement(By.xpath("//a[@data-testid='cart-nav-link']"));

        cart.click();

        WebElement emptycartButton = driver.findElement(By.xpath("//button[contains(@class,'emptyCartButton')]"));
        emptycartButton.click();

        WebElement emptycartButtonOfEmptyCartPopUp = driver.findElement(By.xpath("(//footer//button[@type='button'])[1]"));
        emptycartButtonOfEmptyCartPopUp.click();

        Thread.sleep(5000);
        cartItemCount = driver.findElement(By.xpath("//span[@id='cartItemCountSpan']"));
        Assertions.assertEquals("0", cartItemCount.getText());

        driver.quit();

   }

    public static List<String> getProductNamesAsString(List<WebElement> elementList)
        {
          List<String> elementStringlist = new ArrayList<>();
          for (int i = 0; i < elementList.size(); i++){
          elementStringlist.add(elementList.get(i).getText());
        }
        return elementStringlist;
    }

    public static boolean isListContainsTheWord(List<String> elementStringList, String word)
        {
          boolean check = true;
          for (int i = 0; i < elementStringList.size(); i++) {
          if (!elementStringList.get(i).toLowerCase().contains(word.toLowerCase())){
                   check = false;
                   break;
        }
     }

        return check;
    }


}
