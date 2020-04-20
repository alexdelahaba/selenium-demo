package org.seleniumdemo.pageobject;

import static org.seleniumdemo.utils.ConvertirStringList.crearUrlIMDB;

public class IMDBPage {

    public static String devolverUrlIMDB(String nombrePelicula) {
        String url = crearUrlIMDB(nombrePelicula);
        return url;
    }
}
