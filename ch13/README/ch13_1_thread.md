# **������**

### **���μ���&������**

���μ��� : ���� ���� ���α׷�.
���α׷��� �����ϸ� OS�κ��� ���࿡ �ʿ��� �ڿ�(�޸�)�� �Ҵ�޾� ���μ����� �ȴ�.

���μ����� ������, �޸� ���� �ڿ��� ������� ����.

��� ���μ������� �ּ��� �ϳ� �̻��� �����尡 ����.

�ϳ��� ���μ����� ���� �� �ִ� �������� ������ ���ѵǾ� ���� ������ �����尡 �۾��� �����ϴµ� �������� �޸� ����(ȣ�⽺��)�� �ʿ�� �ϱ� ������ ���μ����� �޸� �Ѱ迡 ���� ������ �� �ִ� �������� ���� ������.

**��Ƽ�½�ŷ�� ��Ƽ������**

CPU�� �ھ �� ���� �� �ϳ��� �۾��� ������ �� �����Ƿ�, ������ ���ÿ� ó���Ǵ� �۾��� ������ �ھ��� ������ ��ġ�Ѵ�.
�� �ھ ���� ª�� �ð� ���� ���� �۾��� ������ ���� ���������ν� ���� �۾����� ��� ���ÿ� ����Ǵ� ��ó�� ���̰� �Ѵ�.

***

**�������� ������ ����**

�����ϴ� ����� 2����
1. Thread Ŭ������ ��ӹ޴� ���
2. Runnable�������̽��� �����ϴ� ���

-> ThreadŬ������ ��ӹ����� �ٸ� Ŭ������ ��� ���� ���ϹǷ� �������̽��� �����ϴ� ����� �Ϲ���.

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
Runnable �������̽��� ������ ���,
Runnable �������̽��� ������ Ŭ������ �ν��Ͻ��� ������ ����, �� �ν��Ͻ��� ThreadŬ������ �������� �Ű������� �����ؾ� �Ѵ�.

�������� ��������� OS�� �����췯�� �ۼ��� �����쿡 ���� �����ȴ�.

�� �� ������ ����� ������� �ٽ� ������ �� ����.

***

**start( ) �� run( )**

run( )�� ȣ���ϴ� ���� ������ �����带 ���� ��Ű�� ���� �ƴ϶� �ܼ��� Ŭ������ ����� �޼��带 ȣ���ϴ� ���� ��.

start( )�� ���ο� �����尡 �۾��� �����ϴµ� �ʿ��� ȣ�⽺���� ������ ������ run( )�� ȣ���ؼ�, ������ ȣ�⽺�ÿ� run( )�� ù ��°�� �ð����� �Ѵ�.
��� ������� �������� �۾��� �����ϱ� ���� �ڽŸ��� ȣ�⽺���� �ʿ�� �ϱ� ������,
���ο� �����带 �����ϰ� �����ų ������ ���ο� ȣ�⽺���� �����ǰ� �����尡 ����Ǹ� �۾��� ���� ȣ�⽺���� �Ҹ��.

```
1. main�޼��忡�� �������� start() �� ȣ��
2. start()�� ���ο� �����带 �����ϰ�, �����尡 �۾��ϴµ� ���� ȣ�⽺���� ����.
3. ���� ������ ȣ�� ���ÿ� run()�� ȣ��Ǿ�, �����尡 ������ �������� �۾��� �����Ѵ�.
4. ������ ȣ�⽺���� 2���̹Ƿ� �����ٷ��� ���� ������ ���ؼ� ������ ���鼭 ����ȴ�.
```

***

**�̱۾����� & ��Ƽ������**

�̱� �� ��Ƽ�� ����ð��� ���� ������, �� ���� ������� �۾��� �ð��� �� �� �ɸ���.
������ �����尣�� �۾� ��ȯ�� �ð��� �ɸ��� ����.

```
public class ThreadEx5 {
	static long startTime = 0;
	
	public static void main(String[] args) {
		ThreadEx5_1 t = new ThreadEx5_1();
		t.start();
		startTime = System.currentTimeMillis();
		
		for(int i=0; i<500; i++)
			System.out.printf("%s", new String("-"));
		
		System.out.println("�ҿ�ð� : " + (System.currentTimeMillis() - ThreadEx5.startTime));
	}
}

class ThreadEx5_1 extends Thread{
	public void run(){
		for(int i=0; i<500; i++)
			System.out.printf("%s", new String("|"));
		
		System.out.println("�ҿ�ð� : " + (System.currentTimeMillis() - ThreadEx5.startTime));
	}
}
```

***

**�������� �켱����**

* ������� �켱����(priority)��� �Ӽ��� ������ �ִ�.
* �� �Ӽ� ���� ���� �����尡 ��� ����ð��� �޶�����.
* ������ 1~10 ���ڰ� �������� �켱������ ����
*  ������ 5

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
�����带 �����ϱ� ������ �켱���� ���� ����.

***

**������ �׷�**

���� ���õ� �����带 �׷����� �ٷ�� ���� ��.

�ڹ� ���ø����̼��� ����Ǹ�, JVM�� main�� system�̶�� ������ �׷��� �����
JVM��� �ʿ��� ��������� �����ؼ� �� ������ �׷쿡 ���Խ�Ų��.

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

**���� ������**

* �������� �۾��� ���� ���� ������
* �Ϲ� �����尡 ��� ����Ǹ� ���������� �ڵ� �����
* ���ѷ����� ���ǹ��� �̿��ؼ� ���� �� ����ϰ� �ִٰ� Ư�� ������ �����Ǹ� �۾��� �����ϰ� �ٽ� ����ϵ��� �ۼ�.

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
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
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
		System.out.println("�ڵ� ����Ǿ����ϴ�.");
	}
}
```
setDaemon( ) �� �ݵ�� start( )�� ȣ���ϱ� ���� ����Ǿ�� �Ѵ�.