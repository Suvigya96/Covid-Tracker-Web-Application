package com.example.covitracker.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.covitracker.service.CurrentCovidData;
import com.example.covitracker.service.model.LocationsData;

@Controller
public class UrlController {

	@Autowired
	CurrentCovidData currentCovidData;

	@GetMapping(path = "/")
	public String home(Model model) {

		List<LocationsData> totalList = currentCovidData.getListdata();
		int totalNumberOfCovidCases = 0;
		int totalNumberOfNewCases = 0;
		for (LocationsData list : totalList) {
			totalNumberOfCovidCases += list.getUpdatedCaseCount();
			totalNumberOfNewCases += list.getDifferenceFromPreviousDay();
		}

		model.addAttribute("locationsData", totalList);
		model.addAttribute("totalNumberOfCovidCases", totalNumberOfCovidCases);
		model.addAttribute("totalNumberOfNewCases", totalNumberOfNewCases);

		return "homepage";
	}

}
