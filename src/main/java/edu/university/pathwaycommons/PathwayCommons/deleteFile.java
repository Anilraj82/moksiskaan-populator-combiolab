package edu.university.pathwaycommons.PathwayCommons;

import java.io.File;

/**
 * This class deletes the downloaded compressed files once the extradtion is completed.
 */

public class deleteFile {
	public void delete() {
		// location of downloaded compressed folder
		File folder = new File(System.getProperty("user.home") + "/eclipse/SeleniumDownloads/");
		for (File file : folder.listFiles()) {
			// deletes the unnecessary and compressed files 
			if (file.getName().endsWith(".gz") || file.getName().endsWith(".part") || file.getName().matches("EXTENDED")
					|| file.getName().matches("Pathway Commons.5.Reactome.BINARY_SIF.tsv.txt")) {
				file.delete();
				System.out.println("Files deleted are : " + file);
			}
		}
	}
}