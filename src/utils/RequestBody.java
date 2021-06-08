package utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestBody {



    public static String buildIssuedRequestBodyJson(String summary,String projectId,String issueTypeValue,String steps){

        JSONObject issueBody = new JSONObject();
        JSONObject fields = new JSONObject();
        issueBody.put("fields",fields);
        // add summary
        fields.put("summary",summary);

        // add project obj
        JSONObject project = new JSONObject();
        project.put("id",projectId);
        fields.put("project",project);

        // add  issuetype obj
        JSONObject issuetype = new JSONObject();
        issuetype.put("name",issueTypeValue);
        fields.put("issuetype",issuetype);

        //add description obj
        JSONObject description = new JSONObject();
        description.put("type","doc");
        description.put("version",1);

        JSONArray content = new JSONArray();
        JSONObject contectFields = new JSONObject();
        contectFields.put("type","paragraph");

        JSONArray subContent = new JSONArray();
        JSONObject subContentFields = new JSONObject();

        subContentFields.put("type","text");
        subContentFields.put("text",steps);
        subContent.put(subContentFields);
        contectFields.put("content",subContent);

        content.put(contectFields);
        description.put("content",content);
        fields.put("description",description);

        //System.out.println(issueBody.toString());

        return issueBody.toString();
    }



}
