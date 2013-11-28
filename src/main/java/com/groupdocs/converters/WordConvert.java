package com.groupdocs.converters;

import com.aspose.words.*;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Bobkov
 */
public class WordConvert extends Converter{

    @Override
    public long convert(String inputFile, int width, int height, String outputDir, int pageToProcess) {
        try {
            String wordDir = outputDir + "/word/";
            dirClean(wordDir);
            createDir(wordDir);

            Document document = new Document(inputFile);
//            ImageSize imageSize = new ImageSize(width, height);
            document.updatePageLayout();
            long start = new Date().getTime();
            ImageSaveOptions imageSaveOptions = new ImageSaveOptions(SaveFormat.JPEG);
            // px per inch
            imageSaveOptions.setResolution(72);
            imageSaveOptions.setJpegQuality(100);
            for(int i = 0; i < pageToProcess; i++){
                imageSaveOptions.setPageIndex(i);
                document.save(wordDir + i + ".jpg", imageSaveOptions);
            }
            long end = new Date().getTime();
            return (end - start);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
}
