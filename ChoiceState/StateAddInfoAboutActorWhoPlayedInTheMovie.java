package assig3.ChoiceState;

import assig3.Global;
import assig3.UI.UI;
import assig3.filmdata.SQLTable;

public class StateAddInfoAboutActorWhoPlayedInTheMovie extends UserChoiceState {
	private static final String  FIRST_NAME = "first_name",
	LAST_NAME = "last_name",
	TITLE = "title";
	private String actorQ = "INSERT INTO `film_actor` (`actor_id`, `film_id`) "
			+ "SELECT `actor_id` , `film_id` FROM "
			+ "`actor` AS a, `film` AS f "
			+ "WHERE a.first_name= '"+ SQLTable.toReplaceableVal(FIRST_NAME) +"' AND a.last_name= '"+  SQLTable.toReplaceableVal(LAST_NAME) 
			+"'  AND f.title =  '"+ SQLTable.toReplaceableVal(TITLE) +"'";
	
	private String m_Query = null;
	@Override
	public void choiceAction() {
		try {
			setSqlVal();
			s_JDbc.doQuery(m_Query, null);
		}catch (Exception e) {
			System.err.println("ERROR :" + e.getMessage());
		}
	}

	@Override
	public boolean setSqlVal() {
		m_Query = SQLTable.replaceableToVal(actorQ, FIRST_NAME, UI.asqActorFirstName()) ;
		m_Query = SQLTable.replaceableToVal(m_Query, LAST_NAME, UI.asqActorLastName()) ;
		m_Query = SQLTable.replaceableToVal(m_Query, TITLE, UI.asqTitle()) ;

		return true;
	}

	@Override
	public String getSqlInsertQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDataValid() {
		// TODO Auto-generated method stub
		return false;
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

}
