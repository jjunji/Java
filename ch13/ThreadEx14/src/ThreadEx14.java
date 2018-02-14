import javax.swing.JOptionPane;

public class ThreadEx14 {
	public static void main(String[] args) {
		ThreadEx14_1 th1 = new ThreadEx14_1();
		th1.start();
		
		String input = JOptionPane.showInputDialog("�ƹ� �� �Է� ");
		System.out.println("�Է��Ͻ� ���� : " + input + "�Դϴ�.");
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
		
		System.out.println("ī��Ʈ�� ���� �Ǿ����ϴ�.");
	}
}