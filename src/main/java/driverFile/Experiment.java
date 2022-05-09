package driverFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;


public class Experiment {
	
	private static Properties Prop;
	private static Document ORProperties;
	private static String ORName;
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		 // LocalDateTime CurrentDateTime = LocalDateTime.now();
		  //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  String SystemDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		  //DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("hh_mm_ss a");
		  String SystemTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh_mm_ss a"));
		  System.out.println(SystemDate);
		  System.out.println(SystemTime);
		  Navigate("HomePage");	
		  ObjectValue("SignInButton");
		  Navigate("SigninPage");
		  ObjectValue("Next");
	}

	public static void Navigate(String ObjName) {
		ORName=ObjName;
		try {
			ORProperties=loadObjectRepository(ORName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//menu/mobiletesting
		String Header_Xpath=ORProperties.selectSingleNode("//"+ORName+"/Header"+ORName).getText();
		System.out.println(Header_Xpath);
		//Check(Header_Xpath);
	}
	public static Document loadObjectRepository(String FileName) throws Exception  {
		String FilePath= "./src/main/java/objectRepository/"+FileName+".xml";
		File file = new File(FilePath);
		SAXReader saxReader = new SAXReader();
        Document document= saxReader.read(file);
		return document;
		/*String Header_Xpath=Prop.getProperty("Header"+ORName);
		System.out.println("Header"+ORName);
		System.out.println(Header_Xpath);*/
	}
	public static void ObjectValue(String ObjName) {
		//String ORName=ObjName;
		String Value=ORProperties.selectSingleNode("//"+ORName+"/"+ObjName).getText();
		System.out.println(Value);
		//Check(Header_Xpath);
	}
	public static String[] KeywordSplitter1(Object Keyword) {
		return Keyword.toString().trim().split("-");
	}
	
	public static void KeywordSplitter(Object[] row) {
		//String ReqKeyword= ("ItemAction_ItemName_DummyKeyword_CopiedText");
		int i=0;
		String[] KeywordSplit = KeywordSplitter1(row[i]);
		String ItemAction;
		String ItemName ;
		int ObjectId;
		String CopiedText;
		ItemAction= KeywordSplit[0];
		//System.out.println(ItemAction);
		ItemName= KeywordSplit[1];
		//System.out.println(ItemName);
		if(KeywordSplit[1].contains("#")) {
			String[] ItemNameSplit = KeywordSplit[1].trim().split("#");
			ItemName=ItemNameSplit[0];
			System.out.println(ItemName);
			ObjectId=Integer.parseInt(ItemNameSplit[1]);
			System.out.println(ObjectId);
		}
		else if(KeywordSplit.length==3) {
			ItemAction = KeywordSplit[0];
			ItemName = KeywordSplit[1];
			System.out.println(ItemName);
			CopiedText=KeywordSplit[2];	
			System.out.println(CopiedText);
			
			
		}
		else {
			ItemName= KeywordSplit[1];
			System.out.println(ItemName);
		}
}
}