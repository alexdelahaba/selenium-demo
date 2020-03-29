package org.seleniumdemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class ComprobarPagina {

    public static void cerrarPublicidad(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));

        }
    }

    public static void cerrarCookies(WebDriver driver) {
        try {
            WebElement cookies = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/button[2]"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(cookies));
            cookies.click();
        } catch(Exception e){

        }
    }


}
