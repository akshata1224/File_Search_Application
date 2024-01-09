package com.searchengine.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.searchengine.exception.InvalidRoot;
import com.searchengine.exception.InvalidRootSelection;
import com.searchengine.model.search.SearchHistoryManager;
import com.searchengine.model.search.SearchManager;
import com.searchengine.model.search.SearchResult;
import com.searchengine.model.search.SearchView;
import com.searchengine.roots.IRootFinder;
import com.searchengine.roots.Roots;
import com.searchengine.view.SearchConsoleClient;

//SingletonClass
//this class is responsible for checking the filename in history log or dir and perform the search
public class SearchControl implements ISearchControl {

	private static SearchControl instance = null;
	SearchView view = new SearchView();
	Roots typeOfRoot;
	IRootFinder rootFinder;
	int rootNumber;

	public static SearchControl getInstance() {

		if (instance == null) {
			instance = new SearchControl();
		}
		return instance;
	}

	SearchConsoleClient client = new SearchConsoleClient();

	@Override
	public void performSearch() throws Exception {
		String fileToSearch;
		List<String> listOfRoots = new ArrayList<String>();
		SearchManager searchMgr = new SearchManager();
		typeOfRoot = getRoot();
		rootFinder = searchMgr.getRootFinder(typeOfRoot);
		listOfRoots = rootFinder.getRoot();
		
		if(typeOfRoot.equals(Roots.ANY_ONE_ROOT)) {
			ifAnyOneRoot(listOfRoots);
			System.out.println("You select " + listOfRoots.get(0));
			System.out.println("All root size: " + listOfRoots.size());
		}
		
		
		fileToSearch = view.getFileToSearch();
		if (SearchHistoryManager.isHistoryAvailable(fileToSearch)) {
			System.out.println("History Available");
			List<String> list = SearchHistoryManager.getSearchHistory(fileToSearch);
			client.displaySearchResult(list);
		} else {
			//SearchManager mgr = new SearchManager();
			Map<String, SearchResult> map = searchMgr.manageSearch(fileToSearch, listOfRoots);
			System.out.println("History not available");
			Set<String> keys = map.keySet();
			List<String> pathsToSave = new ArrayList<String>();
			for (String key : keys) {
				SearchResult result = map.get(key);
				List<String> paths = result.getSearchedFile();
				pathsToSave.addAll(paths);
				client.displaySearchResult(key, result);
			}
			SearchHistoryManager.saveToSearch(pathsToSave, fileToSearch);

		}
	}

	public Roots getRoot() throws InvalidRoot {
		rootNumber = view.displayRootMenu();
		Roots rootType;
		switch (rootNumber) {
		case 1: {
			rootType = Roots.ALL_ROOTS;
			break;
		}
		case 2: {
			rootType = Roots.ANY_ONE_ROOT;
			break;
		}
		case 3: {
			rootType = Roots.ACTIVE_ROOTS;
			break;
		}
		default:
			throw new InvalidRoot();

		}
		return rootType;
	}

	public boolean ifAnyOneRoot(List<String> roots) throws InvalidRootSelection {
		rootNumber = view.selectAnyOneRoot(roots);
		if (rootNumber == 1) {
			String root = roots.get(rootNumber - 1);
			return true;
		} else {
			throw new InvalidRootSelection();
		}
	}

}
