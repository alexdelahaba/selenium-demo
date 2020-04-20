package org.seleniumdemo.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumdemo.Pelicula;
import org.seleniumdemo.pageobject.PáginaEntradaFilmaffinity;
import org.seleniumdemo.pageobject.PaginaPelicula;
import org.seleniumdemo.pageobject.PaginaImagen;
import org.seleniumdemo.pageobject.RottenTomatoesPage;
import org.seleniumdemo.utils.ListaTitulos;
import org.seleniumdemo.utils.ListaUrls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.seleniumdemo.pageobject.IMDBPage.devolverUrlIMDB;
import static org.seleniumdemo.pageobject.JustWatchPage.devolverUrlJustWatch;
import static org.seleniumdemo.pageobject.PaginaPelicula.localizarUrlImagen;
import static org.seleniumdemo.pageobject.RottenTomatoesPage.devolverUrlRottenTomatoes;
import static org.seleniumdemo.utils.GuardarDatos.*;
import static org.seleniumdemo.utils.ListaPeliculas.listaPeliculas;

public class TestsNavegador {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /*Este es el test a realizar si aportamos una lista de peliculas en ListaUrls*/
    @Test
    public void extraerDatosDadaPelicula() throws Exception {
        List<String> listaUrl = ListaUrls.listaUrls;

        for (int i = 0; i < listaUrl.size(); i++) {
            try {
                PaginaPelicula paginaPelicula = new PaginaPelicula(driver);
                driver.get(listaUrl.get(i));
                Pelicula pelicula;
                pelicula = paginaPelicula.encontrarCaracteristicasPagina();
                pelicula.setUrlRottenTomatoes(devolverUrlRottenTomatoes(pelicula.getTitulo()));
                pelicula.setUrlIMDB(devolverUrlIMDB(pelicula.getTitulo()));
                pelicula.setUrlJustWatch(devolverUrlJustWatch(pelicula.getTitulo()));
                pelicula.setUrlImagen(localizarUrlImagen(driver));
                pelicula.setId(pelicula.getUrl().replaceAll("\\D+",""));
                pelicula.setUrlTrailer(devolverUrlTrailer(driver, pelicula.getTitulo()));
                guardarDatosBBDD(pelicula);
                hacerCopiaDeSeguridad();
            } catch (Exception e) {
                System.out.println("Se ha producido un error");
            }
        }
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
    public void extraccionCompleta() {
        List<String> listaUrl = ListaUrls.listaUrls;
        for (int i = 0; i < listaUrl.size(); i++) {
            Pelicula pelicula = null;
            try {
                PaginaPelicula paginaPelicula = new PaginaPelicula(driver);
                PaginaImagen paginaImagen = new PaginaImagen(driver);
                driver.get(listaUrl.get(i));
                pelicula = paginaPelicula.encontrarCaracteristicasPagina();

                String urlImagen = paginaPelicula.localizarImagen();
                paginaImagen.loadHomePage(urlImagen);
                paginaImagen.saveImage(pelicula.getTitulo(), i);
                pelicula.setUrlTrailer(devolverUrlTrailer(driver, pelicula.getTitulo()));

                pelicula.setUrlRottenTomatoes(devolverUrlRottenTomatoes(pelicula.getTituloOriginal()));
                pelicula.setUrlIMDB(devolverUrlIMDB(pelicula.getTitulo()));
                pelicula.setUrlJustWatch(devolverUrlJustWatch(pelicula.getTitulo()));
                guardarDatosBBDD(pelicula);

            } catch (Exception e) {
                System.out.println("Se ha producido un error en la pelicula: " + pelicula.getTitulo());
            }

        }
    }

    @Test
    public void descargarImagenes() throws IOException {
        List<String> listaUrl = ListaUrls.listaUrls;
        for (int i = 0; i < listaUrl.size(); i++) {
            try {
                driver.get(listaUrl.get(i));
                String urlImagen = localizarUrlImagen(driver);
                guardarUrlImagen(i, urlImagen);
            } catch (Exception e) {
            }
            /*PaginaImagen paginaImagen = new PaginaImagen(driver);
            try {
                paginaImagen.guardarImagen(titulo.getText());
            } catch (Exception e) {
            }*/
        }
    }


    @Test
    public void guardarId() throws IOException {
        List<String> listaUrl = ListaUrls.listaUrls;
        for (int i = 0; i < listaUrl.size(); i++) {
            try {
                String urlMod = listaUrl.get(i).replaceAll("\\D+","");
                guardarIdPelicula(i, urlMod);
            } catch (Exception e) {
            }
            /*PaginaImagen paginaImagen = new PaginaImagen(driver);
            try {
                paginaImagen.guardarImagen(titulo.getText());
            } catch (Exception e) {
            }*/
        }
    }

    @Test
    public void urlExtras() {
        List<String> listaTitulos = ListaTitulos.listaTitulos;
        for (int i = 0; i < listaTitulos.size(); i++) {
            Pelicula pelicula = new Pelicula();
            try {

                pelicula.setUrlRottenTomatoes(devolverUrlRottenTomatoes(listaTitulos.get(i)));
                pelicula.setUrlIMDB(devolverUrlIMDB(listaTitulos.get(i)));
                pelicula.setUrlJustWatch(devolverUrlJustWatch(listaTitulos.get(i)));
                guardarUrlsExtras(pelicula, i);

            } catch (Exception e) {
                System.out.println("Se ha producido un error en la pelicula: " );
            }

        }
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
