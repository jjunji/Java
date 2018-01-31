import java.io.*;
import java.util.*;

public class PropertiesEx2 {
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		prop.setProperty("timeout", "30");
		prop.setProperty("language", "ÇÑ±Û");
		prop.setProperty("size", "10");
		prop.setProperty("capacity", "10");
		
		try {
			prop.store(new FileOutputStream("output.txt"), "Properties Example");
			
			prop.storeToXML(new FileOutputStream("output.xml"), "Properties Example");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
