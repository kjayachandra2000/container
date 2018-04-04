package test.features;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SearchPerfumeTest extends BaseTest {

    @DataProvider(name = "search-keywords")
    public static Object[][] credentials() {
        return new Object[][]{
                {"Hugo Boss Boss Bottled Eau de Toilette"},
                {"Lancôme La vie est belle Eau de Parfum"},
        };
    }

    @BeforeTest
    public void setUp() throws MalformedURLException {
        super.setUp();

    }

    @Test(dataProvider = "search-keywords")
    public void productSearch(String perfumeName) {
        searchPage
                .goTo()
                .searchFor(perfumeName);
        Assert.assertTrue(searchPage.getResults());
    }

}
