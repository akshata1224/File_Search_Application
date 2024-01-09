package com.searchengine.model.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher extends Thread {
	private File root;
	private String fileToSearch;
	List<String> filesFoundPath;
	SearchResult result = new SearchResult();
    SearchManager mgr=new SearchManager();
	public FileSearcher() {

	}

	public FileSearcher(String fileToSearch, String rootName) {
		super();
		this.fileToSearch = fileToSearch;
		this.root = new File(rootName);
		this.filesFoundPath = new ArrayList<String>();
	}
	@Override
    public void run(){
    	searchFile(fileToSearch, root);
    }
	public void searchFile(String filename, File root) {
		File[] files = root.listFiles();
		
		if (files!=null) {
			for (File presentFile : files) {
				if (presentFile.isDirectory()) {
                    result.scannedFolder();
					searchFile(filename, presentFile);
				} else if (presentFile.isFile()) {
					if (filename.equalsIgnoreCase(presentFile.getName())) {
                        result.addsearchedFile(presentFile.getAbsolutePath());
						System.out.println(presentFile.getAbsolutePath());
					}
				}
			}
		}
	}

	public String getCurrentRootName() {
		return root.getPath();
	}

	public List<String> getFilesFoundPath() {

		return filesFoundPath;
	}

	public SearchResult getSearchResult() {
		// TODO Auto-generated method stub
		return result;
	}
}
