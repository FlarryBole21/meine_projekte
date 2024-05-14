package immaterial.oracle;

import java.util.Scanner;

public class InputOracle extends Oracle{
	
	private static final long serialVersionUID = 1L;

	public InputOracle() {
		super.setType("Orakel der Inputs");
	}
	
	//Prüft ob eine Eingabe leer ist
	public String inputEmptyCheck(Scanner scanner) {
		String input;
		while(true) {
			input = scanner.nextLine();
			if(!input.isEmpty()) {
				break;
			}else {
				System.err.println("Error --> Keine Eingabe!");
			}
		}
		return input;
	}
	
	//Prüft ob eine Eingabe innerhalb eines Bereichs ist
	public boolean inputIsNumberAndRangeCheck(int lowNumber, int highNumber, String input){
		int numberInput;
		try {
			numberInput = Integer.valueOf(input);
		}catch(NumberFormatException e) {
			System.err.println("NumberFormatException --> " + e.getMessage() + "!");
			return false;
		}
	
		if (numberInput >= lowNumber && numberInput <= highNumber) {
			return true;
		} else {
			System.err.println("Error --> Zahl ist zu hoch oder zu niedrig!");
			return false;
		}
		
	}
	
	//"Leert die Konsole"
	public void consoleClear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
	}
	
	
	//Beendet das Programm
	//Bei Fehler / Exception oder wenn der Spieler stirbt
	public void printEndProgram(){
		printBreakLineMultiple();
	    System.out.println("Programm wird beendet!");
	    printBreakLineMultiple();
	    System.exit(0);
	}
	
	
	public void printBreakLine() {
		System.out.println("--------------------------------------------------------------------------------------");
	}
	
	public void printBreakLineBefore() {
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println();
	}
	
	public void printBreakLineAfter() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------");
	}
	
	public void printBreakLineMultiple() {
		printBreakLineAfter();
		printBreakLineBefore();
	}


}
