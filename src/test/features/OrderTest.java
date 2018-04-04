package test.features;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.pages.CartPage;
import test.pages.OrderPage;
import test.pages.SearchPage;

import java.net.MalformedURLException;

public class OrderTest extends BaseTest {

    @DataProvider(name = "perfumeName")
    public static Object[][] perfumeName() {
        return new Object[][]{
                {"Hugo Boss - Boss Bottled", "50 ml"},
                {"Lancôme - La vie est belle", "100 ml"},
        };
    }

    @BeforeTest
    public void setUp() throws MalformedURLException {
        super.setUp();
    }

    @Test(dataProvider = "perfumeName")
    public void addToCart(String perfumeName, String perfumeSize) {

        searchPage
                .goTo()
                .searchFor(perfumeName);
        orderPage
                .selectPerfumeSize(perfumeSize)
                .addToCart();

        cartPage
                .isDisplayed()
                .verifySelectedItem(perfumeName);
    }
}
