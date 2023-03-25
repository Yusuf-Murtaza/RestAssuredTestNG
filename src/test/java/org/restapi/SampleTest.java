package org.restapi;

import io.restassured.response.Response;
import org.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class SampleTest extends BaseSetup{

    @Test
    public void UploadAcceptedFileTest() throws IOException {

        File acceptedFile= new File(System.getProperty("user.dir")+getGlobalValues("PostiveFlowFilePath"));
        Response resp = given().multiPart("datafil",acceptedFile,"application/xml").multiPart("year",getGlobalValues("MultipartYear")).post();
        resp.then().statusCode(Constants.UploadSuccessStatusCode).log().all();
        Assert.assertEquals(resp.getBody().asString(), Constants.fileUploadSuccessMessage);
        Assert.assertEquals(resp.getStatusCode(),Constants.UploadSuccessStatusCode);
    }

    @Test
    public void UploadUnacceptedFileTest() throws IOException {

        File unacceptedFile= new File(System.getProperty("user.dir")+getGlobalValues("NegativeFlowFilePath"));
        Response resp = given().multiPart("datafil",unacceptedFile,"application/xml").multiPart("year",getGlobalValues("MultipartYear")).post();
        resp.then().statusCode(Constants.UploadFailureStatusCode).log().all();
        Assert.assertEquals(resp.getBody().asString(),Constants.fileUploadFailureMessage);
        Assert.assertEquals(resp.getStatusCode(),Constants.UploadFailureStatusCode);
    }

}
