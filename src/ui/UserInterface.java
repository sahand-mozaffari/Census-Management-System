package ui;

import java.util.Scanner;

public class UserInterface {
	private static String PROMPT = ">> ";

	public static void run() {
		while(true) {
			askOptions("Enter command", new Option[]{Option.GetByKey, Option.SetByKey,Option.Chart, Option.MySort,
					Option.Exit});
		}
	}

	static String askString(String question) {
		Scanner stdin = new Scanner(System.in);
		System.out.println(question);
		return stdin.nextLine();
	}

	static long askLong(String question) {
		while(true) {
			String str = askString(question);
			if(!str.matches("[-+]?\\d+")) {
				System.err.println("Not a valid number!");
				continue;
			}
			return Long.parseLong(str);
		}
	}

	static Option askOptions(String question, Option[] options) {
		String[] opt_str = new String[options.length];
		for(int i = 0; i < options.length; ++i) {
			opt_str[i] = options[i].toString();
		}
		Option opt = options[askStrings(question, opt_str)];
		opt.trigger();
		return opt;
	}
	
	static String getOption(String question){
		Scanner stdin = new Scanner(System.in);
		System.out.println(question + "(0 - > help)");
		return stdin.nextLine();
	}

	static int askStrings(String question, String[] options) {
		while(true) {
			int ind = Integer.parseInt(getOption(question));
			if(ind == 0){
			StringBuilder sb = new StringBuilder(question);
			sb.append("\n");
			for(int i = 0; i < options.length; ++i) {
				sb.append("\t");
				sb.append(i + 1);
				sb.append(". ");
				sb.append(options[i]);
				sb.append("\n");
			}
			sb.append(PROMPT);
		
			ind = (int) askLong(sb.toString());
		
			if(ind != 0 && (ind < 1 || ind > options.length)) {
				System.err.println("Not a valid option!");
				continue;
			}
			}
			return ind - 1;
			
		}
	}

	public static Number askNumber(String question) {
		while(true) {
			String str = askString(question);
			if(!str.matches("[-+]?\\d+(.\\d+)?")) {
				System.err.println("Not a valid number!");
				continue;
			} else if(str.contains("."))
				return Double.parseDouble(str);
			else {
				return Long.parseLong(str);
			}
		}
	}
}

