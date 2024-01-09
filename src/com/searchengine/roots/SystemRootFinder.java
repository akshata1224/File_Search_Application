package com.searchengine.roots;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SystemRootFinder implements IRootFinder {
	private List<String> allRoots = new ArrayList<String>();
	private int size;

	public SystemRootFinder() {
		File[] roots = File.listRoots();
		for (File f : roots) {
			if (f.canRead()) {
				allRoots.add(f.getAbsolutePath());
			}
			size = allRoots.size();
		}
	}

	@Override
	public List<String> getRoot() {
		return allRoots;
	}

	@Override
	public int getNoOfRoots() {
		// TODO Auto-generated method stub
		return size;
	}
}
