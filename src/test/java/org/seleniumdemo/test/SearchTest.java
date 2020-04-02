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
import org.seleniumdemo.utils.ListaUrls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.seleniumdemo.utils.ExtraerUrlTrailer.extraerUrlTrailer;
import static org.seleniumdemo.utils.GuardarDatos.*;
import static org.seleniumdemo.utils.ListaPeliculas.listaPeliculas;

public class SearchTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void lanzarBaseDeDatosPeliculas(String nombrePelicula) throws Exception {

        for (int i = 0; i < 3; i++) {
            PáginaEntradaFilmaffinity paginaEntrada = new PáginaEntradaFilmaffinity(driver);
            PaginaPelicula paginaPelicula = new PaginaPelicula(driver);
            PaginaImagen paginaImagen = new PaginaImagen(driver);

            paginaEntrada.loadHomePage();

            if (paginaEntrada.search(nombrePelicula)) {
                Pelicula pelicula = paginaPelicula.encontrarCaracteristicasPagina();
                /*String urlImagen = paginaPelicula.localizarImagen();
                paginaImagen.loadHomePage(urlImagen);
                paginaImagen.saveImage(nombrePelicula, i);*/
                try {
                    i = crearArchivoBasico(pelicula, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            } else if (driver.getCurrentUrl().contains("/film")) {
                Pelicula pelicula = paginaPelicula.encontrarCaracteristicasPagina();
                /*String urlImagen = paginaPelicula.localizarImagen();
                paginaImagen.loadHomePage(urlImagen);
                paginaImagen.saveImage(nombrePelicula, i);*/
                i = 3;
                try {
                    i = crearArchivoBasico(pelicula, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Pelicula pelicula = new Pelicula();
                pelicula.setTitulo(nombrePelicula);
                pelicula.setTituloOriginal("Se ha producido algún tipo de error. Revisar esta película");
                i = crearArchivoBasico(pelicula, i);
            }
            //grindhouse
            //scoop
            //novecento

        }
    }


    @Test
    public void testDesacoplado() throws Exception {
        int tamaño = listaPeliculas.size();
        for (int iterator = 0; iterator < listaPeliculas.size(); iterator++) {
            String nombrePelicula = listaPeliculas.get(iterator);
            lanzarBaseDeDatosPeliculas(nombrePelicula);

        }
    }


    @Test
    public void testTrailer() throws Exception {
        for (int i = 1; i < 999; i++) {
            try {
                leerTituloPelicula(driver, i);
            } catch (Exception e) {

            }
        }


    }

    @Test
    public void extraerDatosDadaPelicula() throws Exception {
        List<String> listaUrl = ListaUrls.listaUrls;

        for (int i = 0; i < listaUrl.size(); i++) {
            PaginaPelicula paginaPelicula = new PaginaPelicula(driver);
            PaginaImagen paginaImagen = new PaginaImagen(driver);
            driver.get(listaUrl.get(i));
            Pelicula pelicula = paginaPelicula.encontrarCaracteristicasPagina();

            /*String urlImagen = paginaPelicula.localizarImagen();
            paginaImagen.loadHomePage(urlImagen);
            paginaImagen.saveImage(pelicula.getTitulo(), i);*/

            pelicula.setUrlTrailer(devolverUrlTrailer(driver, pelicula.getTitulo()));
            guardarDatosBBDD(pelicula);
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
