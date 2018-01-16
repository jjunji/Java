### **예외처리(exception handling)**

**예외처리**

```
컴파일 에러 - 컴파일 시에 발생하는 에러
런타임 에러 - 실행 시에 발생하는 에러
논리적 에러 - 실행은 되지만, 의도와 다르게 동작하는 것
```

```
에러(error) - 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
예외(exception) - 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
```
자바에서는 실행 시 발생할 수 있는 오류를 클래스로 정의했다.

두개의 그룹으로 나눠질 수 있는데,
RuntimeException클래스와 그 자식 클래스들,
Exception클래스와 그 자식 클래스들이다.

***

```
RuntimeException 클래스들 : 프로그래머의 실수로 발생하는 예외
Exception 클래스들 : 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
```
***

**예외 처리하기  |  try-catch문**
```
예외처리

정의 : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것
목적 : 프로그램의 비정상 종료를 막고, 정상적인 실행 상태를 유지하는 것
```
try블럭에서 예외가 발생하면, 예외가 발생한 위치 이후에 있는 try블럭의 문장들은 수행되지 않으므로, try블럭에 포함시킬 코드의 범위를 잘 선택해야 한다.

모든 예외 클래스는 Exception클래스의 자식이므로, catch블럭의 괄호에 Exception클래스 타입의 참조변수를 선언해 놓으면 어떤 종류의 예외가 발생하더라도 이 catch블럭에 의해서 처리된다.

```
printStackTrace() - 예외 발생 당시의 호출 스택에 있었던 메서드의 정보와 예외 메시지를 화면에 출력한다.

getMessage() - 발생한 예외클래스의 인스턴승세 저장된 메시지를 얻을 수 있다.
```

***

**예외 발생시키기**

```
1. 먼저, 연산자 new를 이용해서 발생시키려는 예외 클래스의 객체를 만든 다음
Exception e = new Exception("고의로 발생시킴");

2. 키워드 throw를 이용해서 예외를 발생시킨다.
throw e;
```

```java
public class ExceptionEx2 {
	public static void main(String[] args) {
		try{
			Exception e = new Exception("고의로 발생시킴");
			throw e;
		}catch (Exception e){
			System.out.println("에러 메시지 : " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("프로그램이 정상 종료되었음.");
	}
}

```

***

**메서드에 예외 선언하기**

```
void method() throws Exception1, Exception2, ExceptionN {
	// 메서드의 내용
}
```
메서드에 예외를 선언하려면, 메서드의 선언부에 키워드 throws를 사용해서 메서드 내에서
발생할 수 있는 예외를 적어주기만 하면 된다.

모든 예외의 최고 조상인 Exception 클래스를 메서드에 선언하면, 모든 종류의 예외가 발생할 가능성이 있다는 뜻.

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
결과 값
```
	at ExceptionEx3.method2(ExceptionEx3.java:13)
	at ExceptionEx3.method1(ExceptionEx3.java:9)
	at ExceptionEx3.main(ExceptionEx3.java:5)
```
실행 결과를 보면
```
- 예외가 발생했을 때, 모두 3개의 메서드(main, method1, method2)가 호출스택에 있었으며, 예외가 발생한 곳은 제일 윗줄에 있는 method2()라는 것과
main메서드가 method1()를, 그리고 method1()은 method2()를 호출했다는 것을 알 수 있다.
```
예외가 강제적으로 발생했으나, try-catch문으로 예외처리를 해주지 않았으므로, method2()는 종료되면서 예외를 자신을 호출한 method1( )에게 넘겨준다. method1( ) 에서도 역시 예외처리 없기 때문에 main으로 넘기고 main에서도 없기 때문에 비정상 종료가 발생한다.

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
			System.out.println("method1에서 예외가 처리됨");
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
			System.out.println("main에서 예외가 처리됨");
			e.printStackTrace();
		}
	}
	
	static void method1() throws Exception{
		throw new Exception();
	}
}
```
예외가 발생한 메서드에서 예외를 처리할 수도 있고, 예외가 발생한 메서드를 호출한 메서드에서 처리할 수도 있다. 또는 두 메서드가 예외처리를 분담할 수도 있다.

***

**finally 블럭**

finally블럭은 try-catch문과 함께 예외의 발생여부에 상관없이 실행되어야할 코드를 포함시킬 목적으로 사용된다. try-catch문의 끝에 선택적으로 덧붙여 사용할 수 있으며,
try-catch-finally 순서로 구성된다.

예외가 발생한 경우에는
```
try -> catch -> finally
```
예외가 발생하지 않은 경우에는
```
try -> finally
```

```java
public class FinallyTest3 {
	public static void main(String[] args) {
		method1();
		System.out.println("method1() 의 수행을 마치고 main메서드로 돌아왔습니다.");
	}
	
