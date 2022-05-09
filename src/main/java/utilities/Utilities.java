package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import driverFile.BaseClass;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.openqa.selenium.Alert;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
//import org.assertj.core.api.SoftAssertions;
//import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

public class Utilities extends BaseClass {

	private static Document ORProperties;
	private static String ORName;
	private static int n = 0;
	private static String ExcelPath = init_prop().getProperty("DataSheetPath");
	private static XLS_Reader reader = new XLS_Reader(ExcelPath);
	private static WebDriver driver;

	public void DatePicker(WebElement element1, WebElement element2, String Day_Month_Year)
			throws InterruptedException {

		/*
		 * if (DateType.equals("ReturnDate")){ System.out.println("ReturnDateSelected");
		 * WebElement element=driver.findElement(By.
		 * xpath("//label[contains(text(),'Return date')]//following::button[1][@class='ui-datepicker-trigger']"
		 * )); element.click(); } else if(DateType.equals("DepartDate")) {
		 * System.out.println("DepartDateSelected"); Thread.sleep(2000); }
		 */
		String[] Value = Day_Month_Year.split("_");
		String Day = Value[0].trim();
		String Month = Value[1].trim();
		String Year = Value[2].trim();
		String MonthYear = element1.getText();// get the value of the current Month Year
		String Month1 = MonthYear.split(" ")[0].trim();
		// System.out.println(Month1);
		String Year1 = MonthYear.split(" ")[1].trim();
		// System.out.println(Year1);
		while (!((Month1.equals(Month)) && (Year1.equals(Year)))) {
			element2.click();// click on Next BUTTON
			MonthYear = element1.getText();
			// System.out.println(MonthYear);
			Month1 = MonthYear.split(" ")[0].trim();
			// System.out.println(Month1);
			Year1 = MonthYear.split(" ")[1].trim();
			System.out.println(Year1);
		}
		driver.findElement(By.xpath("//a[text()='" + Day + "']")).click();

	}

	public static void Select_DepartureCity(String ObjName, int RowNo) throws InterruptedException {
		try {
			String DepartureCity = reader.getCellData("Data", ObjName, RowNo);
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			WebElement element = driver.findElement(By.xpath(xPathValue));
			element.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}

	}

