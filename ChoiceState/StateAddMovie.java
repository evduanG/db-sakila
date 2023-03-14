package assig3.ChoiceState;
import assig3.Global; 
import assig3.filmdata.Movie;

public class StateAddMovie extends UserChoiceState {
	private Movie m_Movie ;
	
	@Override
	public void choiceAction() {
		boolean isSuccess = false;
		String query = null ;
		setUpData();
		
		if(m_Movie.isDataValid()) {
			query = m_Movie.getSqlInsertQuery();			
		}
		
		if(Global.isValidQuery(query)) {
			isSuccess = s_JDbc.doQuery(query, null);
		}
		
		String toShow = isSuccess ? "enter go wall" : "error";
		
		System.out.println(toShow);
	}

	private void setUpData() {
		m_Movie = new Movie();
		m_Movie.configFromUser();
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
