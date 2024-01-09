package com.searchengine.model.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.searchengine.roots.IRootFinder;
import com.searchengine.roots.RootFinderFactory;
import com.searchengine.roots.Roots;

public class SearchManager {
    public IRootFinder getRootFinder(Roots root) {
    	return RootFinderFactory.create(root);
    }
    
    public List<String> getRoots(IRootFinder rootFinder){
		return rootFinder.getRoot();
    	
    }
    public Map<String,SearchResult> manageSearch(String fileToSearch,List<String> roots){
    	HashMap<String,SearchResult> map=new HashMap<String, SearchResult>();
    	FileSearcher[] filesearch=new FileSearcher[roots.size()];
    	int count=0;
    	for(String root:roots) {
    		filesearch[count]=new FileSearcher(fileToSearch,root);
    		filesearch[count].start();
    		count++;
    	}
    	for(FileSearcher fileSearcher : filesearch) {
			try {
				fileSearcher.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
    	
    	//after finding Storing into the Map
    	for(FileSearcher searcher:filesearch) {
    		map.put(searcher.getCurrentRootName(), searcher.getSearchResult());
    	}
    	return map;
    }
}
