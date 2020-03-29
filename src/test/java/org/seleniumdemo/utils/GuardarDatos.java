package org.seleniumdemo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.seleniumdemo.Pelicula;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GuardarDatos {

    static String pattern = "MM-dd-yyyy_hh-ss";
    static DateFormat df = new SimpleDateFormat(pattern);
    static Date today = Calendar.getInstance().getTime();
    static String todayAsString = df.format(today);
    static String FILE_NAME = "Excel/BBDD_" + todayAsString + ".xlsx";

public static String convertirListaAString(List<String> lista){


    String res = String.join(",", lista);


    return res;

}



    public static Integer crearArchivoBasico(Pelicula pelicula, int i) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Peliculas Marzo 20");

        List<String> list = Arrays.asList("1", "2", "1", "2", "1", "2");

        String res = String.join(",", list);

        Object[][] datatypes = {
                {"Pelicula", "Título Original", "Año", "Duración", "País", "Directores", "Guionistas", "Música", "Fotografía", "Reparto", "Género", "Sinopsis", "Nota", "Votos", "Url", "Nota Personal", "Comentario"},
                {pelicula.getTitulo(), pelicula.getTituloOriginal(), pelicula.getAnio(), pelicula.getDuracion(),
                        pelicula.getPais(), convertirListaAString(pelicula.getDirectores()), convertirListaAString(pelicula.getGuionistas()), convertirListaAString(pelicula.getMusica()),
                        convertirListaAString(pelicula.getFotografia()), convertirListaAString(pelicula.getActores()),
                        convertirListaAString(pelicula.getGeneros()), pelicula.getSinopsis(), pelicula.getNota(), pelicula.getVotos(), pelicula.getUrl(), 0, ""}

        };
        /*for (int i = 0; i < sheet.getLastRowNum()) {
            Row ultimaFila = sheet.getRow(0);
        }*/
        int rowNum = 0;
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
                }else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        i = 3;
        return i;
    }
/*

    @Test
    public void leerArchivoExcel() {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void crearArchivoBasico(Pelicula pelicula) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        List<String> list = Arrays.asList("1","2","1","2","1","2");
        String res = String.join(",",list);

        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", res},
                {"float", res, 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        int rowNum = 0;
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
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
*/
}