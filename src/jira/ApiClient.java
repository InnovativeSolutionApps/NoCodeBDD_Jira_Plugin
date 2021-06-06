package jira;

import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ApiClient extends JavaOkHttpClient {

    public  int getLatestReportId() {

        int reportId = 0; // initial value is always 0 instead of Null

        Response response =  callGETApi(ConfigConstants.GET_REPORT_RUN_TIMES_API);

        if (response.code() == 200) {
            try {

                String responseJsonStr = response.body().string().trim();
                JSONArray resJsonArray = new JSONArray(responseJsonStr);
                System.out.println(resJsonArray.length());

                JSONObject reportObj =  resJsonArray.getJSONObject(resJsonArray.length()-1);
                reportId = reportObj.getInt("id");
                System.out.println("reportId : " + reportId);

            }catch (IOException e){
                e.printStackTrace();
            }

        } else {
            System.out.println("GET_REPORT_RUN_TIMES_API Request Call failed");
        }

        return reportId;
    }



}
