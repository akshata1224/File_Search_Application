package com.searchengine.model.search;

import java.util.List;

import com.searchengine.roots.Roots;
import com.searchengine.view.Console;

public class SearchView {
	public int displayRootMenu() {
		
		
		System.out.println("Select your root menu : ");
		System.out.println("1:AllRoots\n2.AnyOneRoot\n3.ActiveRoot");
		return Console.readInt();
	}

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public int selectAnyOneRoot(List<String> roots) {
		int count = 1;
		for (String root : roots) {
			System.out.println(count++ + ": " + root);
		}
		System.out.println("Select Your root: ");
		return Console.readInt();
	}
	public String getFileToSearch() {
		System.out.println("Enter the file name with extension :");
		return Console.readLine();
	}
}
