package com.groupdocs.converters;

import com.aspose.ms.System.Drawing.Imaging.ImageFormat;
import com.aspose.pdf.Document;
import com.aspose.pdf.JpegDevice;
import com.aspose.pdf.Resolution;
import com.aspose.pdf.facades.PageSize;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Bobkov
 */
public class PdfConvert extends Converter{

    @Override
    public long convert(String inputFile, int width, int height, String outputDir, int pageToProcess){
        try {
            String pdfDir = outputDir + "/pdf/";
            dirClean(pdfDir);
            createDir(pdfDir);

            com.aspose.pdf.Document pdfDocument = new Document(inputFile);
            long start = new Date().getTime();
            for (int count = 1; count <= pdfDocument.getPages().size(); count++)
            {
                java.io.OutputStream imageStream = new java.io.FileOutputStream(outputDir + "/" + count + ".jpg");

                PageSize pageSize = new PageSize(width, height);
                Resolution resolution = new Resolution(width, height);
                JpegDevice jpegDevice = new JpegDevice(pageSize, resolution);
                jpegDevice.process(pdfDocument.getPages().get_Item(count), imageStream);
                imageStream.close();
            }
            
//            com.aspose.pdf.facades.PdfConverter converter = new com.aspose.pdf.facades.PdfConverter();
//            converter.bindPdf(inputFile);
//            long start = new Date().getTime();
//            converter.doConvert();
//            converter.setResolution(new Resolution(width / 96, height / 96));
//            int count = 0;
//            while (converter.hasNextImage() && count < pageToProcess){
//                converter.getNextImage(pdfDir + count + ".jpg", ImageFormat.getJpeg());
//                count++;
//            }
//            converter.close();
            long end = new Date().getTime();
            return (end - start);
        } catch (IOException ex) {
            Logger.getLogger(PdfConvert.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
