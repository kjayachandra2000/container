package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = "form[name='top-search']>input")
    private WebElement searchBox;

    @FindBy(className = "searchResultContainerRow")
    private List<WebElement> searchResults;

    @FindBy(css = "ul[class='as-column products']")
    private WebElement searchResult;

    @FindBy(id = "socialInactive")
    private WebElement lnkSocialActivity;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage goTo() {
        this.driver.get("https://www.flaconi.de/");
        System.out.println("Browser launched and navigated to https://www.flaconi.de/");
        return this;
    }

    public void searchFor(String text) {
        wait.until(ExpectedConditions.elementToBeClickable((this.searchBox)));
        Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(driver.findElement(By.className("logo-image")))
                .build();

        mouseOverHome.perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.searchBox.click();
        this.searchBox.sendKeys(text);
        System.out.println(text + " - entered for search");
        wait.until(ExpectedConditions.visibilityOf((this.searchResult)));
        this.searchResults.get(0).click();
        System.out.println("Perfume clicked");
    }

    public boolean getResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("socialInactive")));
        System.out.println("Results appeared");
        return lnkSocialActivity.isDisplayed();
    }

}
