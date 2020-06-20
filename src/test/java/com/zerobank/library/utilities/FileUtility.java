package com.zerobank.library.utilities;

import java.io.File;

public class FileUtility {

    public static File getMostRecentFile(String folder) {
        File directory = new File(folder);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }


}
