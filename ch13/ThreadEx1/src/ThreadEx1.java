public class ThreadEx1 {
	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
//		Thread2 t3 = new Thread2();
//		t3.run();
		
		Runnable r = new Thread2();
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}

class Thread1 extends Thread{

	@Override
	public void run() {
		for(int i=0; i < 5; i++){
			System.out.println(getName());
		}
	}
	
}

class Thread2 implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<5; i++){
			System.out.println(Thread.currentThread().getName());
		}
	}
}

class test extends Thread{
	void doTest(){
		System.out.println("Test hahaha");
	}
}