package org.seleniumdemo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


    public Pelicula encontrarCaracteristicasPagina() {
        pelicula = new Pelicula();
        List<String> listaNombreAtributos = inicializarListaAtributos();
        String xpath1 = "//*[@id=\"left-column\"]/dl[1]/dt[";
        String xpath2 = "]";
        //con este primer for recorre los tags de la página
        for (int i = 1; i <= 13; i++) {
            //este if comprueba que existe el elemento y captura si se produce un error
            if (checkException(xpath1, xpath2, i)) {
                WebElement caracteristica = driver.findElement(By.xpath(xpath1 + i + xpath2));
                String valorElement = caracteristica.getText();
                //otro for para poder asignar tag con atributo
                for (int i2 = 0; i2 < listaNombreAtributos.size(); i2++) {

                    if (listaNombreAtributos.get(i2).equals(valorElement)) {
                        //aqui iría el if de comporbar si es una lista de elementos o un elemento suelto
                        switch (valorElement) {
                            case "Dirección":
                                pelicula.setDirectores(getDirectoresLista(i));
                                break;
                            case "Guion":
                                pelicula.setGuionistas(getGuionistasLista(i));
                                break;
                            case "Reparto":
                                pelicula.setActores(getActoresLista(i));
                                break;
                            case "Música":
                                pelicula.setMusica(getMusicaFotografíaLista(i));
                                break;
                            case "Fotografía":
                                pelicula.setFotografia(getMusicaFotografíaLista(i));
                                break;
                            case "Género":
                                pelicula.setGeneros(getGeneroLista(i));
                                break;
                            case "Año":
                                pelicula.setAnio(getAtributoUnico(i));
                                break;
                            case "Duración":
                                pelicula.setDuracion(getAtributoUnico(i));
                                break;
                            case "Título original":
                                pelicula.setTituloOriginal(getAtributoUnico(i));
                                break;
                            case "País":
                                pelicula.setPais(getAtributoUnico(i));
                                break;
                            case "Sinopsis":
                                pelicula.setSinopsis(getAtributoUnico(i));
                                break;
                            default:
                                break;
                        }

                        /*final By CARACTERISTICA = By.xpath(xpath1Encontrado + i + xpath2Encontrado);
                        listaSelectores.add(i2, caracteristica.findElement(CARACTERISTICA));*/
                    }
                }
            } else {
                i = 14;
            }
        }
        pelicula.setTitulo(driver.findElement(By.xpath("//*[@id=\"main-title\"]/span")).getText());
        pelicula.setUrl(driver.findElement(By.xpath("//*[@id=\"mt-content-cell\"]/div[4]/div/div[1]/ul/li[1]/a")).getAttribute("href"));
        WebElement nota;
        WebElement votos;
        try {
            nota = driver.findElement(By.xpath("//*[@id=\"movie-rat-avg\"]"));
            String doubleMod = nota.getText().replace(",", ".");
            Double resultado = Double.parseDouble(doubleMod);
            pelicula.setNota(resultado);

            votos = driver.findElement(By.xpath("//*[@id=\"movie-count-rat\"]/span"));
            pelicula.setVotos(votos.getText());
        } catch (Exception e) {
            pelicula.setNota(null);
            pelicula.setVotos(null);
        }

        return pelicula;
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
/*
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
    }*/

    public String getAttributoDOM(String atributo, By CARACTERISTICA) {
        try {
            WebElement caracteristica = driver.findElement(CARACTERISTICA);
            String valorAtributo = caracteristica.getAttribute(atributo);
            return valorAtributo;
        } catch (Exception e) {
            return null;
        }
    }


    public List<String> getDirectoresLista(Integer numeroDD) {
        String xpathLista1a = "//*[@id=\"left-column\"]/dl[1]/dd[";
        String xpathLista1b = "]/span[";
        String xpathLista1c = "]/a/span";

        List<String> directores = new ArrayList<String>();
        WebElement caracteristica;

        for (int i = 1; i <= 5; i++) {
            if (checkExceptionCompleja(xpathLista1a, numeroDD, xpathLista1b, i, xpathLista1c)) {
                String xpathLista1 = xpathLista1a + numeroDD + xpathLista1b + i + xpathLista1c;
                caracteristica = driver.findElement(By.xpath(xpathLista1));
                if (caracteristica.getText() != "") {
                    directores.add(caracteristica.getText());
                }
            } else {
                i = 5;
            }
        }
        return directores;
    }

    public String getAtributoUnico(Integer numeroDD) {
        String xpath1a = "//*[@id=\"left-column\"]/dl[1]/dd[";
        String xpath1b = "]";

        WebElement caracteristica;

        if (checkException(xpath1a, xpath1b, numeroDD)) {
            String xpathLista1 = xpath1a + numeroDD + xpath1b;
            caracteristica = driver.findElement(By.xpath(xpathLista1));
            if (caracteristica.getText() != "") {
                return caracteristica.getText();
            }
        }
        return null;
    }

    public List<String> getActoresLista(Integer numeroDD) {
        String xpathLista1a = "//*[@id=\"left-column\"]/dl[1]/dd[";
        String xpathLista1b = "]/span[";
        String xpathLista1c = "]/a/span";

        List<String> actores = new ArrayList<String>();
        WebElement caracteristica;

        for (int i = 1; i <= 10; i++) {
            if (checkExceptionCompleja(xpathLista1a, numeroDD, xpathLista1b, i, xpathLista1c)) {
                String xpathLista1 = xpathLista1a + numeroDD + xpathLista1b + i + xpathLista1c;
                caracteristica = driver.findElement(By.xpath(xpathLista1));
                if (caracteristica.getText() != "") {
                    actores.add(caracteristica.getText());
                }
            } else {
                i = 10;
            }
        }
        return actores;
    }

    public List<String> getMusicaFotografíaLista(Integer numeroDD) {
        String xpathLista1a = "//*[@id=\"left-column\"]/dl[1]/dd[";
        String xpathLista1b = "]/div/span[";
        String xpathLista1c = "]/span";

        List<String> musica = new ArrayList<String>();
        WebElement caracteristica;

        for (int i = 1; i <= 15; i++) {
            if (checkExceptionCompleja(xpathLista1a, numeroDD, xpathLista1b, i, xpathLista1c)) {
                String xpathLista1 = xpathLista1a + numeroDD + xpathLista1b + i + xpathLista1c;
                caracteristica = driver.findElement(By.xpath(xpathLista1));
                if (caracteristica.getText() != "") {
                    musica.add(caracteristica.getText());
                }
            } else {
                i = 15;
            }
        }
        return musica;
    }


    public List<String> getGeneroLista(Integer numeroDD) {
        String xpathListaGenerico = "//*[@id=\"left-column\"]/dl[1]/dd[";

        String xpathLista1b = "]/span[";
        String xpathLista1c = "]/a";

        String xpathLista2b = "]/a[";
        String xpathLista2c = "]";

        List<String> generos = new ArrayList<String>();
        WebElement caracteristica;

        for (int i = 1; i <= 3; i++) {
            if (checkExceptionCompleja(xpathListaGenerico, numeroDD, xpathLista1b, i, xpathLista1c)) {
                String xpathLista1 = xpathListaGenerico + numeroDD + xpathLista1b + i + xpathLista1c;
                caracteristica = driver.findElement(By.xpath(xpathLista1));
                if (caracteristica.getText() != "") {
                    generos.add(caracteristica.getText());
                }
            } else {
                i = 3;
            }
        }

        for (int i = 1; i <= 3; i++) {
            if (checkExceptionCompleja(xpathListaGenerico, numeroDD, xpathLista2b, i, xpathLista2c)) {
                String xpathLista1 = xpathListaGenerico + numeroDD + xpathLista2b + i + xpathLista2c;
                caracteristica = driver.findElement(By.xpath(xpathLista1));
                if (caracteristica.getText() != "") {
                    generos.add(caracteristica.getText());
                }
            } else {
                i = 3;
            }
        }
        return generos;
    }


    public List<String> getGuionistasLista(Integer numeroDD) {
        String xpathLista1a = "//*[@id=\"left-column\"]/dl[1]/dd[";
        String xpathLista1b = "]/div/span[ ";
        String xpathLista1c = "]/span";

        List<String> guionistas = new ArrayList<String>();
        WebElement caracteristica;

        for (int i = 1; i <= 10; i++) {
            if (checkExceptionCompleja(xpathLista1a, numeroDD, xpathLista1b, i, xpathLista1c)) {
                String xpathLista1 = xpathLista1a + numeroDD + xpathLista1b + i + xpathLista1c;
                caracteristica = driver.findElement(By.xpath(xpathLista1));
                if (caracteristica.getText() != "") {
                    guionistas.add(caracteristica.getText());
                }
            } else {
                i = 10;
            }
        }
        return guionistas;
    }


    public Boolean checkException(String xpathMod1, String xpathMod2, int i) {
        try {
            driver.findElement(By.xpath(xpathMod1 + i + xpathMod2));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean checkExceptionCompleja(String xpathMod1, int i1, String xpathMod2, int i2, String xpathMod3) {
        try {
            driver.findElement(By.xpath(xpathMod1 + i1 + xpathMod2 + i2 + xpathMod3));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String localizarImagen() {
        WebElement urlImagen = driver.findElement(By.xpath("//*[@id=\"movie-main-image-container\"]/a/img"));
        String url = urlImagen.getAttribute("src");
        return url;
    }

    public static String localizarUrlImagen(WebDriver driver) {
        WebElement urlImagen = driver.findElement(By.xpath("//*[@id=\"movie-main-image-container\"]/a/img"));
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
