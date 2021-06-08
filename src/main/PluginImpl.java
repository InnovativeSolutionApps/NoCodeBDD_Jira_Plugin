package main;

import jira.ApiClient;
import plugin.PluginInterface;
import utils.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class PluginImpl extends ApiClient implements PluginInterface {

    @Override
    public void callMe(List<?> list) {

        System.out.println("plugin called");

        int id = getLatestReportId();
        System.out.println("Latest Report Id : " + id);

        HashMap<String,String> scenarioDetails = getReportDetails(id,"iX0lH7ckUP");

       // System.out.println("scenarioDescription : " + scenarioDetails.get("scenarioDescription") );
       // System.out.println("Steps  : \n" + scenarioDetails.get("steps"));

        createNewIssueInJira(scenarioDetails.get("scenarioDescription"),"10000",scenarioDetails.get("steps"),"Bug");


    }


}


