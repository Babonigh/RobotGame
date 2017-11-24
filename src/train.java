import java.util.Scanner;

public class train {
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		int capacity = sc.nextInt();
		int numberOfLines = sc.nextInt();
		
		int onBoard = 0;
		boolean possible = true;
		
		for(int i = 0 ; i < numberOfLines ; i++){
			
			int left = sc.nextInt();
			int enter = sc.nextInt();
			int wait = sc.nextInt();
			
			
			if(onBoard-left < 0) {
				possible = false;
				break;
			}
			onBoard -= left;
			if(onBoard + enter > capacity){
				possible = false;
				break;
			}
			onBoard += enter;
			if((wait != 0) && (onBoard < capacity)){
				possible = false;
				break;
			}
			
			
		}
		
		if(!possible || onBoard != 0){
			System.out.println("impossible");
		}
		else{
			System.out.println("possible");
		}
	}

}
