package com.groupdocs.converters;

import com.aspose.ms.System.Drawing.Imaging.ImageFormat;
import com.aspose.pdf.Document;
import com.aspose.pdf.JpegDevice;
import com.aspose.pdf.PageCollection;
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
            PageCollection pageCollection = pdfDocument.getPages();
            long start = new Date().getTime();
            for (int count = 1; count < pageCollection.size() && (pageToProcess == -1 || pageToProcess-- > 0); count++)
            {
                java.io.OutputStream imageStream = new java.io.FileOutputStream(pdfDir + count + ".jpg");

                PageSize pageSize = new PageSize(width, height);
                Resolution resolution = new Resolution(72);
                JpegDevice jpegDevice = new JpegDevice(pageSize, resolution, 100);
                jpegDevice.process(pageCollection.get_Item(count), imageStream);
                imageStream.close();
            }
            long end = new Date().getTime();
            return (end - start);
        } catch (IOException ex) {
            Logger.getLogger(PdfConvert.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
