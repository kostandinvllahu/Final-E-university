package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {

	public static String charArrayToString(char[] array) {
		String str = "";
		for(char c: array) {
			str += c;
		}
		return str;
	}

	public static String getHiddenText(String text) {
		String hiddenText = "";
		for(int i = 0; i < text.length(); i++) {
			hiddenText += "*";
		}
		return hiddenText;
	}

	public static String dateToString(GregorianCalendar date) {
		if(date == null) {
			return "????/??/??";
		}
		return String.format("%04d", date.get(Calendar.YEAR)) + "/" + 
			   String.format("%02d", date.get(Calendar.MONTH) + 1) + "/" +
			   String.format("%02d", date.get(Calendar.DAY_OF_MONTH));
	}

	public static boolean contains(ArrayList<String[]> emails, String email) {
		for(String[] str : emails) {
			if(str[0].equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	public static void remove(ArrayList<String[]> emails, String email) {
		for(int i = 0; i < emails.size(); i++) {
			if(emails.get(i)[0].equalsIgnoreCase(email)) {
				emails.remove(i);
				break;
			}
		}
	}

	public static int getIndex(ArrayList<String[]> list, String infoAtIndex0) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i)[0].equalsIgnoreCase(infoAtIndex0)) {
				return i;
			}
		}
		return -1;
	}

	public static ArrayList<String> splitList(String text, String c) {
		String[] str = text.split(c + "");
		ArrayList<String> list = new ArrayList<String>();
		for (String s : str) {
			list.add(s.trim().toLowerCase());
		}
		return list;
	}

	public static String dateToString(long dateAndTimeInMilis) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTimeInMillis(dateAndTimeInMilis);
		return dateToString(date);
	}

	public static boolean contains(String[] array, String value) {
		for(String str : array) {
			if(str.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

	public static String listToString(ArrayList<String> list) {
		String result = "";
		for (int i = 0; i < list.size() - 1; i++) {
			result += list.get(i) + ", ";
		}
		if(list.size() != 0) {
			result += list.get(list.size() - 1);
		}
		return result;
	}

}
