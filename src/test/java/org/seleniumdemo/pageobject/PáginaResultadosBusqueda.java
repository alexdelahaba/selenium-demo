package org.seleniumdemo.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Objects;

import static org.seleniumdemo.utils.ComprobarPagina.cerrarCookies;
import static org.seleniumdemo.utils.ComprobarPagina.cerrarPublicidad;

public class PáginaResultadosBusqueda {
    private final WebDriver driver;


    public PáginaResultadosBusqueda(final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
    }


    public static Boolean searchPelicula(WebDriver driver, String searchText) {
        try {
            String xpath1 = "//*[@id=\"title-result\"]/div/div[";
            String xpath2 = "]/div[2]/div/div[2]/div[2]/a";
            for (int i = 2; i < 6; i++) {
                WebElement pelicula = driver.findElement(By.xpath(xpath1 + i + xpath2));
                if (pelicula.getText().equals(searchText)) {
                    pelicula.click();
                    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                    if (tabs.size() > 1) {
                        cerrarPublicidad(driver);

                        while (!driver.getCurrentUrl().contains("filmaffinity")) {
                            cerrarPublicidad(driver);
                            WebElement pelicula2 = driver.findElement(By.xpath(xpath1 + i + xpath2));
                            pelicula2.click();
                        }
                        pelicula.click();
                    }
                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
