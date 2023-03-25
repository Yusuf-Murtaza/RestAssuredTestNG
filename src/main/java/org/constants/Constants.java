package org.constants;

import java.io.File;

public class Constants {


    public static final String EXCELPATH = System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx";

    public static final String fileUploadSuccessMessage = "File uploaded and saved to the database successfully!";
    public static final String fileUploadFailureMessage = "Bad data in file. Correct file data and rename file.";
    public static final String PathToConfigFile ="./src/test/java/org/utils/config.properties";

    public static final int UploadSuccessStatusCode= 201;
    public static final int UploadFailureStatusCode= 400;

}
