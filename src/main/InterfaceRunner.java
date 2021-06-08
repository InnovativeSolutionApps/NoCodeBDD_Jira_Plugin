package main;

import java.util.ArrayList;
import java.util.List;

public class InterfaceRunner {

     public static void main(String args[]) {

      // initialize object of PluginImpl class
      PluginImpl plugin = new PluginImpl();

      // create list which contains scenario ids
      List<String> scenarioIds = new ArrayList<>();
      // add values to list
      scenarioIds.add("UG9dTUxRJY");

      // calling PluginInterface callMe method
      plugin.callMe(scenarioIds);

     }
}
