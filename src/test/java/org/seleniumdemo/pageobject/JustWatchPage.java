package org.seleniumdemo.pageobject;

import static org.seleniumdemo.utils.ConvertirStringList.crearUrlJustWatch;

public class JustWatchPage {
    public static String devolverUrlJustWatch(String nombrePelicula){
        String url = crearUrlJustWatch(nombrePelicula);
        return url;
    }
}
