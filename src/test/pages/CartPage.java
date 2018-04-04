package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartPage extends BasePage {

    @FindBy(id = "to-checkout-btn-1")
    private WebElement btnCheckOut;

    @FindBy(className = "details")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
    }


    public CartPage isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(btnCheckOut));
        assertThat(btnCheckOut.isDisplayed(), is(true));
        return this;
    }

    public void verifySelectedItem(String perfumeName) {
        boolean findFlag = false;
        for (WebElement cartItem : cartItems) {
            System.out.println("items: " + cartItem.getText());
            if (cartItem.getText().contains(perfumeName)) {
                findFlag = true;
                break;
            }
        }
        assert findFlag;
    }
}
