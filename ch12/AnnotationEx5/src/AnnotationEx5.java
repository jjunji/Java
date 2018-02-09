import java.lang.annotation.*;

@Deprecated
@SuppressWarnings("1111")
//@TestInfo


public class AnnotationEx5 {
	public static void main(String[] args) {
		
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestInfo{
	int count() default 1;
	String testedBy();
	String[] testTools(); default "JUnit"
	TestType testType() default TestType.FIRST
}

@Retention(RetentionPolicy.RUNTIME)
interface DateTime{
	String yymmdd();
	String hhmmss();
}

enum TestType{ FIRST, FINAL }