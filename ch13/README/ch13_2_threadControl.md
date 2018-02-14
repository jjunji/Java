# **쓰레드의 실행 제어**

```
1. 쓰레드를 생성하고 start()를 호출하면 바로 실행되는 것이 아니라 실행대기열에 저장되어 자신의 차례가 될 때까지 기다려야 한다.
2. 실행 대기상태에 있다가 자신의 차례가 되면 실행상태가 된다.
3. 주어진 실행시간이 다되거나 yield()를 만나면 다시 실행대기상태가 되고 다음 차례의 쓰레드가 실행상태가 된다.
4. 지정된 일시정지시간이 다되거나, notify(), resume(), interrupt() 가 호출되면 일시정지 상태를 벗어나 다시 실행대기열에 저장되어 자신의 차례를 기다리게 된다.
5. 실행을 모두 마치거나 stop()이 호출되면 쓰레드는 소멸된다.
```

***

**sleep**

일정시간동안 쓰레드를 멈추게 한다.

일시정지 상태가 된 쓰레드는 지정된 시간이 다 되거나 interrupt( )가 호출되면,
InterruptedException이 발생되어 잠에서 깨어나 실행대기 상태가 된다.

sleep( )을 호출할 때는 항상 try-catch문으로 예외를 처리해줘야 한다.

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
		System.out.println("main 종료");
	}
}

class ThreadEx12_1 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print("-");
		}
		System.out.println("th1 종료");
	}
}

class ThreadEx12_2 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print("|");
		}
		System.out.println("th2 종료");
	}
}
```
sleep( )은 항상 현재 실행 중인 쓰레드에 대해 작동.

***

**interrupt( ) & interrupted( )**

쓰레드의 작업을 취소한다.

```
interrupt()		: 쓰레드에게 작업을 멈추라고 요청한다.
interrupted()	: 쓰레드에 대해 interrupt()가 호출되었는지 알려준다.
호출되지 않았다면 false, 호출되었다면 true 반환.
```

```java
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
```
catch 에 interrupt( ) 추가해야 정상 작동됨.
-> sleep( ) 에 의해 쓰레드가 멈췄을 때 interrupt( )를 호출하면 InterruptedException이 발생되고
쓰레드의 interrupted 상태는 false로 자동 초기화 된다.

***

**suspend( ) , resume( ) , stop( )**

```
suspend()	: sleep처럼 쓰레드를 멈추게 한다.
resume()	: suspend에 의해 정지된 쓰레드를 호출하기 위해 사용.
stop()		: 호출 즉시 쓰레드가 종료된다.
```

교착상태를 일으키기 쉽게 작성되어서 사용을 권장하지 않음.

***

**yield( )**

다른 쓰레드에게 양보한다.

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
yield( )와 interrupt( )를 적절히 사용하면, 프로그램의 응답성을 높이고 보다 효율적인 실행이 가능하게 할 수 있다.

***

**join**

다른 쓰레드의 작업을 기다린다.

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
		System.out.println("소요시간 : " + (System.currentTimeMillis() - ThreadEx19.startTime));
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

쓰레드 자신이 하던 작업을 잠시 멈추고 다른 쓰레드가 지정된 시간동안 작업을 수행하도록 할 때 join( )을 사용한다.

join( ) 도 sleep( ) 처럼 interrupt( )에 의해 대기상태에서 벗어날 수 있으며, join( )이 호출되는 부분을 try-catch문으로 감싸야 한다.

현재 쓰레드가 아닌 특정 쓰레드에 대해 동작하므로 static 메서드가 아님.