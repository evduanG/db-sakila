package assig3;

import java.util.Date;

public class Global {
	public static final String SPECE = " ";
	public static final String EMPTY = "";
	public static final int ADD_YEAR_TO_GET_REAL_YEAR = 1900; 
	public static final int MIN_RELEASE_YEAR = ADD_YEAR_TO_GET_REAL_YEAR + 70;
	
	public static boolean isNotEmpty(String...i_Str) {
		boolean isEmpty = true;
		
		for (String strings : i_Str) {
			isEmpty = strings == EMPTY || strings == null || strings ==SPECE ; 
			
			if(isEmpty) {
				break;
			}
		}
		
		return !isEmpty;
	}

	public static boolean isValidYear(int i_ReleaseYear) {
		return i_ReleaseYear >= MIN_RELEASE_YEAR && i_ReleaseYear <=  getCurrentYear();
	}
	
	public static int getCurrentYear() {
		return (new Date()).getYear() +  ADD_YEAR_TO_GET_REAL_YEAR;
	}

	public static String toCapitalized(String i_Str) {
		String first = i_Str.substring(0,1).toUpperCase();
		String all = i_Str.substring(1).toLowerCase();
		
		return first +all;
	}

	public static boolean isValidQuery(String query) {
		return true;
	}

}

