package hw4;

import base.SeleniumBase;
import lesson4.SelenideIndexPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static lesson4.SelenideIndexPage.*;

public class SelenideTest extends SeleniumBase{
    private SelenideIndexPage indexPage;


    //@BeforeSuite
    //public void beforeSuite() {
    //  Configuration.browser = Browsers.CHROME;
    //}

    @BeforeMethod
    public void initTest(){
        //1 Open test site by URL
        open("https://epam.github.io/JDI/index.html");
        indexPage = page(SelenideIndexPage.class);
       // driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MINUTES);
    }

    @AfterMethod
    public void closeTest(){
        close();
    }

    @Test
    public void simpleTest() {
        //2 Assert Browser title
        Assert.assertEquals(getWebDriver().getTitle(), "Home Page");

        //3 Perform login
        indexPage.login("epam", "1234");

        //4 Assert User name in the left-top side of screen that user is loggined
        indexPage.checkBy("#user-name", "Piter Chailovskii");

        //5 Click on "Service" subcategory in the header and check that drop down contains options
        indexPage.clickService();
        List<String> serviceUrls = new ArrayList<String>();
        List<String> texts = new ArrayList<String>();
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(1) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(2) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(3) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(4) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(5) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(6) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(7) > a");
        serviceUrls.add("body > header > div > nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li.dropdown.open > ul > li:nth-child(8) > a");
        texts.add("Support");
        texts.add("Dates");
        texts.add("Complex Table");
        texts.add("Simple Table");
        texts.add("User Table");
        texts.add("Table with pages");
        texts.add("Different elements");
        texts.add("Performance");
        indexPage.checkList(serviceUrls, texts);

        //6 Click on Service subcategory in the left section and check that drop down contains options
        indexPage.clickSideService();
        List <String> sideUrls = new ArrayList<String>();
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(1) > a > p > span");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(2) > a");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(3) > a > span");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(4) > a > span");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(5) > a > span");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(6) > a > span");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(7) > a > span");
        sideUrls.add("#mCSB_1_container > ul > li:nth-child(3) > ul > li:nth-child(8) > a");
        indexPage.checkList(sideUrls, texts);

        //7 Open through the header menu Service -> Different Elements Page
        open("https://epam.github.io/JDI/different-elements.html");
        indexPage.checkMenu();

        //8 Assert that there is Right Section
        indexPage.checkVisible("#mCSB_2_container > section:nth-child(1) > div.info-panel-body.info-panel-body-log");

        //9 Assert that there is Left Section
        indexPage.checkVisible("#mCSB_1_container > ul > li.menu-title.active > ul > li:nth-child(6) > a");

        //10 Select checkboxes
        indexPage.clickCheckbox(new DifferentElements[]{DifferentElements.WATER , DifferentElements.WIND});

        //11 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        indexPage.checkCheckboxLogs(new DifferentElements[]{DifferentElements.WATER, DifferentElements.WIND});


        //12 Select radio
        indexPage.clickRadio(new DifferentElements[]{DifferentElements.SELEN});

        //13 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        indexPage.checkRadioLogs(new DifferentElements[]{DifferentElements.SELEN});

        //14 Select in dropdown
        indexPage.clickDropdown(new DropdownElements[] {DropdownElements.YELLOW});

        //15 Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        indexPage.checkDropdownLogs(new DropdownElements[] {DropdownElements.YELLOW});

        //16 Unselect and assert checkboxes
        indexPage.clickCheckbox(new DifferentElements[]{DifferentElements.WATER , DifferentElements.WIND});

        //17 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        indexPage.checkCheckboxLogs(new DifferentElements[]{DifferentElements.WATER, DifferentElements.WIND});

    }
}
