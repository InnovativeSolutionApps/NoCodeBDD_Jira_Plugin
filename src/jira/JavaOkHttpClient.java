package jira;

import okhttp3.*;
import utils.ConfigConstants;

import java.io.IOException;
import java.util.Base64;

public class JavaOkHttpClient {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private  String getAuthenticationToken(String userName, String password) {
        StringBuilder authenticationToken = new StringBuilder();
        authenticationToken.append("Basic ");
        authenticationToken.append(base64(new StringBuilder(userName).append(":").
                append(password).toString()));
        return authenticationToken.toString();
    }

    private  String base64(String s) {
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }

    public Response callJiraGETApi(String apiEndpoint) {

        System.out.println("apiEndpoint : " + apiEndpoint);

        Response response = null;

        try {

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url(apiEndpoint)
                    .method("GET", null)
                    .addHeader("Authorization", getAuthenticationToken(ConfigConstants.USER_NAME, ConfigConstants.PASSWORD))
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build();

            response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    public Response callGETApi(String apiEndpoint) {
        Response response = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(apiEndpoint)
                    .method("GET", null)
                    .build();
            response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    public Response callCreateIssuePOSTJiraApi(String apiEndpoint,String body){

        Response response = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder().
                    url(apiEndpoint).
                    post(RequestBody.create(JSON, body)).
                    addHeader("Authorization", getAuthenticationToken(ConfigConstants.USER_NAME, ConfigConstants.PASSWORD)).
                    addHeader("Accept", "application/json").
                    addHeader("Content-Type", "application/json").
                    build();

            response = client.newCall(request).execute();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


}
