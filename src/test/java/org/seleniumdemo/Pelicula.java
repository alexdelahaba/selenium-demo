package org.seleniumdemo;

import java.util.List;

public class Pelicula {
    String titulo;
    String tituloOriginal;
    String anio;
    String duracion;
    String pais;
    List<String> directores;
    List<String> guionistas;
    List<String> musica;
    List<String> fotografia;
    List<String> actores;
    List<String> generos;
    String sinopsis;
    Double nota;
    String votos;
    String url;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<String> getDirectores() {
        return directores;
    }

    public void setDirectores(List<String> directores) {
        this.directores = directores;
    }

    public List<String> getGuionistas() {
        return guionistas;
    }

    public void setGuionistas(List<String> guionistas) {
        this.guionistas = guionistas;
    }

    public List<String> getMusica() {
        return musica;
    }

    public void setMusica(List<String> musica) {
        this.musica = musica;
    }

    public List<String> getFotografia() {
        return fotografia;
    }

    public void setFotografia(List<String> fotografia) {
        this.fotografia = fotografia;
    }

    public List<String> getActores() {
        return actores;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getVotos() {
        return votos;
    }

    public void setVotos(String votos) {
        this.votos = votos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
