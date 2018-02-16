import javax.swing.plaf.synth.SynthSeparatorUI;

@FunctionalInterface
interface MyFunction{
	void test();
}

class LambdaEx1 {
	static void execute(MyFunction f){
		f.test();
	}
	
	static MyFunction getMyFunction(){
		MyFunction f = () -> System.out.println("f3.run()");
		return f;
	}
	
	public static void main(String[] args) {
		MyFunction f1 = () -> System.out.println("f1.run()");
		
		MyFunction f2 = new MyFunction(){
			public void test(){
				System.out.println("f2.run()");
			}
		};
		
		MyFunction f3 = getMyFunction();
		
		f1.test();
		f2.test();
		f3.test();
		
		execute(f1);
		execute( () -> System.out.println("run()"));
	}
}
