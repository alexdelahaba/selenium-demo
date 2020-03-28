package org.seleniumdemo.pageobject;

import com.steadystate.css.parser.selectors.PseudoElementSelectorImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumdemo.Pelicula;

import java.util.*;

import static java.lang.Integer.parseInt;

public class PaginaPelicula {
    private final WebDriver driver;
    private final static By COOKIES = By.xpath("//*[@id=\"qcCmpButtons\"]/button[2]");


    private Pelicula pelicula;

    public PaginaPelicula(final WebDriver driver) {
        Objects.requireNonNull(driver);
        this.driver = driver;
    }


    public List<WebElement> encontrarCaracteristicasPagina() {
        List<WebElement> listaSelectores = new ArrayList<>();
        List<String> listaNombreAtributos = inicializarListaAtributos();
        String xpath1 = "//*[@id=\"left-column\"]/dl[1]/dt[";
        String xpath2 = "]";
        String xpath1Encontrado = "//*[@id=\"left-column\"]/dl[1]/dd[";
        String xpath2Encontrado = "]";
        for (int i = 1; i <= 3; i++) {
            if (checkException(xpath1, xpath2, i)) {
                WebElement caracteristica = driver.findElement(By.xpath(xpath1 + i + xpath2));
                String valorElement = caracteristica.getText();
                for(int i2 = 0; i2<listaNombreAtributos.size(); i2++) {
                    if(listaNombreAtributos.get(i2).equals(valorElement)) {
                        //aqui iría el if de comporbar si es una lista de elementos o un elemento suelto
                        final By CARACTERISTICA = By.xpath(xpath1Encontrado + i + xpath2Encontrado);
                        listaSelectores.add(i2, caracteristica.findElement(CARACTERISTICA));
                    }
                }
            }

        }
        return listaSelectores;
       /* final By TITULO = By.xpath("//*[@id=\"main-title\"]/span");
        final By TITULOORIGINAL = By.xpath("//*[@class=\"movie-info\"]/dd[1]");
        *//*final By ANIO = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[2]");*//*
        final By DURACION = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[3]");
        final By PAIS = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[4]");
        final By MUSICA = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[7]/div/span/span");
        final By FOTOGRAFIA = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[8]/div/span/span");
        final By SINOPSIS = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[12]");
        final By NOTA = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[2]/div[2]/div[1]/div[2]/div[1]");
        final By VOTOS = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[2]/div[2]/div[1]/div[2]/div[2]/span");
        final By URL = By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[1]/ul/li[1]/a");*/

    }

    public Pelicula scraper() {

        pelicula = new Pelicula();

        //Cookies
        /*WebElement cookies = driver.findElement(COOKIES);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(cookies));
        cookies.click();*/

        List<WebElement> listaSelectores = encontrarCaracteristicasPagina();
        /*WebElement titulo = driver.findElement(TITULO);
        WebElement tituloOriginal = driver.findElement(TITULOORIGINAL);
        WebElement anio = driver.findElement(ANIO);
        WebElement pais = driver.findElement(PAIS);*/

       /* pelicula.setTitulo(getDatoString(TITULO));
        pelicula.setTituloOriginal(getDatoString(TITULOORIGINAL));*/
        pelicula.setAnio(getDatoInteger(listaSelectores.get(0)));
        /*String duracion = getDatoString(DURACION);
        String duracionReal = duracion.substring(0, duracion.indexOf(" "));
        pelicula.setDuracion(parseInt(duracionReal));
        pelicula.setPais(getDatoString(PAIS));
        pelicula.setMusica(getDatoString(MUSICA));
        pelicula.setFotografia(getDatoString(FOTOGRAFIA));
        pelicula.setSinopsis(getDatoString(SINOPSIS));
        pelicula.setNota(getDatoDouble(NOTA));
        pelicula.setVotos(getDatoString(VOTOS));
        pelicula.setUrl(getAttributoDOM("href", URL));*/
        getDirectores(pelicula);
        getActores(pelicula);

        return pelicula;

    }


