public class ThreadEx5 {
	static long startTime = 0;
	
	public static void main(String[] args) {
		ThreadEx5_1 t = new ThreadEx5_1();
		t.start();
		startTime = System.currentTimeMillis();
		
		for(int i=0; i<500; i++)
			System.out.printf("%s", new String("-"));
		
		System.out.println("소요시간 : " + (System.currentTimeMillis() - ThreadEx5.startTime));
	}
}

class ThreadEx5_1 extends Thread{
	public void run(){
		for(int i=0; i<500; i++)
			System.out.printf("%s", new String("|"));
		
		System.out.println("소요시간 : " + (System.currentTimeMillis() - ThreadEx5.startTime));
	}
}