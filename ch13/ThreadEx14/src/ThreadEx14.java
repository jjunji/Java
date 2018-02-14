import javax.swing.JOptionPane;

public class ThreadEx14 {
	public static void main(String[] args) {
		ThreadEx14_1 th1 = new ThreadEx14_1();
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값 입력 ");
		System.out.println("입력하신 값은 : " + input + "입니다.");
		th1.interrupt();
		System.out.println(th1.isInterrupted());
	}
}

class ThreadEx14_1 extends Thread{
	public void run(){
		int i = 10;
		
		while(i != 0 && !isInterrupted()){
			System.out.println(i--);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				//interrupt();
			}
		}
		
		System.out.println("카운트가 종료 되었습니다.");
	}
}