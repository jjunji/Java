import java.util.*;

public class HashMapEx1 {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("myId", "1234");
		map.put("asdf", "1111");
		map.put("asdf", "1234");
		
		Scanner s = new Scanner(System.in);
		
		while(true){
			System.out.println("id & pwd �Է����ּ���.");
			System.out.println("id : ");
			String id = s.nextLine().trim();
			
			System.out.println("pwd : ");
			String pwd = s.nextLine().trim();
			
			if(!map.containsKey(id)){
				System.out.println("id�� �������� �ʽ��ϴ�.");
				
				continue;
			}else{
				if(!(map.get(id)).equals(pwd)){
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				} else{
					System.out.println("��ġ�մϴ�.");
					break;
				}
			}
		}
	}
}
