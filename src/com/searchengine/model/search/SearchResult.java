package com.searchengine.model.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResult {
	private List<String> searchedFile = new ArrayList<String>();
	private int scannedFolder = 1;
	private Map<String, FileSearcher> fileSearchers;

	public SearchResult() {

	}

	public SearchResult(Map<String, FileSearcher> map) {
		this.fileSearchers = map;
	}

	public void addsearchedFile(String File) {
		this.searchedFile.add(File);
	}

	public void scannedFolder() {
		scannedFolder++;
	}

	public List<String> getSearchedFile() {
		return searchedFile;
	}

	public int getScannedFolder() {
		return scannedFolder;
	}

	public List<String> getRootNames(String fileToSearch) {
		List<String> rootNames = new ArrayList<String>();
		for (FileSearcher fileSearcher : fileSearchers.values()) {
			rootNames.add(fileSearcher.getCurrentRootName());
		}
		return rootNames;
	}

	public List<String> getFilesFoundPath(String rootName) {
		return fileSearchers.get(rootName).getFilesFoundPath();
	}

	public List<String> getAllFilesFoundPath(String inputFileName) {
		List<String> roots = getRootNames(inputFileName);
		List<String> allFilesFoundPath = new ArrayList<String>();

		for (String root : roots) {
			allFilesFoundPath.addAll(getFilesFoundPath(root));
		}
		return allFilesFoundPath;
	}

}
