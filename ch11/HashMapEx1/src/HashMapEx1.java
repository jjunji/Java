import java.util.*;

public class HashMapEx1 {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("myId", "1234");
		map.put("asdf", "1111");
		map.put("asdf", "1234");
		
		Scanner s = new Scanner(System.in);
		
		while(true){
			System.out.println("id & pwd 입력해주세요.");
			System.out.println("id : ");
			String id = s.nextLine().trim();
			
			System.out.println("pwd : ");
			String pwd = s.nextLine().trim();
			
			if(!map.containsKey(id)){
				System.out.println("id가 존재하지 않습니다.");
				
				continue;
			}else{
				if(!(map.get(id)).equals(pwd)){
					System.out.println("비밀번호가 일치하지 않습니다.");
				} else{
					System.out.println("일치합니다.");
					break;
				}
			}
		}
	}
}
