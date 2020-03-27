package org.seleniumdemo;

import java.util.List;

public class Pelicula {
    String titulo;
    String tituloOriginal;
    Integer anio;
    Integer duracion;
    String pais;
    String director1;
    String director2;
    String director3;
    String guionista1;
    String guionista2;
    String guionista3;
    String musica;
    String fotografia;
    String actor1;
    String actor2;
    String actor3;
    String actor4;
    String actor5;
    String actor6;
    String actor7;
    String actor8;
    String actor9;
    String actor10;
    String genero;
    String genero1;
    String genero2;
    String genero3;
    String genero4;
    String genero5;
    String genero6;
    String genero7;

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", tituloOriginal='" + tituloOriginal + '\'' +
                ", anio='" + anio + '\'' +
                ", duracion=" + duracion +
                ", pais='" + pais + '\'' +
                ", director1='" + director1 + '\'' +
                ", director2='" + director2 + '\'' +
                ", director3='" + director3 + '\'' +
                ", guionista1='" + guionista1 + '\'' +
                ", guionista2='" + guionista2 + '\'' +
                ", guionista3='" + guionista3 + '\'' +
                ", musica='" + musica + '\'' +
                ", fotografia='" + fotografia + '\'' +
                ", actor1='" + actor1 + '\'' +
                ", actor2='" + actor2 + '\'' +
                ", actor3='" + actor3 + '\'' +
                ", actor4='" + actor4 + '\'' +
                ", actor5='" + actor5 + '\'' +
                ", actor6='" + actor6 + '\'' +
                ", actor7='" + actor7 + '\'' +
                ", actor8='" + actor8 + '\'' +
                ", actor9='" + actor9 + '\'' +
                ", actor10='" + actor10 + '\'' +
                ", genero='" + genero + '\'' +
                ", genero1='" + genero1 + '\'' +
                ", genero2='" + genero2 + '\'' +
                ", genero3='" + genero3 + '\'' +
                ", genero4='" + genero4 + '\'' +
                ", genero5='" + genero5 + '\'' +
                ", genero6='" + genero6 + '\'' +
                ", genero7='" + genero7 + '\'' +
                ", genero8='" + genero8 + '\'' +
                ", genero9='" + genero9 + '\'' +
                ", genero10='" + genero10 + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", nota=" + nota +
                ", votos=" + votos +
                '}';
    }

    public Pelicula() {
    }

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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDirector1() {
        return director1;
    }

    public void setDirector1(String director1) {
        this.director1 = director1;
    }

    public String getDirector2() {
        return director2;
    }

    public void setDirector2(String director2) {
        this.director2 = director2;
    }

    public String getDirector3() {
        return director3;
    }

    public void setDirector3(String director3) {
        this.director3 = director3;
    }

    public String getGuionista1() {
        return guionista1;
    }

    public void setGuionista1(String guionista1) {
        this.guionista1 = guionista1;
    }

    public String getGuionista2() {
        return guionista2;
    }

    public void setGuionista2(String guionista2) {
        this.guionista2 = guionista2;
    }

    public String getGuionista3() {
        return guionista3;
    }

    public void setGuionista3(String guionista3) {
        this.guionista3 = guionista3;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    public String getActor4() {
        return actor4;
    }

    public void setActor4(String actor4) {
        this.actor4 = actor4;
    }

    public String getActor5() {
        return actor5;
    }

    public void setActor5(String actor5) {
        this.actor5 = actor5;
    }

    public String getActor6() {
        return actor6;
    }

    public void setActor6(String actor6) {
        this.actor6 = actor6;
    }

    public String getActor7() {
        return actor7;
    }

    public void setActor7(String actor7) {
        this.actor7 = actor7;
    }

    public String getActor8() {
        return actor8;
    }

    public void setActor8(String actor8) {
        this.actor8 = actor8;
    }

    public String getActor9() {
        return actor9;
    }

    public void setActor9(String actor9) {
        this.actor9 = actor9;
    }

    public String getActor10() {
        return actor10;
    }

    public void setActor10(String actor10) {
        this.actor10 = actor10;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero1() {
        return genero1;
    }

    public void setGenero1(String genero1) {
        this.genero1 = genero1;
    }

    public String getGenero2() {
        return genero2;
    }

    public void setGenero2(String genero2) {
        this.genero2 = genero2;
    }

    public String getGenero3() {
        return genero3;
    }

    public void setGenero3(String genero3) {
        this.genero3 = genero3;
    }

    public String getGenero4() {
        return genero4;
    }

    public void setGenero4(String genero4) {
        this.genero4 = genero4;
    }

    public String getGenero5() {
        return genero5;
    }

    public void setGenero5(String genero5) {
        this.genero5 = genero5;
    }

    public String getGenero6() {
        return genero6;
    }

    public void setGenero6(String genero6) {
        this.genero6 = genero6;
    }

    public String getGenero7() {
        return genero7;
    }

    public void setGenero7(String genero7) {
        this.genero7 = genero7;
    }

    public String getGenero8() {
        return genero8;
    }

    public void setGenero8(String genero8) {
        this.genero8 = genero8;
    }

    public String getGenero9() {
        return genero9;
    }

    public void setGenero9(String genero9) {
        this.genero9 = genero9;
    }

    public String getGenero10() {
        return genero10;
    }

    public void setGenero10(String genero10) {
        this.genero10 = genero10;
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

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    String genero8;
    String genero9;
    String genero10;
    String sinopsis;
    Double nota;
    Integer votos;

}
