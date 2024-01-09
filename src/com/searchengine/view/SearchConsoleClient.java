package com.searchengine.view;

import java.io.FileNotFoundException;
import java.util.List;

import com.searchengine.model.search.SearchResult;



public class SearchConsoleClient {
	//if history is Available
	public void displaySearchResult(List<String> list) {
		for (String path : list) {
			System.out.println(path);
		}
	}
	
	 public void displaySearchResult(String drive,SearchResult result) throws FileNotFoundException {
		   List<String> paths=result.getSearchedFile();
		   System.out.println(drive);
		   System.out.println("Number of folders scanned: "+ result.getScannedFolder());
		   if(paths.size()>=1) {
			   System.out.println("File found in the following location");
			   for(String path: paths) {
				   System.out.println(path);
			   }
		   }
		   else {
			   throw new FileNotFoundException();
		   }
	   }
}
