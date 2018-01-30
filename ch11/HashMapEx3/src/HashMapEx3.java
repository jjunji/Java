import java.util.*;

public class HashMapEx3 {
	static HashMap phoneBook = new HashMap();
	
	public static void main(String[] args) {
		addPhoneNo("친구", "전지", "0101101");
		addPhoneNo("친구", "자바", "03251101");
		addPhoneNo("친구", "씨", "01046601");
		addPhoneNo("직원", "김나나", "23325");
		addPhoneNo("직원", "김대리", "23411325");
		addPhoneNo("기타", "아무개", "23452345235325");
		addPhoneNo("직원", "밈미밈미", "234235325");
		
		printList();
	}
	
	static void addGroup(String groupName){
		if(!phoneBook.containsKey(groupName)){
			phoneBook.put(groupName, new HashMap());
		}
	}
	
	static void addPhoneNo(String groupName, String name, String tel){
		addGroup(groupName);
		HashMap group = (HashMap) phoneBook.get(groupName);
		group.put(tel, name);
	}
	
	static void printList(){
		Set set = phoneBook.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()){
			Map.Entry e = (Map.Entry) it.next();
			
			Set subSet = ((HashMap)e.getValue()).entrySet();
			Iterator subIt = subSet.iterator();
			
			System.out.println(" * " + e.getKey() + " [" + subSet.size() + "]");
			
			while(subIt.hasNext()){
				Map.Entry subE = (Map.Entry)subIt.next();
				String tel = (String)subE.getKey();
				String name = subE.getValue().toString();
				
				System.out.println(name + " " + tel);
			}
			System.out.println();
		}
	}
}
