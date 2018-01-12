public class PolyArgumentTest {
	public static void main(String[] args) {
		Buyer b = new Buyer();
		
		Product t = new Tv();
		
		//b.buy(new Tv());
		b.buy(t);
		b.buy(new Computer());
		
		System.out.println("현재 남은 돈은 " + b.money + "만원입니다.");
		System.out.println(b.bonusPoint);
	}
		
}

class Product{
	int price;
	int bonusPoint;
	
	Product(int price){
		this.price = price;
		this.bonusPoint = (int) (price/10.0);
	}
}

class Tv extends Product{
	Tv(){
		super(100);
	}
	
	public String toString(){
		return "Tv";
	}
}

class Computer extends Product{
	Computer(){
		super(200);
	}
	
	public String toString(){
		return "computer";
	}
}

class Buyer{
	int money = 1000;
	int bonusPoint = 0;
	
	void buy(Product p){
		if(money < p.price){
			System.out.println("잔액이 부족합니다.");
			return;
		}
		
		money -= p.price;
		bonusPoint += p.bonusPoint;
		System.out.println(p+ " 을 구매했습니다.");
	}
}














