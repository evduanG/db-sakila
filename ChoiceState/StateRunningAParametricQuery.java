package assig3.ChoiceState;
import assig3.UI.UI;

import assig3.UI.Menu;
import assig3.filmdata.SQLTable;

public class StateRunningAParametricQuery extends UserChoiceState {
	private static Menu m_MenuParametricQuery = new Menu(new String[]
			{"Search for a movie by a word that appears in the name of the movie",
			 "Search all movies in which an actor appeared by the actor's name", 
			 "Search all actors who appeared in movies in a certain language by language",
			 "Searching for a movie that, according to the database, is played by exactly X actors",
			 "Search for an English language movie that did not star Robert De Niro"},
			"Running A Parametric Query" , "?");

	private static final String[] title = {"title"};
	private static final int SEARCH_MOVIE_BY_WORD = 0 ,
			SEARCH_MOVIES_ACTOR_APPEARED =1 ,
			SEARCH_ACTORS_WHO_APPEARED_IN_MOVIES_BY_LANGUAGE = 2,
			SEARCH_MOVIE_THAT_IS_PLAYED_BY_EXACTLY_X_ACTORS =3 , 
			ENGLISH_LANGUAGE_MOVIE_NOT_STAR_ROBERT_DE_NIRO = 4;
	private static final String WORD_IN_MOVIE =  "word_in_movie", FIRST_NAME = "first_name" , LAST_NAME = "last_name", LANGUAGE = "language", X ="X";
			
	private static final String[] s_Query;
	
	static {
		s_Query= new String[5];
		
		s_Query[SEARCH_MOVIE_BY_WORD] = " SELECT `title` FROM `film` WHERE `title` LIKE '%" + SQLTable.toReplaceableVal(WORD_IN_MOVIE) +"%'";
		
		s_Query[SEARCH_MOVIES_ACTOR_APPEARED] = "SELECT `title` FROM `film` WHERE `film_id` IN (SELECT `film_id` FROM `actor` a JOIN `film_actor` lf "
				+ "ON a.actor_id= lf.actor_id WHERE a.first_name = \""+SQLTable.toReplaceableVal(FIRST_NAME)+"\" AND a.last_name=\""
				+ SQLTable.toReplaceableVal(LAST_NAME) +"\")";
		
		s_Query[SEARCH_ACTORS_WHO_APPEARED_IN_MOVIES_BY_LANGUAGE] = "SELECT `first_name`, `last_name` FROM ((`actor` JOIN film_actor )"
				+ " JOIN `language`) WHERE `name` = \"" + SQLTable.toReplaceableVal(LANGUAGE) +"\"";
		
		s_Query[SEARCH_MOVIE_THAT_IS_PLAYED_BY_EXACTLY_X_ACTORS]= "SELECT `title` FROM film f LEFT JOIN (SELECT `film_id`, "
				+ "COUNT(`film_id`) AS `count` FROM `film_actor` GROUP BY `film_id`) c ON f.film_id = c.film_id WHERE c.count ="
				+ SQLTable.toReplaceableVal(X);
		
		s_Query[ENGLISH_LANGUAGE_MOVIE_NOT_STAR_ROBERT_DE_NIRO] = "SELECT f.title FROM `film` f JOIN `language` l "
				+ "ON l.language_id = f.language_id WHERE l.name =\"English\" "
				+ "AND f.film_id NOT IN ( SELECT fa.film_id FROM `film_actor` fa JOIN `actor` a ON a.actor_id =fa.actor_id"
				+ " WHERE `first_name` = \"ROBERT\" AND `last_name` = \"DE_NIRO\")";
	}
	
	private String m_Query;

	@Override
	public void choiceAction() {
		try {
			parrsUi( UI.getParametricQuery(m_MenuParametricQuery));			
		}catch (Exception e) {
			System.err.println("ERROR :" + e.getMessage());
		}
	}

	@Override
	public boolean setSqlVal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSqlInsertQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDataValid() {
		// TODO Auto-generated method stub
		return m_Query != null;
	}

	@Override
	public void configFromUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void parrsUi(String i_Ans) {
		boolean isValid = false; 
		
		while (!isValid) {
			switch (i_Ans) {
			case "1" :
			case "a":
				m_Query = SQLTable.replaceableToVal(s_Query[SEARCH_MOVIE_BY_WORD], WORD_IN_MOVIE, UI.getWord()) ;
				if(isDataValid()) {
					s_JDbc.doQuery(m_Query,  title);					
				}

				isValid = true;
				break;
			case "2" :
			case "b":
				m_Query = SQLTable.replaceableToVal(SQLTable.replaceableToVal( s_Query[SEARCH_MOVIES_ACTOR_APPEARED],
						FIRST_NAME, UI.asqActorFirstName().trim().toUpperCase()
						),LAST_NAME, UI.asqActorLastName().trim().toUpperCase()) ;
				if(isDataValid()) {					
					s_JDbc.doQuery(m_Query, title);
				}

				isValid = true;
				break;
			case "3" :
			case "c":
				m_Query = SQLTable.replaceableToVal(s_Query[SEARCH_ACTORS_WHO_APPEARED_IN_MOVIES_BY_LANGUAGE], LANGUAGE, UI.asqLanguage());
				if(isDataValid()) {					
					s_JDbc.doQuery(m_Query, new String[]{"first_name", "last_name"});
				}

				isValid = true;
				break;
			case "4" :
			case "d":
				m_Query = SQLTable.replaceableToVal(s_Query[SEARCH_MOVIE_THAT_IS_PLAYED_BY_EXACTLY_X_ACTORS], X , UI.xGonGiveItToYa().trim().toUpperCase()); 
				if(isDataValid()) {					
					s_JDbc.doQuery(m_Query, title);
				}

				isValid = true;
				break;
			case "5":
			case "e":
				m_Query = s_Query[ENGLISH_LANGUAGE_MOVIE_NOT_STAR_ROBERT_DE_NIRO];
				if(isDataValid()) {					
					s_JDbc.doQuery(m_Query, title);
				}

				isValid = true;
			default:
				UI.wrongInput();
				i_Ans = UI.getParametricQuery(m_MenuParametricQuery);
			}
			
		}
	}

}
