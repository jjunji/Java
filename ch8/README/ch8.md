### **����ó��(exception handling)**

**����ó��**

```
������ ���� - ������ �ÿ� �߻��ϴ� ����
��Ÿ�� ���� - ���� �ÿ� �߻��ϴ� ����
���� ���� - ������ ������, �ǵ��� �ٸ��� �����ϴ� ��
```

```
����(error) - ���α׷� �ڵ忡 ���ؼ� ������ �� ���� �ɰ��� ����
����(exception) - ���α׷� �ڵ忡 ���ؼ� ������ �� �ִ� �ټ� �̾��� ����
```
�ڹٿ����� ���� �� �߻��� �� �ִ� ������ Ŭ������ �����ߴ�.

�ΰ��� �׷����� ������ �� �ִµ�,
RuntimeExceptionŬ������ �� �ڽ� Ŭ������,
ExceptionŬ������ �� �ڽ� Ŭ�������̴�.

***

```
RuntimeException Ŭ������ : ���α׷����� �Ǽ��� �߻��ϴ� ����
Exception Ŭ������ : ������� �Ǽ��� ���� ������ ���ο� ���� �߻��ϴ� ����
```
***

**���� ó���ϱ�  |  try-catch��**
```
����ó��

���� : ���α׷� ���� �� �߻��� �� �ִ� ������ �߻��� ����� �ڵ带 �ۼ��ϴ� ��
���� : ���α׷��� ������ ���Ḧ ����, �������� ���� ���¸� �����ϴ� ��
```
try������ ���ܰ� �߻��ϸ�, ���ܰ� �߻��� ��ġ ���Ŀ� �ִ� try���� ������� ������� �����Ƿ�, try���� ���Խ�ų �ڵ��� ������ �� �����ؾ� �Ѵ�.

��� ���� Ŭ������ ExceptionŬ������ �ڽ��̹Ƿ�, catch���� ��ȣ�� ExceptionŬ���� Ÿ���� ���������� ������ ������ � ������ ���ܰ� �߻��ϴ��� �� catch���� ���ؼ� ó���ȴ�.

```
printStackTrace() - ���� �߻� ����� ȣ�� ���ÿ� �־��� �޼����� ������ ���� �޽����� ȭ�鿡 ����Ѵ�.

getMessage() - �߻��� ����Ŭ������ �ν��Ͻ¼� ����� �޽����� ���� �� �ִ�.
```

***

**���� �߻���Ű��**

```
1. ����, ������ new�� �̿��ؼ� �߻���Ű���� ���� Ŭ������ ��ü�� ���� ����
Exception e = new Exception("���Ƿ� �߻���Ŵ");

2. Ű���� throw�� �̿��ؼ� ���ܸ� �߻���Ų��.
throw e;
```

```java
public class ExceptionEx2 {
	public static void main(String[] args) {
		try{
			Exception e = new Exception("���Ƿ� �߻���Ŵ");
			throw e;
		}catch (Exception e){
			System.out.println("���� �޽��� : " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("���α׷��� ���� ����Ǿ���.");
	}
}

```

***

**�޼��忡 ���� �����ϱ�**

```
void method() throws Exception1, Exception2, ExceptionN {
	// �޼����� ����
}
```
�޼��忡 ���ܸ� �����Ϸ���, �޼����� ����ο� Ű���� throws�� ����ؼ� �޼��� ������
�߻��� �� �ִ� ���ܸ� �����ֱ⸸ �ϸ� �ȴ�.

��� ������ �ְ� ������ Exception Ŭ������ �޼��忡 �����ϸ�, ��� ������ ���ܰ� �߻��� ���ɼ��� �ִٴ� ��.

```java
public class ExceptionEx3 {
	public static void main(String[] args) throws Exception{
		method1();
	}
	
	static void method1() throws Exception{
		method2();
	}
	
	static void method2() throws Exception{
		throw new Exception();
	}
}
```
��� ��
```
	at ExceptionEx3.method2(ExceptionEx3.java:13)
	at ExceptionEx3.method1(ExceptionEx3.java:9)
	at ExceptionEx3.main(ExceptionEx3.java:5)
```
���� ����� ����
```
- ���ܰ� �߻����� ��, ��� 3���� �޼���(main, method1, method2)�� ȣ�⽺�ÿ� �־�����, ���ܰ� �߻��� ���� ���� ���ٿ� �ִ� method2()��� �Ͱ�
main�޼��尡 method1()��, �׸��� method1()�� method2()�� ȣ���ߴٴ� ���� �� �� �ִ�.
```
���ܰ� ���������� �߻�������, try-catch������ ����ó���� ������ �ʾ����Ƿ�, method2()�� ����Ǹ鼭 ���ܸ� �ڽ��� ȣ���� method1( )���� �Ѱ��ش�. method1( ) ������ ���� ����ó�� ���� ������ main���� �ѱ�� main������ ���� ������ ������ ���ᰡ �߻��Ѵ�.

***

```java
public class ExceptionTest4 {
	public static void main(String[] args) {
		method1();
	}
	
	static void method1(){
		try{
			throw new Exception();
		}catch(Exception e){
			System.out.println("method1���� ���ܰ� ó����");
			e.printStackTrace();
		}
	}
}

//=================================================================

public class ExceptionTest5 {
	public static void main(String[] args) {
		try {
			method1();
		} catch (Exception e) {
			System.out.println("main���� ���ܰ� ó����");
			e.printStackTrace();
		}
	}
	
	static void method1() throws Exception{
		throw new Exception();
	}
}
```
���ܰ� �߻��� �޼��忡�� ���ܸ� ó���� ���� �ְ�, ���ܰ� �߻��� �޼��带 ȣ���� �޼��忡�� ó���� ���� �ִ�. �Ǵ� �� �޼��尡 ����ó���� �д��� ���� �ִ�.