	/**
	 * @param ORName
	 * @param ObjName
	 * @param RowNo
	 */
	public static void Set(String ObjName, int RowNo) {

		try {
			// String ColumnName=ObjName;
			String WebEditData = reader.getCellData("Data", ObjName, RowNo);
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			System.out.println(WebEditData);
			driver.findElement(By.xpath(xPathValue)).clear();
			driver.findElement(By.xpath(xPathValue)).sendKeys(WebEditData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// assertions.assertAll();
			dialogBox(e);
		}

	}

	/**
	 * @param ObjName
	 * @param RowNo
	 * @return void
	 */
	public static void Select(String ObjName, int RowNo) {

		try {
			// String ColumnName = ObjName;
			String Data = reader.getCellData("Data", ObjName, RowNo);
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			driver.findElement(By.xpath(xPathValue)).click();
			Select SELECT = new Select(driver.findElement(By.xpath(xPathValue)));
			SELECT.selectByVisibleText(Data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
			// e.printStackTrace();
		}

	}

	/**
	 * @param ORName
	 * @param ObjName
	 */
	public static void Click(String ObjName) {
		try {
			Check(ObjName);
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			driver.findElement(By.xpath(xPathValue)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
	}

	/**
	 * @param ORName
	 * @param ObjName
	 */
	public static void Check(String ObjName) {
		try {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xPathValue)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
	}

	public static void MouseHover(String ObjName) {
		try {
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(xPathValue))).build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
	}

	public static String SrcScreenshot() {
		// Timestamp instant= Timestamp.from(Instant.now());

		TakesScreenshot src = ((TakesScreenshot) driver);

		LocalDateTime CurrentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_hh.mm.ss a");
		String timeStamp = CurrentDateTime.format(formatter);
		// DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("hh_mm_ss a");
		// String SystemTime = CurrentDateTime.format(formatter1);
		File Srcfile = src.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/target/" + timeStamp + ".png";
		File DestFile = new File(filePath);
		try {
			FileUtils.copyFile(Srcfile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
		return filePath;

	}

	public static void AlertAccept() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
	}

	public static void AlertDismiss() throws InterruptedException {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
	}

	public static void WindowHandle() {
		String MainWindow = driver.getWindowHandle();
		Set<String> handler = driver.getWindowHandles();
		Iterator<String> it = handler.iterator();
		String ChildWindow = it.next();
		while (it.hasNext()) {
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
			}
		}

	}

	public static void FileUpload(String ObjName, String FilePath) {
		try {
			String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
			driver.findElement(By.xpath(xPathValue));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dialogBox(e);
		}
	}

	public static void Wait(int time) {

		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block

		}

		// TODO Auto-generated catch block

	}

	public static void close() {
		driver.quit();
	}

//public static throw Exception;		
	public static void dialogBox(Exception m) {

		JLabel messageLabel = new JLabel("<html><body><p style='width: 300px;'>"
				+ "Click OK button to proceed within 15 Second" + m.toString() + "</p></body></html>");
		Timer timer = new Timer(15000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (n == JOptionPane.CANCEL_OPTION) {
					System.out.println("Clicked On CANCEL Option");
					close();
					// throw new Exception("Invalid browsername, Please check platform.xls");

					// Assert.assertTrue("Step is Failed",false);
				}

				/*
				 * else if(n==JOptionPane.OK_OPTION) {
				 * 
				 * System.out.println("Clicked On OK Option"); }
				 */
				else {

					try {
						SwingUtilities.getWindowAncestor(messageLabel).dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}

					// System.out.println("");
					// Assert.fail("Step is Failed");
					Assert.assertTrue(false);
				}

			}
		});
		timer.setRepeats(false);
		timer.start();
		// JFrame frame = new JFrame();
		System.out.println("executed");
		n = JOptionPane.showConfirmDialog(null, messageLabel, "An Error Occured", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.ERROR_MESSAGE, null);

	}

	public static String Innertext(String ObjName) {
		String xPathValue = ORProperties.selectSingleNode("//" + ORName + "/" + ObjName).getText();
		String Text = driver.findElement(By.xpath(xPathValue)).getText();
		return Text;
	}

	/**
	 * @param ORName
	 * @return void
	 */
	public static void Navigate(String ObjName) {
		ORName = ObjName;
		try {
			ORProperties = loadObjectRepository(ORName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String Header_Xpath = ORProperties.selectSingleNode("//" + ORName + "/Header" + ORName).getText();
		System.out.println(Header_Xpath);
		Check("/Header" + ORName);
	}

	public static void initialization() {
		String browserName = init_prop().getProperty("browser");
		if (browserName.equals("CHROME")) {
			WebDriverManager.chromedriver().setup();

			// System.setProperty("webdriver.chrome.driver","D://Selenium//ChromeDriver//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("window-size=1400,800");
			// options.addArguments("headless");
			/*
			 * DesiredCapabilities capabilities = new DesiredCapabilities();
			 * capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			 * options.merge(capabilities);
			 */
			driver = new ChromeDriver();
		} else if (browserName.equals("EDGE")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		String url = init_prop().getProperty("url");
		driver.get(url);

	}

	/**
	 * @param FileName
	 * @return properties of respected object repository
	 */

	public static Document loadObjectRepository(String FileName) throws Exception {

		String FilePath = init_prop().getProperty("ORPath");
		File file = new File(FilePath + FileName + ".xml");
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(file);
		return document;

	}

	public static void screenshot(Scenario scenario) throws IOException {
		// String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new
		// Date());

		String screenshotName = scenario.getName().replaceAll(" ", "_");
		System.out.println(screenshotName);
		TakesScreenshot src = ((TakesScreenshot) driver);
		final byte[] SourcePath = src.getScreenshotAs(OutputType.BYTES);
		scenario.attach(SourcePath, "image/png", screenshotName);
	}
}