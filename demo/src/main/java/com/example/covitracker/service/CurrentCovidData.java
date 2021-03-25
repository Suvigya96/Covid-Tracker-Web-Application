package com.example.covitracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.covitracker.service.model.LocationsData;

@Service
public class CurrentCovidData {

	private List<LocationsData> listdata = new ArrayList<>();
	
	public List<LocationsData> getListdata() {
		return listdata;
	}

	private static String dataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getCurrentCovidData() throws IOException, InterruptedException, URISyntaxException {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(dataUrl)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		
		for (CSVRecord record : records) {
			
			LocationsData data = new LocationsData();
			data.setState(record.get("Province/State"));
			data.setCountry(record.get("Country/Region"));
			data.setUpdatedCaseCount(Integer.parseInt(record.get(record.size() - 1)));
			
			int currentDayRecord = Integer.parseInt(record.get(record.size() - 1));
			int prevDayRecord = Integer.parseInt(record.get(record.size() - 2));
			
			data.setDifferenceFromPreviousDay(currentDayRecord - prevDayRecord);
			listdata.add(data);
		}

	}
}