    public String getDatoString(By CARACTERISTICA) {
        try {
            WebElement caracteristica = driver.findElement(CARACTERISTICA);
            String resultado = caracteristica.getText();
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getDatoInteger(WebElement caracteristica) {
        try {
            Integer resultado = parseInt(caracteristica.getText());
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDatoDouble(By CARACTERISTICA) {
        try {
            WebElement caracteristica = driver.findElement(CARACTERISTICA);
            String doubleMod = caracteristica.getText().replace(",", ".");
            Double resultado = Double.parseDouble(doubleMod);
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAttributoDOM(String atributo, By CARACTERISTICA) {
        try {
            WebElement caracteristica = driver.findElement(CARACTERISTICA);
            String valorAtributo = caracteristica.getAttribute(atributo);
            return valorAtributo;
        } catch (Exception e) {
            return null;
        }
    }

    public Pelicula getActores(Pelicula pelicula) {
        String xpathMod1 = "/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[9]/span[";
        String xpathMod2 = "]/a/span";
        List<String> actores = new ArrayList<String>();
        WebElement caracteristica;
        for (int i = 1; i <= 10; i++) {
            if (checkException(xpathMod1, xpathMod2, i)) {
                caracteristica = driver.findElement(By.xpath(xpathMod1 + i + xpathMod2));
                if (caracteristica.getText() != "") {
                    actores.add(caracteristica.getText());
                }
            }
        }
        pelicula.setActores(actores);

        return pelicula;
    }

    public Pelicula getDirectores(Pelicula pelicula) {
        String xpathMod1 = "/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[5]/span[";
        String xpathMod2 = "]/a/span";
        List<String> directores = new ArrayList<String>();
        WebElement caracteristica;
        for (int i = 1; i <= 20; i++) {
            if (checkException(xpathMod1, xpathMod2, i)) {
                caracteristica = driver.findElement(By.xpath(xpathMod1 + i + xpathMod2));
                if (caracteristica.getText() != "") {
                    directores.add(caracteristica.getText());
                }
            } else {
                i = 20;
            }
        }
        pelicula.setDirectores(directores);
        return pelicula;
    }

    public Pelicula getGuionistas(Pelicula pelicula) {
        String xpathMod1 = "/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[6]/div/span[";
        String xpathMod2 = "]/span";
        List<String> guionistas = new ArrayList<String>();
        WebElement caracteristica;
        for (int i = 1; i <= 20; i++) {
            if (checkException(xpathMod1, xpathMod2, i)) {
                caracteristica = driver.findElement(By.xpath(xpathMod1 + i + xpathMod2));
                if (caracteristica.getText() != "") {
                    guionistas.add(caracteristica.getText());
                }
            } else {
                i = 20;
            }
        }
        pelicula.setGuionistas(guionistas);
        return pelicula;
    }

    public Pelicula getGeneros(Pelicula pelicula) {
        String xpathMod1 = "/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[3]/dl[1]/dd[5]/span[";
        String xpathMod2 = "]/a/span";
        List<String> directores = new ArrayList<String>();
        WebElement caracteristica;
        for (int i = 1; i <= 20; i++) {
            if (checkException(xpathMod1, xpathMod2, i)) {
                caracteristica = driver.findElement(By.xpath(xpathMod1 + i + xpathMod2));
                if (caracteristica.getText() != "") {
                    directores.add(caracteristica.getText());
                }
            } else {
                i = 20;
            }
        }
        pelicula.setDirectores(directores);
        return pelicula;
    }

    public Boolean checkException(String xpathMod1, String xpathMod2, int i) {
        try {
            driver.findElement(By.xpath(xpathMod1 + i + xpathMod2));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String localizarImagen() {
        WebElement urlImagen = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr/td[2]/div[1]/div[4]/div/div[2]/div[1]/a[1]/img"));
        String url = urlImagen.getAttribute("src");
        return url;
    }

    public List<String> inicializarListaAtributos() {
        List<String> listaAtributos = new ArrayList<String>();
        List<String> listaAtributosFake = Arrays.asList(
                "Título original",
                "Año",
                "Duración",
                "País",
                "Dirección",
                "Guion",
                "Música",
                "Fotografía",
                "Reparto",
                "Género",
                "Sinopsis");
        listaAtributos.addAll(listaAtributosFake);

        return listaAtributos;
    }

}
