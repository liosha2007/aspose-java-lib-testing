package com.groupdocs;

import com.groupdocs.converters.PdfConvert;
import com.groupdocs.converters.WordConvert;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: lioshaNout
 * Date: 08.10.13
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //CONFIG
        int width = 960;
        int height = 1280;
        int pageToProcess = 5;
        int numOfCicles = 1;
        String pdfInputFile = new File("input-pdf-file.pdf").getAbsolutePath();
        String wordInputFile = new File("input-doc-file.doc").getAbsolutePath();
        String outputDir = "./directory-out/";
        long average;
        //END OF CONFIG
        /*
         * File convertion
         */
        //PDF CONVERTION
        PdfConvert pdf = new PdfConvert();
        System.out.println("--------------- [ DETAILS ] ---------------");
        System.out.println("File name: " + new File(pdfInputFile).getName());
        System.out.println("Image width: " + width + ", height: " + height);
        System.out.println("--------------- [ RESULTS ] ---------------");
        for(int i = 1; i < pageToProcess; i++){
            average = 0;
            for(int j = 0; j < numOfCicles; j++){
                long time = pdf.convert(pdfInputFile, width, height, outputDir, i);
                if(time > 0){
                    average += time;
                }
            }
            System.out.println("Page count: " + i);
            System.out.println("Time to extract images: " + average/numOfCicles + "ms");
            System.out.println("-------------------------------------------");
        }
        
        //WORD CONVERTION
        WordConvert word = new WordConvert();
        System.out.println("--------------- [ DETAILS ] ---------------");
        System.out.println("File name: " + new File(wordInputFile).getName());
        System.out.println("Image width: " + width + ", height: " + height);
        System.out.println("--------------- [ RESULTS ] ---------------");
        for(int i = 1; i < pageToProcess; i++){
            average = 0;
            for(int j = 0; j < numOfCicles; j++){
                long time = word.convert(wordInputFile, width, height, outputDir, i);
                if(time > 0){
                    average += time;
                }
            }
            System.out.println("Page count: " + i);
            System.out.println("Time to extract images: " + average/numOfCicles + "ms");
            System.out.println("-------------------------------------------");
        }
    }
}
