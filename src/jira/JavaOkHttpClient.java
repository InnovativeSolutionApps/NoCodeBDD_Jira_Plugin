package jira;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Base64;

public class JavaOkHttpClient {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static String getAuthenticationToken(String userName, String password) {
        StringBuilder authenticationToken = new StringBuilder();
        authenticationToken.append("Basic ");
        authenticationToken.append(base64(new StringBuilder(userName).append(":").
                append(password).toString()));
        return authenticationToken.toString();
    }

    private static String base64(String s) {
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }

    public Response callJiraGETApi(String apiEndpoint) {

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

            response  = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


      public Response callGETApi(String apiEndpoint){
          Response response = null;

          try {
              OkHttpClient client = new OkHttpClient().newBuilder()
                      .build();
              Request request = new Request.Builder()
                      .url(apiEndpoint)
                      .method("GET", null)
                      .build();
               response = client.newCall(request).execute();

          }catch (IOException e) {
              e.printStackTrace();
          }

          return  response;
      }



}
