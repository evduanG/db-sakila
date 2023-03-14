package assig3.ChoiceState;

import assig3.SQL.JDBC;

public abstract class UserChoiceState implements IQueryMaker {
	
	// CHOICE 
	public static final String CHOICE_ADD_MOVIE = "1",
			CHOICE_ADD_PLAYER = "2", 
			CHOICE_ADD_INFO_ABOUT_ACTOR_WHO_PLAYED_IN_THE_MOVIE = "3",
			PERFORM_USER_QUERIES = "4", 
			CHOICE_RUNNING_A_PARAMETRIC_QUERY = "5", 
			CHOICE_FINISH = "6";
	
	// JDBC
	protected static JDBC s_JDbc;
	
	// Finish-Choice
	public static FinishChoice s_FinishChoice = new FinishChoice();
	
	
	public static void addFinishChoiceListener(FinishListener i_Listener) {
		s_FinishChoice.addListener(i_Listener);
	}
	public static void setJDBC(JDBC i_Connection) {
		s_JDbc = i_Connection;
	}
	
	public abstract void choiceAction();

	public static UserChoiceState creat(String userChoice) {
		switch (userChoice) {
		case CHOICE_ADD_MOVIE:
			return new StateAddMovie();
		case CHOICE_ADD_PLAYER:
			return new StateAddActor();
		case CHOICE_ADD_INFO_ABOUT_ACTOR_WHO_PLAYED_IN_THE_MOVIE:
			return new StateAddInfoAboutActorWhoPlayedInTheMovie();
		case PERFORM_USER_QUERIES:
			return new StatePerformUserQueries();
		case CHOICE_RUNNING_A_PARAMETRIC_QUERY:
			return new StateRunningAParametricQuery();
		case CHOICE_FINISH:
			return s_FinishChoice;
		default:
			return new StateDefault();
		}
	}
	
	public static void close() {
		s_FinishChoice = null;
	}
	
	@Override
	public boolean isDataValid() {
		// default 
		return true;
	}
	
	@Override
	public boolean setSqlVal() {
		// default 
		return false;
	}

	@Override
	public String getSqlInsertQuery() {
		// default 
		return null;
	}
	
	@Override
	public void configFromUser() {
		// default 
	}
	@Override
	public boolean isSuccess() {
		// default 
		return false;
	}
}
