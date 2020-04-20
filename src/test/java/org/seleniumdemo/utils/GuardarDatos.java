package org.seleniumdemo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.seleniumdemo.Pelicula;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.seleniumdemo.utils.ExtraerUrlTrailer.extraerUrlTrailer;

public class GuardarDatos {

    static String pattern = "MM-dd-yyyy_hh-ss";
    static DateFormat df = new SimpleDateFormat(pattern);
    static Date today = Calendar.getInstance().getTime();
    static String todayAsString = df.format(today);
    static String FILE_NAME = "Excel/CopiasSeguridad/BBDD_" + todayAsString + ".xlsx";
    private static final String FILE_NAME_REAL = "Excel/0000000000000000000BBDD.xlsx";

    public static String convertirListaAString(List<String> lista) {

        try {
            String res = String.join(",", lista);
            return res;
        } catch (Exception e) {
            return null;
        }


    }


    public static Integer crearArchivoBasico(Pelicula pelicula, int i) throws IOException {
        try {
            Object[][] datatypes = {
                    {pelicula.getTitulo(), pelicula.getTituloOriginal(), pelicula.getAnio(), pelicula.getDuracion(),
                            pelicula.getPais(), convertirListaAString(pelicula.getDirectores()), convertirListaAString(pelicula.getGuionistas()), convertirListaAString(pelicula.getMusica()),
                            convertirListaAString(pelicula.getFotografia()), convertirListaAString(pelicula.getActores()),
                            convertirListaAString(pelicula.getGeneros()), pelicula.getSinopsis(), pelicula.getNota(), pelicula.getVotos(), pelicula.getUrl(), 0, ""}

            };
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Integer lastRow = 0;
            for (int celdaEscrita = 0; celdaEscrita < 9999; celdaEscrita++) {
                try {
                    sheet.getRow(celdaEscrita).getCell(0);
                } catch (Exception e) {
                    lastRow = celdaEscrita;
                    break;
                }
            }

            int rowNum = lastRow;
            System.out.println("Creating excel");

            for (Object[] datatype : datatypes) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : datatype) {
                    Cell cell = row.createCell(colNum++);

                    if (field instanceof String) {
                        if (field.equals("Se ha producido algún tipo de error. Revisar esta película")) {
                            CellStyle style = workbook.createCellStyle();
                            style.setFillForegroundColor(IndexedColors.RED.getIndex());
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            Font font = workbook.createFont();
                            font.setColor(IndexedColors.WHITE.getIndex());
                            font.setBold(true);
                            style.setFont(font);
                            cell.setCellStyle(style);
                        }
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    } else if (field instanceof Double) {
                        cell.setCellValue((Double) field);
                    }
                }
            }

            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_REAL);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();



        /*for (int i = 0; i < sheet.getLastRowNum()) {
            Row ultimaFila = sheet.getRow(0);
        }*/


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        i = 3;
        return i;
    }

    public static void guardarDatosBBDD(Pelicula pelicula) throws IOException {
        try {
            Object[][] datatypes = {
                    {pelicula.getTitulo(), pelicula.getTituloOriginal(), pelicula.getAnio(), pelicula.getDuracion(),
                            pelicula.getPais(), convertirListaAString(pelicula.getDirectores()), convertirListaAString(pelicula.getGuionistas()), convertirListaAString(pelicula.getMusica()),
                            convertirListaAString(pelicula.getFotografia()), convertirListaAString(pelicula.getActores()),
                            convertirListaAString(pelicula.getGeneros()), pelicula.getSinopsis(), pelicula.getNota(), pelicula.getVotos(), pelicula.getUrl(), 0, "",
                            pelicula.getUrlTrailer(), pelicula.getUrlRottenTomatoes(), pelicula.getUrlIMDB(), pelicula.getUrlJustWatch(),pelicula.getUrlImagen(), pelicula.getId()}

            };
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Integer lastRow = 0;
            for (int celdaEscrita = 950; celdaEscrita < 2500; celdaEscrita++) {
                try {
                    sheet.getRow(celdaEscrita).getCell(0);
                } catch (Exception e) {
                    lastRow = celdaEscrita;
                    break;
                }
            }

            int rowNum = lastRow;
            System.out.println("Creating excel");

            for (Object[] datatype : datatypes) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : datatype) {
                    Cell cell = row.createCell(colNum++);

                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    } else if (field instanceof Double) {
                        cell.setCellValue((Double) field);
                    }
                }
            }

            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_REAL);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();



        /*for (int i = 0; i < sheet.getLastRowNum()) {
            Row ultimaFila = sheet.getRow(0);
        }*/


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public static String devolverUrlTrailer(WebDriver driver, String nombrePelicula) {
        String[] grupoPalabras = nombrePelicula.split(" ");
        ArrayList<String> listaPalabras = new ArrayList<>();
        for (String palabra : grupoPalabras) {
            listaPalabras.add(palabra);
        }
        String url = extraerUrlTrailer(driver, listaPalabras);
        return url;
    }

    public static void leerTituloPelicula(WebDriver driver, int i) {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i);
            String nombrePelicula = row.getCell(0).getStringCellValue();
            String[] grupoPalabras = nombrePelicula.split(" ");
            ArrayList<String> listaPalabras = new ArrayList<>();
            for (String palabra : grupoPalabras) {
                listaPalabras.add(palabra);
            }
            String url = extraerUrlTrailer(driver, listaPalabras);
            Cell cell1 = row.createCell(17);
            cell1.setCellValue(url);


            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_REAL);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void guardarUrlImagen(int i, String urlImagen) {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i+1);


            Cell cell1 = row.createCell(21);
            cell1.setCellValue(urlImagen);


            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_REAL);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void guardarIdPelicula(int i, String urlMod) {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i+1);


            Cell cell1 = row.createCell(22);
            cell1.setCellValue(urlMod);


            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_REAL);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void guardarUrlsExtras(Pelicula pelicula, int i) {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i+1);


            Cell cell1 = row.createCell(20);
            cell1.setCellValue(pelicula.getUrlJustWatch());
            Cell cell2 = row.createCell(19);
            cell2.setCellValue(pelicula.getUrlIMDB());
            Cell cell3 = row.createCell(18);
            cell3.setCellValue(pelicula.getUrlRottenTomatoes());

            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_REAL);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void hacerCopiaDeSeguridad() {
        String pattern = "MM-dd-yyyy_HH-mm-ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        String FILE_NAME = "Excel/CopiasSeguridad/BBDD_" + todayAsString + ".xlsx";
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            excelFile.close();
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {

        }
    }

}