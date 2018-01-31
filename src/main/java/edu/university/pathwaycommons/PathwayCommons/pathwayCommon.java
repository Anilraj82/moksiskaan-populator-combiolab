package edu.university.pathwaycommons.PathwayCommons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


/**
 * This class automates the process of downloading the links present in the archive of Pathways Commons.
 * The url link looks like  http://www.pathwaycommons.org/archives/PC2/v9/
 * Each url link contains several links to downloadeable files. 
 */

public class pathwayCommon {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.home") + "/eclipse/driversandjars/geckodriver");

		// Create a new instance of the Firefox profile
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", System.getProperty("user.home") + "/eclipse/SeleniumDownloads/");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/x-compressed;" + "application/x-gzip;");
		profile.setPreference("browser.download.manager.showWhenStarting", false);

		// Create a new instance of the Firefox options
		FirefoxOptions option = new FirefoxOptions();
		option.setProfile(profile);

		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver(option);
		driver.manage().window().fullscreen();

		// Array of different url endings
		String[] urls = { "/v9/", "/v8/", "/v7/", "/v6/", "/v5/"};

		// Array of link ending that is needed to populate moksiskaan database
		String[] link = {"hgnc.sif.gz", "BINARY_SIF.hgnc.txt.sif.gz", "BINARY_SIF.hgnc.sif.gz", "BINARY_SIF.tsv.gz", "tsv.gz"};

		for (int i = 0; i < urls.length; i++) {
			// Opening each urls of PathwaysCommons in firefox browser
			driver.get("http://www.pathwaycommons.org/archives/PC2/" + urls[i]);

			// Find the list of text input element by pertial links text
			List<WebElement> links = driver.findElements(By.partialLinkText(link[i]));

			System.out.println("The size(number) of links on this page " + urls[i] + " is " + links.size());
			System.out.println("-------------------------------------------");

			for (int j = 0; j < links.size(); j++) {
				System.out.print("Now its clicking : " + (j + 1) + " ");
				System.out.println(links.get(j).getText());
				// Click to download the file
				links.get(j).click();
			}
			
			// implicitely waits for 10 sec
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

		System.out.println("Thats all to be printed.");

		// Close the browser
		driver.quit();

		// Object to extract compressed file
		extract extractObj = new extract();
		extractObj.extractFile();

		// Object to delete compressed file
		deleteFile deleteObj = new deleteFile();
		deleteObj.delete();

	}
}
