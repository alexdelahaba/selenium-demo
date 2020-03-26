package org.seleniumdemo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class SearchResultPage {
    private final WebDriver driver;

    public SearchResultPage(final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
    }

    public String search() {
        WebElement cookies = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/button[2]"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[2]/button[2]")));
        cookies.click();
        WebElement director = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[5]/span/a/span"));
        WebElement anio = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[2]"));
        return director.getText();

    }

    public boolean isResultItemDisplayed(String itemName) {
        return driver.findElement(By.cssSelector(String.format("img[alt='%s']", itemName))).isDisplayed();
    }
}
