package org.seleniumdemo.pageobject;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static org.seleniumdemo.pageobject.PáginaResultadosBusqueda.searchPelicula;
import static org.seleniumdemo.utils.ComprobarPagina.*;

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

    public Boolean search(String searchText) {

        //Cookies
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.withMessage("cerrando pestaña de publicidad...");
        cerrarPublicidad(driver);
        cerrarCookies(driver);
        cerrarPublicidad(driver);
        try {
            WebElement barraBusqueda = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/form/input[1]"));
            WebDriverWait wait2 = new WebDriverWait(driver, 10);
            wait2.until(ExpectedConditions.visibilityOf(barraBusqueda));
            barraBusqueda.sendKeys(searchText);
            barraBusqueda.sendKeys(Keys.DOWN, Keys.ENTER);
        } catch (NoSuchElementException e) {
            return false;
        }


        return searchPelicula(driver, searchText);

    }


}
