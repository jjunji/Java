enum Transportation{
	BUS(100){ int fare(int distance) { return distance*BASIC_FARE;}};
	
	final int BASIC_FARE;
	
	Transportation(int basicFare){
		BASIC_FARE = basicFare;
	}
	
	public int getBasicFare(){ return BASIC_FARE; }
	
	abstract int fare(int distance);
}

public class EnumEx3 {
	public static void main(String[] args) {
		System.out.println(Transportation.BUS.fare(100));
	}
}
