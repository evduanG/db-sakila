package assig3.ChoiceState;

import assig3.Global;
import assig3.actor.Actor;

public class StateAddActor extends UserChoiceState {
	private Actor m_Actor;
	private String m_SqlVal;
	
	public StateAddActor() {
		Actor m_Actor = new Actor();
		m_SqlVal = null;
	}
	@Override
	public void choiceAction() {
		try {
			boolean isSuccess = false;
			String query = null ;
			
			m_Actor.configFromUser();
			
			if (isDataValid()) {
				query = getSqlInsertQuery();
			}
			
			if(Global.isValidQuery(query)) {
				isSuccess = s_JDbc.doQuery(query, null);
			}
			
			String toShow = isSuccess ? "enter go wall" : "error";
		}catch (Exception e) {
			System.err.println("ERROR :" + e.getMessage());
		}
	}

	@Override
	public boolean setSqlVal() {
		return false;
	}

	@Override
	public String getSqlInsertQuery() {
		return null;
	}

	@Override
	public boolean isDataValid() {
		return Global.isNotEmpty(m_Actor.getFirstName(), m_Actor.getLastName());
	}

	@Override
	public void configFromUser() {
		
	}
	@Override
	public boolean isSuccess() {
		return false;
	}

}
