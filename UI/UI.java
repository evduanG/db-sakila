package assig3.UI;

import java.util.Scanner;

import assig3.Global;
import assig3.filmdata.ERating;
import assig3.filmdata.ESpecialFeatures;

public class UI {
	private final static String EROR_USER_CHOICE_MSG = "eror not a Choice",
			ASQ_TITLE = "what is the name of the movie?",
			ASQ_DESCRIPTION = "What is the description of the movie?",
			ASQ_RELEASE_YEAR = "What year did the movie come out?",
			ASQ_LANGUAGE = "What is the dubbing (spoken language) of the film?",
			ASQ_LANGUAGE_ORIGINAL = "What is the original language of the film?",
			ASQ_LENGTH = "How long is the movie (in minutes)?",
			ASQ_RATING = "What is the rating of the movie?",
			ASQ_SPECIAL_FEATURES = "Is the movie special?",
			ASQ_ACTORS_FIRST_NAME = "What is the actors first name?",
			ASQ_ACTORS_LAST_NAME = "What is the actors last name?",
					ASQ_X=	"is played by exactly X actors what is x?"
;	
	private static Menu s_MainMenu = new Menu(),
			s_RatingMenu = new Menu(new String[]
					{
							"G",
							"PG",
							"PG 13",
							"R",
							"NC 17"
					},
					"Rating",
					"What is the rating of the movie?"),
			s_SpecialFeaturesMenu = new Menu(new String[]
					{
							"Trailers",
							"Commentaries",
							"Deleted Scenes",
							"Behind The Scenes",
							"not have one"
					},
					"Special Features",
					"What Special Features the move have?");

	public static Scanner s_Scanner =  new Scanner(System.in);
	
	public static void close() {
		try {
			s_Scanner.close();			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String getPhat() {
		return UI.userInput("what is the Phat? ");
	}

	public static String getUserChoice() {
		s_MainMenu.show();
		
		return	s_Scanner.nextLine().trim();
	}

	public static String asqTitle() {
		return userInput(ASQ_TITLE);
	}
	
	private static String userInput(String i_Asq) {
		System.out.println(i_Asq);
		return s_Scanner.nextLine();
		// TODO: fix this 
//		String res = null;
//		
//		do {
//			System.out.println(i_Asq);
//			if (s_Scanner.hasNextLine()) {
//				res = s_Scanner.nextLine().trim();	
//				
//				if(!Global.isNotEmpty(res)) {
//					res = null;
//				}
//			}
//			else {
//				wrongInput();
//			}
//			
//		}
//		while (res == null);
//		
//		return res;
	}
	
	public static void wrongInput() {
		cleanBuffer();
		System.out.println("try agine");
	}

	public static void cleanBuffer() {
		if(s_Scanner.hasNext()) {
			s_Scanner.next();			
		}
	}

	public static String asqDescription() {
		return userInput(ASQ_DESCRIPTION);
	}

	public static int asqReleaseYear() {
		return intUserInput(ASQ_RELEASE_YEAR);
	}

	private static int intUserInput(String i_Asq) {
		System.out.println(i_Asq);
		return s_Scanner.nextInt();
		// TODO : fix this 
//		boolean isValid = false;
//		int res = -1;
//		
//		do {
//			System.out.println(i_Asq);
//			
//			if(s_Scanner.hasNextInt()) {
//				res = s_Scanner.nextInt();				
//				isValid = true;				
//			}else {
//				wrongInput();
//			}
//		}
//		while (!isValid);
//		
//		cleanBuffer();
//		return res;
	}

	public static String asqLanguage() {
		return Global.toCapitalized(UI.userInput(ASQ_LANGUAGE));
	}

	public static String asqLanguageOriginal() {
		String ans = null;
		
		if (haveVal("Does the film have an original language?") ) {
			ans = userInput(ASQ_LANGUAGE_ORIGINAL);
		}
		
		return ans;			
	}

	public static int asqLength() {
		return intUserInput(ASQ_LENGTH);
	}

	public static ERating asqRating() {
		if(haveVal("Does the film have an Rating?")) {
			s_RatingMenu.show();
			return EnumPrsss.prsssRating(s_Scanner.next());
		}
		
		return null;
	}

	public static ESpecialFeatures asqSpecialFeatures() {
		if(haveVal("Does the film have an Special-Feature?")) {
			s_SpecialFeaturesMenu.show();
			return EnumPrsss.prsssSpecialFeatures(s_Scanner.next());
		}
		
		return null;
	}

	public static boolean haveVal(String i_Q) {
		String ans = null;
		boolean res = false;
		
		while (true) {
			System.out.println(i_Q);
			
			if(s_Scanner.hasNextLine()) {
				ans = s_Scanner.nextLine();
				
				switch (ans.toLowerCase()) 
				{
				case "true":
				case "yes":
				case "y" :
					return true;
				case "false":
				case "no":
				case "n" :
					return false;
				default:
					break;
				}
			}
		}
		
	}

	public static String asqActorFirstName() {
		return userInput(ASQ_ACTORS_FIRST_NAME);
	}

	public static String asqActorLastName() {
		return userInput(ASQ_ACTORS_LAST_NAME);
	}

	public static String getParametricQuery(Menu show) {
		show.show();
		return UI.userInput("what is your Parametric Query" );
	}

	public static String getWord() {
		
		return UI.userInput("what word to look for "); 
	}
// UI.userInput(
//	public static String getFirstNanme() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public static String xGonGiveItToYa() {
		return UI.userInput(ASQ_X);
	}

	public static String getPerformUserQueries() {
		return userInput("Perform User Queries \n write the Queries");
	}

}
