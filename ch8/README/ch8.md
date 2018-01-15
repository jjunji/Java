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

