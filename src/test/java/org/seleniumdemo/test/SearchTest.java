package org.seleniumdemo.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumdemo.Pelicula;
import org.seleniumdemo.pageobject.PáginaEntradaFilmaffinity;
import org.seleniumdemo.pageobject.PaginaPelicula;
import org.seleniumdemo.pageobject.PaginaImagen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.seleniumdemo.utils.GuardarDatos.crearArchivoBasico;
import static org.seleniumdemo.utils.ListaPeliculas.listaPeliculas;

public class SearchTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = System.getProperty("browserName");
        /*System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");*/
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void lanzarBaseDeDatosPeliculas() throws Exception {
        int tamaño = listaPeliculas.size();
        for (int iterator = 0; iterator < listaPeliculas.size(); iterator++) {
            String nombrePelicula = listaPeliculas.get(iterator);
            for (int i = 0; i < 3; i++) {
                PáginaEntradaFilmaffinity paginaEntrada = new PáginaEntradaFilmaffinity(driver);
                PaginaPelicula paginaPelicula = new PaginaPelicula(driver);
                PaginaImagen paginaImagen = new PaginaImagen(driver);

                paginaEntrada.loadHomePage();

                if (paginaEntrada.search(nombrePelicula)) {


                    String urlImagen = paginaPelicula.localizarImagen();
                    paginaImagen.loadHomePage(urlImagen);
                    paginaImagen.saveImage(nombrePelicula, i);
                } else if (driver.getCurrentUrl().contains("/film")) {
                    Pelicula pelicula = paginaPelicula.encontrarCaracteristicasPagina();
                    String urlImagen = paginaPelicula.localizarImagen();
                    paginaImagen.loadHomePage(urlImagen);
                    paginaImagen.saveImage(nombrePelicula, i);
                    i = crearArchivoBasico(pelicula, i);
                }
                //grindhouse
                //scoop
                //novecento

            }
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
