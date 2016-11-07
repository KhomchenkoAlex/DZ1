/*
Программа проверяет на валидность номера некоторых мобильных операторов Украины.
Проверка осуществляется на наличие букв в номере, длине номера, формата номера и кода операторов.
*/
import java.util.*;
public class DZ1 {   

// Метод принимает строку с номером и удаляет из номера разделители и начальный +, если он есть.
static String formatNumber (String s){
		s = s.replace(" ","");
		s = s.replace("-","");
		s = s.replace("(","");
		s = s.replace(")","");
		s = s.replace("+","");
		return s;
}

// Метод принимает строку с номером и проверяет содержит ли номер не цифры. Если в номере только цифры возвращает True, иначе False.
static boolean hasNoLetter (String s){
	int number_length = s.length();		
	for (int i = 0; i < number_length; i++){
		if (Character.isDigit(s.charAt(i)) == false){
			System.out.println("Number should contain only digits");
			return false;
		}
	}
	return true;
}

// Метод проверяет длину номера и префикс в зависимости от длины.
static boolean hasCorrectLengthAndPrefix (String s){
	boolean flag;
	int number_length = s.length();
	switch ( number_length ) {
		case 10:
			if (s.startsWith("0") && isValidOperatorCode(s,0,3))
				flag = true;
			else{
				System.out.println("Invalid format number or operator code!");
				flag = false;
			}
			break;
		case 11:
			if (s.startsWith("8") && isValidOperatorCode(s,1,4))
				flag = true;
			else{
				System.out.println("Invalid format number or operator code!");
				flag = false;
			}
			break;
		case 12:
			if (s.startsWith("38") && isValidOperatorCode(s,2,5))
				flag = true;
			else{
				System.out.println("Invalid format number or operator code!");
				flag = false;
			}
			break;
		case 14:
			if (s.startsWith("0038") && isValidOperatorCode(s,4,7))
				flag = true;
			else{
				System.out.println("Invalid format number or operator code!");
				flag = false;
			}
			break;
		default:
			if (number_length > 14)
				System.out.println ("Number is too long!");
				else if (number_length < 10)
					System.out.println ("Number is too short!");
				else if (number_length == 13)
					System.out.println ("Incorrect format of number.");
			return flag = false;	
	}
	return flag;
}
// Метод проверяет корректность кода оператора. Коды не входящие в массив operator_code считаются не корректными.
static boolean isValidOperatorCode (String s, int x, int y){
	String operator_code[] = {"067","097","096","098","063","093","050","066","095","099","068","091","092","094"};
	boolean flag = false;	
	String substr = s.substring(x,y);
	for (String n: operator_code){
		if (substr.equals(n)){
			flag = true;
			break;
		}
	}
	if (flag == false){
		System.out.println ("Invalid operator code!");
	}
	return flag;
}

// Метод выполняет суммирование цифр номера. Суммирование происходит до получения однозначного числа.
static int summDigitOfNumber (String s){
	long sum = 0;
	long n = Long.parseLong(s,10);
	int counter  = 0;		
	while (n >= 10) {	
		counter++;
		while (n != 0){       
    	    sum = sum + (n % 10);
    	    n/=10;
		}
		System.out.println(counter+"st round of calculation, sum is: " + sum);
		n = sum;
		sum = 0;
	}
	return (int)n;
}

public static void main(String [] args){
		String cell_Number = "";
		boolean flag = false;
		
		do {
			System.out.println("Please enter your cell phone number:");
			Scanner in = new Scanner(System.in);
			cell_Number = in.nextLine();
			cell_Number = formatNumber(cell_Number);
			if (hasNoLetter(cell_Number) && hasCorrectLengthAndPrefix(cell_Number))
				flag = true;
			else{
				System.out.println("Please try again!");				
				flag = false;
			}	
		}
		while (flag == false);

		int n = summDigitOfNumber (cell_Number);
		
		switch ( n ) {
			
			case 1:
				System.out.println ("One");
				break;
			case 2:
				System.out.println ("Two");
				break;
			case 3:
				System.out.println ("Three");
				break;
			case 4:
				System.out.println ("Four");
				break;
			default:
				System.out.println (n);
		}
	}
}
