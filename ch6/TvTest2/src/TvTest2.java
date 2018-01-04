
public class TvTest2 {

	public static void main(String[] args) {
		Tv t1 = new Tv();
		Tv t2 = new Tv();
		
		System.out.println("t1의 채널 값은 : " + t1.channel);
		System.out.println("t2의 채널 값은 : " + t2.channel);
		
		t1.channel = 7;
		System.out.println("t1의 채널을 변경하였습니다.");
		
		System.out.println("t1의 채널 값은 : " + t1.channel);
		System.out.println("t2의 채널 값은 : " + t2.channel);
	}
}

class Tv{
	// Tv의 속성(멤버변수)
	String color;
	boolean power;
	int channel;
	
	// Tv의 기능(메서드)
	void power(){ power = !power; }
	void channelUp(){ ++channel; }
	void channelDown(){ --channel; }
}