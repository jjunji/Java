# **쓰레드**

### **프로세스&쓰레드**

프로세스 : 실행 중인 프로그램.
프로그램을 실행하면 OS로부터 실행에 필요한 자원(메모리)을 할당받아 프로세스가 된다.

프로세스는 데이터, 메모리 등의 자원과 쓰레드로 구성.

모든 프로세스에는 최소한 하나 이상의 쓰레드가 존재.

하나의 프로세스가 가질 수 있는 쓰레드의 개수는 제한되어 있지 않으나 쓰레드가 작업을 수행하는데 개별적인 메모리 공간(호출스택)을 필요로 하기 때문에 프로세스의 메모리 한계에 따라 생성할 수 있는 쓰레드의 수가 결정됨.

**멀티태스킹과 멀티쓰레딩**

CPU의 코어가 한 번에 단 하나의 작업만 수행할 수 있으므로, 실제로 동시에 처리되는 작업의 개수는 코어의 개수와 일치한다.
각 코어가 아주 짧은 시간 동안 여러 작업을 번갈아 가며 수행함으로써 여러 작업들이 모두 동시에 수행되는 것처럼 보이게 한다.

***

**쓰레드의 구현과 실행**

구현하는 방법은 2가지
1. Thread 클래스를 상속받는 방법
2. Runnable인터페이스를 구현하는 방법

-> Thread클래스를 상속받으면 다른 클래스를 상속 받지 못하므로 인터페이스를 구현하는 방법이 일반적.

```
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
```
Runnable 인터페이스를 구현한 경우,
Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를 Thread클래스의 생성자의 매개변수로 제공해야 한다.

쓰레드의 실행순서는 OS의 스케쥴러가 작성한 스케쥴에 의해 결정된다.

한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다.

***

**start( ) 와 run( )**

run( )을 호출하는 것은 생성된 쓰레드를 실행 시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것일 뿐.

start( )는 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택을 생성한 다음에 run( )을 호출해서, 생성된 호출스택에 run( )이 첫 번째로 올가가게 한다.
모든 쓰레드는 독립적인 작업을 수행하기 위해 자신만의 호출스택을 필요로 하기 때문에,
새로운 쓰레드를 생성하고 실행시킬 때마다 새로운 호출스택이 생성되고 쓰레드가 종료되면 작업에 사용된 호출스택은 소멸됨.

```
1. main메서드에서 쓰레드의 start() 를 호출
2. start()는 새로운 쓰레드를 생성하고, 쓰레드가 작업하는데 사용될 호출스택을 생성.
3. 새로 생성된 호출 스택에 run()이 호출되어, 쓰레드가 독립된 공간에서 작업을 수행한다.
4. 이제는 호출스택이 2개이므로 스케줄러가 정한 순서에 의해서 번갈아 가면서 실행된다.
```

***

**싱글쓰레드 & 멀티쓰레드**

싱글 과 멀티의 수행시간은 거의 같으나, 두 개의 쓰레드로 작업한 시간이 좀 더 걸린다.
이유는 쓰레드간의 작업 전환에 시간이 걸리기 때문.

```
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
```

***

**쓰레드의 우선순위**

* 쓰레드는 우선순위(priority)라는 속성을 가지고 있다.
* 이 속성 값에 따라 쓰레드가 얻는 실행시간이 달라진다.
* 범위는 1~10 숫자가 높을수록 우선순위가 높다
*  메인은 5

```
public class ThreadEx8 {
	public static void main(String[] args) {
		ThreadEx8_1 t1 = new ThreadEx8_1();
		ThreadEx8_2 t2 = new ThreadEx8_2();
		
		t2.setPriority(7);
		
		System.out.println(t1.getPriority());
		System.out.println(t2.getPriority());
		
		t1.start();
		t2.start();
	}
}

class ThreadEx8_1 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print("-");
			for(int x=0; x<10000000; x++);
		}
	}
}

class ThreadEx8_2 extends Thread{
	public void run(){
		for(int i=0; i<300; i++){
			System.out.print("|");
			for(int x=0; x<10000000; x++);
		}
	}
}
```
쓰레드를 실행하기 전에만 우선순위 변경 가능.

***

**쓰레드 그룹**

서로 관련된 쓰레드를 그룹으로 다루기 위한 것.

자바 어플리케이션이 실행되면, JVM은 main과 system이라는 쓰레드 그룹을 만들고
JVM운영에 필요한 쓰레드들을 생성해서 이 쓰레드 그룹에 포함시킨다.

```
public class ThreadEx9 {
	public static void main(String[] args) {
		ThreadGroup main = Thread.currentThread().getThreadGroup();
		ThreadGroup grp1 = new ThreadGroup("Group1");
		ThreadGroup grp2 = new ThreadGroup("Group2");
		
		ThreadGroup subGrp1 = new ThreadGroup(grp1, "SubGroup1");
		
		grp1.setMaxPriority(3);
		
		Runnable r = new Runnable(){
			public void run(){
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		new Thread(grp1, r, "th1").start();
		new Thread(subGrp1, r, "th2").start();
		new Thread(grp2, r, "th3").start();
		
		System.out.println(">> List of ThreadGroup : " + main.getName()
							+ ", Active ThreadGroup " + main.activeGroupCount()
							+ ", Active Thread : " + main.activeCount() );
		main.list();
	}
}
```

***

**데몬 쓰레드**

* 쓰레드의 작업을 돕는 보조 쓰레드
* 일반 쓰레드가 모두 종료되면 강제적으로 자동 종료됨
* 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성.

```
public class ThreadEx10 implements Runnable{
	static boolean autoSave = false;
	
	public static void main(String[] args) {
		Thread t = new Thread(new ThreadEx10());
		t.setDaemon(true);
		t.start();
		
		for(int i=1; i<=10; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
			System.out.println(i);
			
			if(i==5)
				autoSave = true;
		}
		System.out.println("프로그램이 종료되었습니다.");
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(3 * 1000);
			} catch (Exception e) { }
			
			if(autoSave) {
				autoSave();
			}
		}
	}
	
	public void autoSave(){
		System.out.println("자동 저장되었습니다.");
	}
}
```
setDaemon( ) 은 반드시 start( )를 호출하기 전에 실행되어야 한다.