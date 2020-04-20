package org.seleniumdemo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.seleniumdemo.utils.ConvertirStringList.crearUrlRottenTomatoes;

public class RottenTomatoesPage {


    public static String devolverUrlRottenTomatoes(String nombrePelicula) {
        String url = crearUrlRottenTomatoes(nombrePelicula);

        return url;
    }


}
