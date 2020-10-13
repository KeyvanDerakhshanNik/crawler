package com.sedna;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;

public class CrawlerService {
	private HashSet<String> results;
	private HashSet<String> links;
	private HashSet<String> images;
	private HashSet<String> jss;
	private HashSet<String> csss;
	
	public CrawlerService() {
		results=new HashSet<String>();
		links = new HashSet<String>();
		images = new HashSet<String>();
		jss=new HashSet<String>();
		csss=new HashSet<String>();
	}

	public HashSet<String> getPageLinks(String URL) {
		if (!links.contains(URL) & URL.contains("www.sedna.com")) {
			try {
				if (links.add(URL)) {
					System.out.println(" ----- > The Link: ");
					System.out.println(URL);					
					results.add("<h1>"+URL+"</h1>");
				}
				//Fetch the HTML code
				Document document = Jsoup.connect(URL).get();
				//Parse the HTML 
				Elements linksOnPage = document.select("a[href]");
				Elements imagesOnPage = document.select("img");
				Elements jssOnPage = document.select("script");
				Elements csssOnPage = document.select("link");
				System.out.println("----> Images:");
				for (Element img : imagesOnPage) {
					System.out.println(img);
					images.add(img.toString());				
					results.add(img.toString());
				}		
				System.out.println("----> JavaScripts:");
				for (Element js : jssOnPage) {
					System.out.println(js);
					images.add(js.toString());				
					results.add(js.toString());
				}	
				System.out.println("----> CSSs:");
				for (Element css : csssOnPage) {
					System.out.println(css);
					images.add(css.toString());				
					results.add(css.toString());
				}	
				for (Element page : linksOnPage) {
					results.addAll(getPageLinks(page.attr("abs:href")));
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
		return results;
	}
}
