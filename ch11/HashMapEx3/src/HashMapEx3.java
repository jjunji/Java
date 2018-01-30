import java.util.*;

public class HashMapEx3 {
	static HashMap phoneBook = new HashMap();
	
	public static void main(String[] args) {
		addPhoneNo("ģ��", "����", "0101101");
		addPhoneNo("ģ��", "�ڹ�", "03251101");
		addPhoneNo("ģ��", "��", "01046601");
		addPhoneNo("����", "�質��", "23325");
		addPhoneNo("����", "��븮", "23411325");
		addPhoneNo("��Ÿ", "�ƹ���", "23452345235325");
		addPhoneNo("����", "�ҹ̹ҹ�", "234235325");
		
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
