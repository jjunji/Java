interface Movable{
	void move(int x, int y);
}

interface Attackable{
	void attack(Unit u);
}

interface Fightable extends Movable, Attackable{}

class Fighter extends Unit implements Fightable{
	public void move(int x, int y) { }
	public void attack(Unit u){ }
}

class Unit{
	int curHP;
	int x;
	int y;
}

public class FighterTest {
	public static void main(String[] args) {
		Fighter f = new Fighter();
		
		if(f instanceof Unit){
			System.out.println("f�� ���� Ŭ������ �ڼ��Դϴ�.");
		}
		
		if(f instanceof Fightable){
			System.out.println("f�� �����ͺ� �������̽��� �����߽��ϴ�.");
		}
		
		if(f instanceof Movable){
			System.out.println("f�� ������ �������̽��� �����߽��ϴ�.");
		}
		
		if(f instanceof Attackable){
			System.out.println("f�� ���þ�� �������̽��� �����߽��ϴ�.");
		}
		
		if(f instanceof Object){
			System.out.println("f�� ������Ʈ Ŭ������ �ڼ��Դϴ�.");
		}
		
	}
}

