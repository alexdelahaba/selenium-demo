package org.seleniumdemo.utils;

import java.util.ArrayList;
import java.util.List;

public class ConvertirStringList {

    public static String convertirListaAString(List<String> lista) {
        try {
            String res = String.join(",", lista);
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public static String crearUrlRottenTomatoes(String nombrePelicula) {
        String urlBase = "https://www.rottentomatoes.com/search?search=";
        String[] grupoPalabras = nombrePelicula.split(" ");
        ArrayList<String> listaPalabras = new ArrayList<>();
        for (String palabra : grupoPalabras) {
            listaPalabras.add(palabra);
        }
        for (int i = 0; i < listaPalabras.size(); i++) {
            urlBase += listaPalabras.get(i) + "%20";
        }
        return urlBase;
    }
    public static String crearUrlJustWatch(String nombrePelicula) {
        String urlBase = "https://www.justwatch.com/es/buscar?q=";
        String[] grupoPalabras = nombrePelicula.split(" ");
        ArrayList<String> listaPalabras = new ArrayList<>();
        for (String palabra : grupoPalabras) {
            listaPalabras.add(palabra);
        }
        for (int i = 0; i < listaPalabras.size(); i++) {
            urlBase += listaPalabras.get(i) + "%20";
        }
        return urlBase;
    }

    public static String crearUrlIMDB(String nombrePelicula) {
        String urlBase = "https://www.imdb.com/find?q=";
        String[] grupoPalabras = nombrePelicula.split(" ");
        ArrayList<String> listaPalabras = new ArrayList<>();
        for (String palabra : grupoPalabras) {
            listaPalabras.add(palabra);
        }
        for (int i = 0; i < listaPalabras.size(); i++) {
            urlBase += listaPalabras.get(i) + "+";
        }
        return urlBase;
    }
}
