package org.seleniumdemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExtraerUrlTrailer {


    public static String extraerUrlTrailer(WebDriver driver, List<String> palabrasPelicula) {
        String urlBusqueda = "https://www.youtube.com/results?search_query=";
        for (int i = 0; i < palabrasPelicula.size(); i++) {
            urlBusqueda += palabrasPelicula.get(i) + "+";
        }
        urlBusqueda += "trailer+español";
        driver.get(urlBusqueda);
        WebElement trailerPelicula = driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
        trailerPelicula.click();
        String url = driver.getCurrentUrl();
        return url;

    }
}
