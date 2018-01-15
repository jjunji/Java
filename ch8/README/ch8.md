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

