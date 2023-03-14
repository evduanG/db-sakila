
package assig3.ChoiceState;
import assig3.UI.UI;
public class StatePerformUserQueries extends UserChoiceState {

	@Override
	public void choiceAction() {
		try {
			s_JDbc.doQuery(UI.getPerformUserQueries(), new String[] {null});	
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
