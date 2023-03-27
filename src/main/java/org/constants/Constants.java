package org.constants;

import java.io.File;

public class Constants {


    public static final String EXCELPATH = System.getProperty("user.dir")+"/src/test/java/org/utils/APITestDataSheet.xlsx";
    public static final String RUNMANAGERSHEET="Test";

    public static final String fileUploadSuccessMessage = "File uploaded and saved to the database successfully!";
    public static final String fileUploadFailureMessage = "Bad data in file. Correct file data and rename file.";
    public static final String PathToConfigFile ="./src/test/java/org/utils/config.properties";
    public static final int UploadSuccessStatusCode= 201;
    public static final int UploadFailureStatusCode= 400;
    public static final File acceptedFile= new File(System.getProperty("user.dir")+"/src/test/java/org/fileupload/positive.xml");
    public static final File unacceptedFile= new File(System.getProperty("user.dir")+"/src/test/java/org/fileupload/negative.xml");
    public static final File acceptedFileFromExcel= new File(System.getProperty("user.dir")+"/src/test/java/org/fileupload/positive.xml");
    //public static final File unacceptedFileExcel= new File(System.getProperty("user.dir")+getParticularCellData());

}
