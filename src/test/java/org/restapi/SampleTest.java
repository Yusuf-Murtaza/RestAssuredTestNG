package org.restapi;

import io.restassured.response.Response;
import org.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.utils.ExcelUtil.*;

public class SampleTest extends BaseSetup{
    public void UploadFile(File fileName,int statusCode, String responseMessage) {

        Response resp=given().multiPart("datafil",fileName,"application/xml").multiPart("year",getGlobalValues("MultipartYear")).
                when().post().
                then().statusCode(statusCode).log().all().extract().response();

        Assert.assertEquals(resp.getBody().asString(), responseMessage);
        Assert.assertEquals(resp.getStatusCode(),statusCode);

    }

    public void UploadFileWithLoop(String filePath,String statusCodeString) {
        File fileName=new File(System.getProperty("user.dir")+filePath);
        int statusCodeValue=Integer.parseInt(statusCodeString);
        Response resp=given().multiPart("datafil",fileName,"application/xml").multiPart("year",getGlobalValues("MultipartYear")).
                when().post().
                then().statusCode(statusCodeValue).log().all().extract().response();

        Assert.assertEquals(resp.getStatusCode(),statusCodeValue);
    }

    @Test
    public void FileUploadTest(){
        UploadFile(Constants.acceptedFile,Constants.UploadSuccessStatusCode,Constants.fileUploadSuccessMessage);
        UploadFile(Constants.unacceptedFile,Constants.UploadFailureStatusCode,Constants.fileUploadFailureMessage);

    }
    @Test
    public void FileUploadTestUsingExcel(){
        File acceptedFileExcel= new File(System.getProperty("user.dir")+getParticularCellData(getDataFromExcel("Test"),"TC1",2));
        File unacceptedFileExcel= new File(System.getProperty("user.dir")+getParticularCellData(getDataFromExcel("Test"),"TC2",2));
        int UploadSuccessStatusCodeExcel= Integer.parseInt(getParticularCellData(getDataFromExcel("Test"),"TC1",1));
        int UploadFailureStatusCodeExcel= Integer.parseInt(getParticularCellData(getDataFromExcel("Test"),"TC2",1));
        UploadFile(acceptedFileExcel,UploadSuccessStatusCodeExcel,Constants.fileUploadSuccessMessage);
        UploadFile(unacceptedFileExcel,UploadFailureStatusCodeExcel,Constants.fileUploadFailureMessage);
    }

    @Test
    public void FileUploadTestUsingExcelForLoop(){
        for(int i=0;i<getTotalRowCount("Test");i++){
            String filePath=getAllColumnData("Test",3).get(i);
            String statusCodeString=getAllColumnData("Test",2).get(i);
            UploadFileWithLoop(filePath,statusCodeString);
        }
    }

}
