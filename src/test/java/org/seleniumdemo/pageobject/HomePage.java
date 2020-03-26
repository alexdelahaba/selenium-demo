package org.seleniumdemo.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class HomePage {
    private final static By WOMEN_MENU = By.linkText("Women");
    private final static By SEARCH_FIELD = By.xpath("/html/body/div[1]/div[1]/div/div[2]/form/input[1]");

    private final WebDriver driver;

    public HomePage(final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
    }

    public void goToWomenSubcategory() {
        driver.findElement(WOMEN_MENU).click();
    }

    public void loadHomePage() {
        driver.get("https://www.filmaffinity.com/es/main.html");
    }

    public void search(String searchText) {
        Objects.requireNonNull(searchText);
        WebElement searchField = driver.findElement(SEARCH_FIELD);
        searchField.sendKeys(searchText);
        /*searchField.sendKeys(Keys.DOWN);*/
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul/li[1]/div")));
        WebElement pelicula = driver.findElement(By.xpath("/html/body/ul/li[1]/div"));
        pelicula.click();
    }
}
