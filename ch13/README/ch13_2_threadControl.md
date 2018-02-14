# **�������� ���� ����**

```
1. �����带 �����ϰ� start()�� ȣ���ϸ� �ٷ� ����Ǵ� ���� �ƴ϶� �����⿭�� ����Ǿ� �ڽ��� ���ʰ� �� ������ ��ٷ��� �Ѵ�.
2. ���� �����¿� �ִٰ� �ڽ��� ���ʰ� �Ǹ� ������°� �ȴ�.
3. �־��� ����ð��� �ٵǰų� yield()�� ������ �ٽ� ��������°� �ǰ� ���� ������ �����尡 ������°� �ȴ�.
4. ������ �Ͻ������ð��� �ٵǰų�, notify(), resume(), interrupt() �� ȣ��Ǹ� �Ͻ����� ���¸� ��� �ٽ� �����⿭�� ����Ǿ� �ڽ��� ���ʸ� ��ٸ��� �ȴ�.
5. ������ ��� ��ġ�ų� stop()�� ȣ��Ǹ� ������� �Ҹ�ȴ�.
```

***

**sleep**

�����ð����� �����带 ���߰� �Ѵ�.

�Ͻ����� ���°� �� ������� ������ �ð��� �� �ǰų� interrupt( )�� ȣ��Ǹ�,
InterruptedException�� �߻��Ǿ� �ῡ�� ��� ������ ���°� �ȴ�.

sleep( )�� ȣ���� ���� �׻� try-catch������ ���ܸ� ó������� �Ѵ�.

```java
public class ThreadEx12 {
	public static void main(String[] args) {
		ThreadEx12_1 th1 = new ThreadEx12_1();
		ThreadEx12_2 th2 = new ThreadEx12_2();
		
		th1.start();
		th2.start();
		
		try {
			th1.sleep(2000);
			//Thread.sleep(2000);
		} catch (Exception e) {
			
		}
		System.out.println("main ����");
	}
}

class ThreadEx12_1 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print("-");
		}
		System.out.println("th1 ����");
	}
}

class ThreadEx12_2 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print("|");
		}
		System.out.println("th2 ����");
	}
}
```
sleep( )�� �׻� ���� ���� ���� �����忡 ���� �۵�.

***

**interrupt( ) & interrupted( )**

�������� �۾��� ����Ѵ�.

```
interrupt()		: �����忡�� �۾��� ���߶�� ��û�Ѵ�.
interrupted()	: �����忡 ���� interrupt()�� ȣ��Ǿ����� �˷��ش�.
ȣ����� �ʾҴٸ� false, ȣ��Ǿ��ٸ� true ��ȯ.
```

```java
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
```
catch �� interrupt( ) �߰��ؾ� ���� �۵���.
-> sleep( ) �� ���� �����尡 ������ �� interrupt( )�� ȣ���ϸ� InterruptedException�� �߻��ǰ�
�������� interrupted ���´� false�� �ڵ� �ʱ�ȭ �ȴ�.

***

**suspend( ) , resume( ) , stop( )**

```
suspend()	: sleepó�� �����带 ���߰� �Ѵ�.
resume()	: suspend�� ���� ������ �����带 ȣ���ϱ� ���� ���.
stop()		: ȣ�� ��� �����尡 ����ȴ�.
```

�������¸� ����Ű�� ���� �ۼ��Ǿ ����� �������� ����.

***

**yield( )**

�ٸ� �����忡�� �纸�Ѵ�.

```java
public class ThreadEx18 {
	public static void main(String[] args) {
		ThreadEx18_1 th1 = new ThreadEx18_1("*");
		ThreadEx18_1 th2 = new ThreadEx18_1("**");
		ThreadEx18_1 th3 = new ThreadEx18_1("***");
	
		th1.start();
		th2.start();
		th3.start();
		
		try {
			Thread.sleep(2000);
			th1.suspend();
			Thread.sleep(2000);
			th2.suspend();
			Thread.sleep(3000);
			th1.resume();
			Thread.sleep(3000);
			th1.stop();
			th2.stop();
			Thread.sleep(2000);
			th3.stop();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

class ThreadEx18_1 implements Runnable{
	boolean suspended = false;
	boolean stopped = false;
	
	Thread th;
	
	ThreadEx18_1(String name){
		th = new Thread(this, name);
	}
	
	public void run(){
		String name = th.getName();
		
		while(!stopped){
			if(!suspended){
				System.out.println(name);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(name + " - interrupted");
				}
			}else{
				Thread.yield();
			}
		}
		System.out.println(name + " - stopped");
	}
	
	public void suspend(){
		suspended = true;
		th.interrupt();
		System.out.println(th.getName() + " - interrupt() by suspend()");
	}
	
	public void resume(){
		suspended = false;
	}
	
	public void stop(){
		stopped = true;
		th.interrupt();
		System.out.println(th.getName() + " - interrupt() by stop()");
	}
	
	public void start(){
		th.start();
	}
}
```
yield( )�� interrupt( )�� ������ ����ϸ�, ���α׷��� ���伺�� ���̰� ���� ȿ������ ������ �����ϰ� �� �� �ִ�.

***

**join**

�ٸ� �������� �۾��� ��ٸ���.

```java
public class ThreadEx19 {
	static long startTime = 0;
	
	public static void main(String[] args) {
		ThreadEx19_1 th1 = new ThreadEx19_1();
		ThreadEx19_2 th2 = new ThreadEx19_2();
		
		th1.start();
		th2.start();
		
		startTime = System.currentTimeMillis();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("�ҿ�ð� : " + (System.currentTimeMillis() - ThreadEx19.startTime));
	}
}

class ThreadEx19_1 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print(new String("-"));
		}
	}
}

class ThreadEx19_2 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print(new String("|"));
		}
	}
}
```

������ �ڽ��� �ϴ� �۾��� ��� ���߰� �ٸ� �����尡 ������ �ð����� �۾��� �����ϵ��� �� �� join( )�� ����Ѵ�.

join( ) �� sleep( ) ó�� interrupt( )�� ���� �����¿��� ��� �� ������, join( )�� ȣ��Ǵ� �κ��� try-catch������ ���ξ� �Ѵ�.

���� �����尡 �ƴ� Ư�� �����忡 ���� �����ϹǷ� static �޼��尡 �ƴ�.