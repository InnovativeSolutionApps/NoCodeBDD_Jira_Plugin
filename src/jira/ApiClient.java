package jira;

import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.ConfigConstants;
import utils.RequestBody;

import java.io.IOException;
import java.util.HashMap;

public class ApiClient extends JavaOkHttpClient {

    public int getLatestReportId() {

        int reportId = 0; // initial value is always 0 instead of Null

        Response response = callGETApi(ConfigConstants.GET_REPORT_RUN_TIMES_API);

        if (response.code() == 200) {
            try {

                String responseJsonStr = response.body().string().trim();
                JSONArray resJsonArray = new JSONArray(responseJsonStr);
                //System.out.println(resJsonArray.length());

                JSONObject reportObj = resJsonArray.getJSONObject(resJsonArray.length() - 1);
                reportId = reportObj.getInt("id");
                // System.out.println("reportId : " + reportId);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("GET_REPORT_RUN_TIMES_API Request Call failed");
        }

        return reportId;
    }


    public HashMap<String, String> getReportDetails(int reportId, String scenarioId) {

        HashMap<String, String> scenarioDetails = new HashMap<>();
        Response response = callGETApi(ConfigConstants.GET_REPORT_DETAILS_API + reportId);

        if (response.code() == 200) {
            try {

                String responseJsonStr = response.body().string().trim();
                //System.out.println("responseJsonStr : "+ responseJsonStr);

                JSONArray resJsonArray = new JSONArray(responseJsonStr);
                //System.out.println(resJsonArray.toString());

                for (int i = 0; i < resJsonArray.length(); i++) {  // loop through features array obj

                    JSONObject jsonObject = resJsonArray.getJSONObject(i); // feature obj

                    JSONArray scenarioArrayObj = jsonObject.getJSONArray("scenarioTOs"); // get scenario array obj

                    //System.out.println(scenarioArrayObj.length());

                    for (int j = 0; j < scenarioArrayObj.length(); j++) { //  loop through scenario array Obj

                        JSONObject scenarioObj = scenarioArrayObj.getJSONObject(j); // get scenario obj
                        //System.out.println("?? : " + scenarioObj.toString());

                        if (scenarioObj.getString("scenarioId").equals(scenarioId)) {

                            System.out.println("scenarioId : " + scenarioObj.getString("scenarioId"));
                            // System.out.println("scenarioDescription : " + scenarioObj.getString("scenarioDescription"));

                            JSONArray stepsArrayObj = scenarioObj.getJSONArray("stepTOs");
                            StringBuilder bddSteps = new StringBuilder();

                            for (int k = 0; k < stepsArrayObj.length(); k++) {  // loop through steps Array Obj

                                JSONObject stepObj = stepsArrayObj.getJSONObject(k);
                                String stepDes = stepObj.getString("stepDescription");
                                bddSteps.append(stepDes + "\n");

                            }
                            scenarioDetails.put("scenarioDescription", scenarioObj.getString("scenarioDescription"));
                            scenarioDetails.put("steps", bddSteps.toString());


                            break;
                        }

                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("GET_REPORT_DETAILS_API Request Call failed");
        }

        return scenarioDetails;
    }


    public void createNewIssueInJira(String scenarioDescription, String projectId, String steps, String issueType) {

        String body = RequestBody.buildIssuedRequestBodyJson(scenarioDescription, projectId, issueType, steps);

        Response response = callCreateIssuePOSTJiraApi(ConfigConstants.CREATE_ISSUE_API, body);

        if (response.code() == 201) {
            try {
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("create New Issue In Jira Request Call failed");
        }
    }


}
