import java.util.Arrays;
import java.util.Scanner;

public class zoo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int testCase = 0;

		while (true) {

			int linesOfInput = Integer.parseInt(sc.nextLine());

			if (linesOfInput == 0)
				break;

			testCase++;
			String[] animals = new String[linesOfInput];
			int[] amount = new int[linesOfInput];
			
			for (int i = 0; i < linesOfInput; i++) { 
				// read input
				String animalName = identifyAnimal(sc.nextLine());
				for (int j = 0; j < animals.length; j++) {
					if(animals[j] == null){
						animals[j] = animalName;
						amount[j]++;
						break;
					}
					else if(animalName.equals(animals[j])){
						amount[j]++;
						break;
					}					
				}
				
			}
			
			for (int i = 0; i < amount.length; i++) {
				if(animals[i] == null)
					break;
				System.out.println(animals[i] + " | " + amount[i]);
			}

		}

		sc.close();

	}
	static String identifyAnimal(String str) {
		return str.substring(str.lastIndexOf(" ") + 1).toLowerCase();
	}
}
