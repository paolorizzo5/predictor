package com.paolorizzo.predictor.utils;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class ParsingUtils {
	
	static Logger logger = LogManager.getLogger("root");
	
	@Test
	public void direttaItParser() throws IOException{
		
		Document doc = Jsoup.connect("http://www.diretta.it/calcio/").get();
		Elements leagues = doc.select("ul");
		for (Element league : leagues) {
			Elements links = league.getElementsByClass("submenu");
			for (Element element : links) {
				Elements li = element.select("li");
				for (Element element2 : li) {
					Elements aHref = element2.select("a");
					System.out.println();
					readLleaueData(aHref.attr("abs:href"));
				}
				
			}
			logger.debug(league.toString());
		}
		
		
	}

	private void readLleaueData(String leagueLink) throws IOException {
		Document doc = Jsoup.connect(leagueLink + "archivio/").get();
		
	}

}
