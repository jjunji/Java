class Person{
	long id;
	
	public boolean equals(Object obj){
		if(obj != null && obj instanceof Person){
			return id == ((Person)obj).id;
		} else{
			return false;
		}
	}
	
	Person(long id){
		this.id = id;
	}
}

public class EqualsEx2 {
	public static void main(String[] args) {
		Person p1 = new Person(920620);
		Person p2 = new Person(920620);
		
		if(p1 == p2){
			System.out.println("p1�� p2�� ���� ����Դϴ�.");
		} else{
			System.out.println("p1�� p2�� �ٸ� ����Դϴ�.");
		}
		
		if(p1.equals(p2)){
			System.out.println("p1�� p2�� ���� ����Դϴ�.");
		} else{
			System.out.println("p1�� p2�� �ٸ� ����Դϴ�.");
		}
	}
}
