package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results


    @RequestMapping(value = "results")
    public String search(Model model, String searchType, String searchTerm) {

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        if(searchType.equals("all")){
            for(HashMap<String,String> job : JobData.findByValue(searchTerm)) {
                jobs.add(job);
            }
        } else {
            for(HashMap<String,String> job : JobData.findByColumnAndValue(searchType,searchTerm)) {
                jobs.add(job);
            }
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);


        return "search";

    }
}
