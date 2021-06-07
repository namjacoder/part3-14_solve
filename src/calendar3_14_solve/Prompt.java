package calendar3_14_solve;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {

	public void printMenu() {
		System.out.println("+-----------------------+");
		System.out.println("| 1. 일정 등록     ");
		System.out.println("| 2. 일정 검색     ");
		System.out.println("| 3. 달력 보기     ");
		System.out.println("| h. 도움말  q. 종료");
		System.out.println("+-----------------------+");
	}
	
	public int parseDay(String week) {
		if (week.equals("su")) return 0;
		else if (week.equals("mo")) return 0;
		else if (week.equals("tu")) return 0;
		else if (week.equals("we")) return 0;
		else if (week.equals("th")) return 0;
		else if (week.equals("fr")) return 0;
		else if (week.equals("sa")) return 0;
		else
			return 0;
	}
	
	
	public void runPrompt() throws ParseException {
		printMenu();
		
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();
		
		while (true) {
			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = scanner.next();
			
			if (cmd.equals("1")) cmdRegister(scanner, cal);
			else if (cmd.equals("2")) cmdSearch(scanner, cal);
			else if (cmd.equals("3")) cmdCal(scanner, cal);
			else if (cmd.equals("h")) printMenu();
			else if (cmd.equals("q")) break;
		}

		System.out.println("Thank you. Bye~");
		scanner.close();
	}
	
	private void cmdCal(Scanner s, Calendar c) {
		int month = 6;
		int year = 2021;
		System.out.println("년도를 입력하세요.");
		System.out.println("YEAR> ");
		year = s.nextInt();
		
		System.out.println("달을 입력하세요.");
		System.out.println("MONTH> ");
		month = s.nextInt();
		
		if (month > 12 || month < 1) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
		c.printCalendar();
	}
	
	private void cmdSearch(Scanner s, Calendar c) {
		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해 주세요 (yyyy-MM-dd)");
		String date = s.next();
		String plan = "";
		
		try {
			plan = c.searchPlan(date);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("일정 검색 중 오류가 발생했습니다.");
		}
		
		System.out.println(plan);
	}
	
	private void cmdRegister(Scanner s, Calendar c) throws ParseException {
		System.out.println("[새 일정 등록]");
		System.out.println("날짜를 입력해 주세요 (yyyy-MM-dd)");
		String date = s.next();
		System.out.println("일정을 입력해 주세요.");
		String text = s.next();
		c.registerPlan(date, text);
	}
	
	public static void main(String[] args) throws ParseException {
		Prompt p = new Prompt();
		p.runPrompt();
	}
}
