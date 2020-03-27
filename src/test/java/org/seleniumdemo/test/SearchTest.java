package org.seleniumdemo.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumdemo.pageobject.PáginaEntradaFilmaffinity;
import org.seleniumdemo.pageobject.PaginaPelicula;
import org.seleniumdemo.pageobject.PaginaImagen;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = System.getProperty("browserName");
        /*System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");*/
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void searchDressAndValidateResults() throws IOException {
        PáginaEntradaFilmaffinity paginaEntrada = new PáginaEntradaFilmaffinity(driver);
        PaginaPelicula paginaPelicula = new PaginaPelicula(driver);
        PaginaImagen paginaImagen = new PaginaImagen(driver);

        paginaEntrada.loadHomePage();
        paginaEntrada.search("scoop");

        paginaPelicula.scraper();

        String urlImagen = paginaPelicula.localizarImagen();
        paginaImagen.loadHomePage(urlImagen);
        paginaImagen.saveImage();


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
