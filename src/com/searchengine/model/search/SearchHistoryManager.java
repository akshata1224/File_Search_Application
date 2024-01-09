package com.searchengine.model.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchHistoryManager {
	public static boolean isHistoryAvailable(String fileName) {
		File file = new File(fileName+".log");
		return file.exists();
	}
    
	private static String getLog(String fileName) {
		StringBuilder logFile=new StringBuilder();
		logFile.append(fileName);
		logFile.append(".log");
		return logFile.toString();
	}
	
	public static void saveToSearch(List<String> list,String fileName) throws IOException {
		BufferedWriter writer = null;
		try {
			File f=new File(fileName+".log");
			//f.createNewFile();
			writer = new BufferedWriter(new FileWriter(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String str:list) {
			writer.write(str+"\n");
		}
		writer.close();
	}
	public static List<String> getSearchHistory(String fileName) throws IOException  {
		List<String> list=new ArrayList<String>();
		BufferedReader reader=new BufferedReader(new FileReader(getLog(fileName)));
		
		String path="";
		try {
			while((path=reader.readLine())!=null) {
				if(new File(path).exists()) {
					list.add(path);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			reader.close();
		}
		return list;
	}


}
