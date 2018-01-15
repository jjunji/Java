interface Parseable{
	// ���� �м��۾��� �����Ѵ�.
	public abstract void parse(String fileName);
}

class ParserManager{
	// ����Ÿ���� Parseable �������̽��̴�.
	public static Parseable getParser(String type){
		if(type.equals("XML")){
			return new XMLParser();
		}else{
			Parseable p = new HTMLParser();
			return p;
			// return new HTMLParser();
		}
	}
}

class XMLParser implements Parseable{

	@Override  // �������̵� ����Ű alt + shift + s
	public void parse(String fileName) {
		System.out.println(fileName + "-xml parsing completed.");
	}
}

class HTMLParser implements Parseable{

	@Override
	public void parse(String fileName) {
		System.out.println(fileName + "-html parsing completed.");
	}
	
}

public class ParserTest {
	public static void main(String[] args) {
		Parseable parser = ParserManager.getParser("XML");
		parser.parse("document.xml");
		parser = ParserManager.getParser("HTML");
		parser.parse("document2.html");
	}
}