	static void method1(){
		try {
			System.out.println("method1()이 호출되었습니다.");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("method1()의 finally블럭이 실행되었습니다.");
		}
	}
}
```
결과
```
method1()이 호출되었습니다.
method1()의 finally블럭이 실행되었습니다.
method1()의 수행을 마치고 main메서드로 돌아왔습니다.
```
try블럭에서 return문이 실행되는 경우에도 finally 블럭의 문장들이 먼저 실행된 후에,
현재 실행 중인 메서드를 종료한다.
마찬가지로 catch블럭의 문장 수행 중에 return문을 만나도 finally블럭의 문장들은 수행된다.

***

**자동 자원 반환 - try-with-resources문**

try-with-resources문의 괄호( ) 안에 객체를 생성하는 문장을 넣으면, 이 객체는 따로 close( )를 호출하지 않아도 try 블럭을 벗어나는 순간 자동적으로 close( ) 가 호출된다.
그 다음에 catch블럭 또는 finally블럭이 수행된다.

```java
public interface AutoCloseable{
	void close() throws Exception;
}
```
자동으로 객체의 close( ) 가 호출될 수 있으려면, 클래스가 AutoCloseable이라는 인터페이스를 구현한 것이어야만 한다.

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
		System.out.println("exceptionWork (" + exception + ") 가 호출됨");
		
		if(exception)
			throw new WorkException("WorkException발생!!!");
	}
	
	public void close() throws CloseException{
		System.out.println("close()가 호출됨");a
		throw new CloseException("CloseException 발생");
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
결과
```
exceptionWork (false) 가 호출됨
close()가 호출됨

CloseException: CloseException 발생
exceptionWork (true) 가 호출됨
	at CloseableResource.close(TryWithResourceEx.java:37)
	at TryWithResourceEx.main(TryWithResourceEx.java:7)
close()가 호출됨
WorkException: WorkException발생!!!
	at CloseableResource.exceptionWork(TryWithResourceEx.java:32)
	at TryWithResourceEx.main(TryWithResourceEx.java:16)
	Suppressed: CloseException: CloseException 발생
		at CloseableResource.close(TryWithResourceEx.java:37)
		at TryWithResourceEx.main(TryWithResourceEx.java:17)
```
두 예외가 동시에 발생할 수 없기 때문에, 실제 발생한 예외를 WorkException으로 하고,
CloseException은 억제된 예외로 다룬다. 억제된 예외에 대한 정보는 실제 발생한 예외인 WorkException에 저장된다.

만일 기존의 try-catch문을 사용했다면, 먼저 발생한 WorkException은 무시되고, 마지막으로 발생한 CloseException에 대한 내용만 출력되었을 것이다.

***

**예외 되던지기**

한 메서드에서 발생할 수 있는 예외가 여럿인 경우, 몇개는 try-catch문을 통해서 메서드 내에서 자체적으로 처리하고, 그 나머지는 선언부에 지정하여 호출한 메서드에서 처리하도록 함으로써, 양쪽에서 나눠서 처리되도록 할 수 있다.

주의해야할 점은 예외가 발생할 메서드에서는 try-catch문을 사용해서 예외처리를 해줌과 동시에 메서드의 선언부에 발생할 예외를 throws에 지정해줘야 한다는 것이다.

```java
class ExceptionEx17 {
	public static void main(String[] args) {
		try {
			method1();
		} catch (Exception e) {
			System.out.println("main메서드에서 예외가 처리되었습니다.");
		}
	}
	
	static void method1() throws Exception{
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("method1 에서 예외가 처리되었습니다.");
			throw e;
		}
	}
}
```
결과
```
method1 에서 예외가 처리되었습니다.
main메서드에서 예외가 처리되었습니다.
```
