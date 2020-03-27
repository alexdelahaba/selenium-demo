package org.seleniumdemo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumdemo.Pelicula;

import java.util.Objects;

import static java.lang.Integer.parseInt;

public class PaginaPelicula {
    private final WebDriver driver;
    private final static By COOKIES = By.xpath("/html/body/div[1]/div/div/div[2]/button[2]");
    private final static By TITULO = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/h1/span");
    private final static By TITULOORIGINAL = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[1]");
    private final static By ANIO = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[2]");
    private final static By PAIS = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[4]");

    private Pelicula pelicula;

    public PaginaPelicula (final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
    }

    public Pelicula scraper() {
        pelicula = new Pelicula();
        WebElement cookies = driver.findElement(COOKIES);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(cookies));

        cookies.click();
        WebElement titulo = driver.findElement(TITULO);
        WebElement tituloOriginal = driver.findElement(TITULOORIGINAL);
        WebElement anio = driver.findElement(ANIO);
        WebElement pais = driver.findElement(PAIS);

        pelicula.setTitulo(titulo.getText());
        pelicula.setTituloOriginal(tituloOriginal.getText());
        pelicula.setAnio(parseInt(anio.getText()));
        pelicula.setPais(pais.getText());
        System.out.println(pelicula);
        return pelicula;

    }


    public String localizarImagen() {
        WebElement urlImagen = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[2]/div[1]/a[1]/img"));
        String url = urlImagen.getAttribute("src");
        return url;
    }

}
