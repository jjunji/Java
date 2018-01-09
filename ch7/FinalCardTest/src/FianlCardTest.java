class FianlCardTest {

	public static void main(String[] args) {
		Card c = new Card("Heart", 10);
		//c.NUMBER = 5;
		System.out.println(c.KIND);
		System.out.println(c.NUMBER);
	}

}

class Card{
	final int NUMBER;
	final String KIND;
	
	static int width = 100;
	static int height = 250;
	
	Card(String kind, int num){
		KIND = kind;
		NUMBER = num;
	}
	
	Card(){
		this("HEART", 1);
	}
}