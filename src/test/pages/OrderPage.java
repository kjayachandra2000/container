package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderPage extends BasePage {

    @FindBy(css = "li[class*='variant']")
    private List<WebElement> chkVariant;

    @FindBy(id = "socialInactive")
    private WebElement lnkSocialActivity;

    @FindBy(css = "button[title='In den Warenkorb']")
    private List<WebElement> btnAddToCart;


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public OrderPage selectPerfumeSize(String perfumeSize) {
        boolean findFlag = false;
        for (WebElement element : chkVariant) {
            if (element.getText().startsWith(perfumeSize)) {
                element.click();
                findFlag = true;
                break;
            }
        }
        assert findFlag;
        System.out.println("perfume size selected : " + perfumeSize);
        return this;
    }

    public void addToCart() {
        for (WebElement btn : btnAddToCart) {
            if (!btn.getText().equalsIgnoreCase("")) {
                btn.click();
                break;
            }
        }
    }
}