***

**finally ��**

finally���� try-catch���� �Բ� ������ �߻����ο� ������� ����Ǿ���� �ڵ带 ���Խ�ų �������� ���ȴ�. try-catch���� ���� ���������� ���ٿ� ����� �� ������,
try-catch-finally ������ �����ȴ�.

���ܰ� �߻��� ��쿡��
```
try -> catch -> finally
```
���ܰ� �߻����� ���� ��쿡��
```
try -> finally
```

```java
public class FinallyTest3 {
	public static void main(String[] args) {
		method1();
		System.out.println("method1() �� ������ ��ġ�� main�޼���� ���ƿԽ��ϴ�.");
	}
	
	static void method1(){
		try {
			System.out.println("method1()�� ȣ��Ǿ����ϴ�.");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("method1()�� finally���� ����Ǿ����ϴ�.");
		}
	}
}
```
���
```
method1()�� ȣ��Ǿ����ϴ�.
method1()�� finally���� ����Ǿ����ϴ�.
method1()�� ������ ��ġ�� main�޼���� ���ƿԽ��ϴ�.
```
try������ return���� ����Ǵ� ��쿡�� finally ���� ������� ���� ����� �Ŀ�,
���� ���� ���� �޼��带 �����Ѵ�.
���������� catch���� ���� ���� �߿� return���� ������ finally���� ������� ����ȴ�.

***

**�ڵ� �ڿ� ��ȯ - try-with-resources��**

try-with-resources���� ��ȣ( ) �ȿ� ��ü�� �����ϴ� ������ ������, �� ��ü�� ���� close( )�� ȣ������ �ʾƵ� try ���� ����� ���� �ڵ������� close( ) �� ȣ��ȴ�.
�� ������ catch�� �Ǵ� finally���� ����ȴ�.

```java
public interface AutoCloseable{
	void close() throws Exception;
}
```
�ڵ����� ��ü�� close( ) �� ȣ��� �� ��������, Ŭ������ AutoCloseable�̶�� �������̽��� ������ ���̾�߸� �Ѵ�.

```java
import javax.xml.ws.WebServiceException;

class TryWithResourceEx {
	public static void main(String[] args) {
		try (CloseableResource cr = new CloseableResource()){
			cr.exceptionWork(false);
		} catch (WorkException e) {
			e.printStackTrace();
		} catch (CloseException e){
			e.printStackTrace();
		}
		
		System.out.println();
		
		try (CloseableResource cr = new CloseableResource()){
			cr.exceptionWork(true);
		} catch (WorkException e) {
			e.printStackTrace();
		} catch (CloseException e){
			e.printStackTrace();
		}
	}

}

class CloseableResource implements AutoCloseable{

	public void exceptionWork(boolean exception) throws WorkException{
		System.out.println("exceptionWork (" + exception + ") �� ȣ���");
		
		if(exception)
			throw new WorkException("WorkException�߻�!!!");
	}
	
	public void close() throws CloseException{
		System.out.println("close()�� ȣ���");a
		throw new CloseException("CloseException �߻�");
	}
}

class WorkException extends Exception{
	WorkException(String msg){
		super(msg);
	}
}

class CloseException extends Exception{
	CloseException(String msg){
		super(msg);
	}
}
```
���
```
exceptionWork (false) �� ȣ���
close()�� ȣ���

CloseException: CloseException �߻�
exceptionWork (true) �� ȣ���
	at CloseableResource.close(TryWithResourceEx.java:37)
	at TryWithResourceEx.main(TryWithResourceEx.java:7)
close()�� ȣ���
WorkException: WorkException�߻�!!!
	at CloseableResource.exceptionWork(TryWithResourceEx.java:32)
	at TryWithResourceEx.main(TryWithResourceEx.java:16)
	Suppressed: CloseException: CloseException �߻�
		at CloseableResource.close(TryWithResourceEx.java:37)
		at TryWithResourceEx.main(TryWithResourceEx.java:17)
```
�� ���ܰ� ���ÿ� �߻��� �� ���� ������, ���� �߻��� ���ܸ� WorkException���� �ϰ�,
CloseException�� ������ ���ܷ� �ٷ��. ������ ���ܿ� ���� ������ ���� �߻��� ������ WorkException�� ����ȴ�.

���� ������ try-catch���� ����ߴٸ�, ���� �߻��� WorkException�� ���õǰ�, ���������� �߻��� CloseException�� ���� ���븸 ��µǾ��� ���̴�.

***

**���� �Ǵ�����**

�� �޼��忡�� �߻��� �� �ִ� ���ܰ� ������ ���, ��� try-catch���� ���ؼ� �޼��� ������ ��ü������ ó���ϰ�, �� �������� ����ο� �����Ͽ� ȣ���� �޼��忡�� ó���ϵ��� �����ν�, ���ʿ��� ������ ó���ǵ��� �� �� �ִ�.

�����ؾ��� ���� ���ܰ� �߻��� �޼��忡���� try-catch���� ����ؼ� ����ó���� ���ܰ� ���ÿ� �޼����� ����ο� �߻��� ���ܸ� throws�� ��������� �Ѵٴ� ���̴�.

```java
class ExceptionEx17 {
	public static void main(String[] args) {
		try {
			method1();
		} catch (Exception e) {
			System.out.println("main�޼��忡�� ���ܰ� ó���Ǿ����ϴ�.");
		}
	}
	
	static void method1() throws Exception{
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("method1 ���� ���ܰ� ó���Ǿ����ϴ�.");
			throw e;
		}
	}
}
```
���
```
method1 ���� ���ܰ� ó���Ǿ����ϴ�.
main�޼��忡�� ���ܰ� ó���Ǿ����ϴ�.
```
