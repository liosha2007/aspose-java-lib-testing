package com.groupdocs.converters;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Alex Bobkov
 */
public abstract class Converter {
    public abstract long convert(String inputFile, int width, int height, String outputDir, int pageToProcess);
    
    protected void dirClean(String outputDir) throws IOException{
        if (new File(outputDir).exists()) {
            FileUtils.deleteDirectory(new File(outputDir));
        }
    }
    
    protected void createDir(String path){
        new File(path).mkdirs();
    }
}
