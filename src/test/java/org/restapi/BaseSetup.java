package org.restapi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.constants.Constants;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.lessThan;

public class BaseSetup {

    @BeforeClass
    public void setup () throws IOException {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getGlobalValues("BaseUri"))
                .setBasePath(getGlobalValues("BasePath"))
                .setContentType("multipart/form-data")
                .build();

        RestAssured.requestSpecification = requestSpecification;

    }


    public static String getGlobalValues(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(Constants.PathToConfigFile);
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();


    }
}