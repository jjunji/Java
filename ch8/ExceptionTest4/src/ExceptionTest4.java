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
