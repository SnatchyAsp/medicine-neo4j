package com.zangmz.hit.medicineneo4j.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import lombok.NoArgsConstructor;

import static com.jayway.restassured.RestAssured.given;

@NoArgsConstructor
public class RestTicketClient {

    private String tgt;
    private String st;
    private String service = "http://umlsks.nlm.nih.gov";
    private String username = "CharlesZang";
    private String password = "ZMZ4230369%4793%";
    private String authUri = "https://utslogin.nlm.nih.gov";
    private String apikey = "783581a0-ed8f-420c-9a5d-ddc4fe2c6dd4";

    public RestTicketClient (String username, String password) {

        this.username = username;
        this.password = password;
    }

    public RestTicketClient (String apikey) {
        this.apikey = apikey;
    }


    public String getTgt()

    {
        String tgt = null;
        if(this.username != null && this.password != null){
            RestAssured.baseURI=authUri;
            Response response =  given()//.log().all()
                    .request().with()
                    .param("username", username)
                    .param("password", password)
                    .expect()
                    .statusCode(201)
                    .when().post("/cas/v1/tickets");

            Headers h = response.getHeaders();
            tgt = h.getValue("location").substring(h.getValue("location").indexOf("TGT"));
            //response.then().log()

        } else if (apikey != null) {
            RestAssured.baseURI=authUri;
            Response response =  given()//.log().all()
                    .request().with()
                    .param("apikey", apikey)
                    .expect()
                    .statusCode(201)
                    .when().post("/cas/v1/api-key");

            Headers h = response.getHeaders();
            tgt = h.getValue("location").substring(h.getValue("location").indexOf("TGT"));
        }
        return tgt;
    }


    public String getST(String tgt)
    {
        RestAssured.baseURI=authUri;
        Response response =  given()//.log().all()
                .request().with()
                .param("service", service)
                .expect()
                .statusCode(200)
                .when().post("/cas/v1/tickets/" + tgt);

        String st = response.getBody().asString();
        //response.then().log().all();
        return st;
    }

    public void logout(String ticket)
    {
        RestAssured.baseURI=authUri;
        Response response =  given()//.log().all()
                .request().with()
                .param("service", service)
                .expect()
                .statusCode(200)
                .when().delete("/cas/v1/tickets/" + ticket);
        //	response.then().log().all();
    }


}
