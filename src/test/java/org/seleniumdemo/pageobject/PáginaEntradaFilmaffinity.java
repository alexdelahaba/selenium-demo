package org.seleniumdemo.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class PáginaEntradaFilmaffinity {
    private final WebDriver driver;
    private final static By BARRA_BUSQUEDA = By.xpath("/html/body/div[1]/div[1]/div/div[2]/form/input[1]");
    private final static By COOKIES = By.xpath("/html/body/div[1]/div/div/div[2]/button[2]");
    private final static By PELICULA = By.xpath("/html/body/ul/li[1]/div");


    public PáginaEntradaFilmaffinity(final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
    }

    public void loadHomePage() {
        driver.get("https://www.filmaffinity.com/es/main.html");
    }

    public void search(String searchText) {
        WebElement barraBusqueda = driver.findElement(BARRA_BUSQUEDA);
        /*WebElement cookies = driver.findElement(COOKIES);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(cookies));

        cookies.click();*/

        barraBusqueda.sendKeys(searchText);
        WebElement pelicula = driver.findElement(PELICULA);
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.visibilityOf(pelicula));
        pelicula.click();
    }


}
