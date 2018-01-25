import java.util.*;

public class QueueEx1 {
	static Queue q = new LinkedList();
	static final int MAX_SIZE = 5;
	
	public static void main(String[] args) {
		
		System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");
		
		while(true){
			System.out.println(">>");
			try {
				Scanner s = new Scanner(System.in);
				String input = s.nextLine().trim();
				
				if("".equals(input)) continue;
				
				if(input.equalsIgnoreCase("q")){
					System.exit(0);
				} else if(input.equalsIgnoreCase("help")){
					System.out.println(" help - 도움말 보기 ");
					System.out.println(" q or Q - 종료");
					System.out.println(" history - 최근 입력 명령어");
				} else if(input.equalsIgnoreCase("history")){
					int i = 0;
					
					save(input);
					
					LinkedList tmp = (LinkedList)q;
					ListIterator it = tmp.listIterator();
					
					while(it.hasNext()){
						System.out.println(++i + "." + it.next());
					}
				} else{
					save(input);
					System.out.println(input);
				}

			} catch (Exception e) {
				System.out.println("입력 오류");
			}
		}
	}
	
	public static void save(String input){
		if(!"".equals(input))
			q.offer(input);
		
		if(q.size() > MAX_SIZE)
			q.remove(); // 최대 크기를 넘으면 제일 먼저 입력된 것을 삭제한다.
	}
	
}
