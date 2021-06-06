package main;

import jira.ApiClient;
import plugin.PluginInterface;

import java.io.IOException;
import java.util.List;


public class PluginImpl extends ApiClient implements PluginInterface {

    @Override
    public void callMe(List<?> list) {

        System.out.println("plugin called");

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Scenario Id : " + list.get(i));
        }

        int id = getLatestReportId();
        System.out.println("id " + id);

        



    }


}


