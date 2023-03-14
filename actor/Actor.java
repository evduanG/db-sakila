package assig3.actor;

import assig3.Global;
import assig3.UI.UI;
import assig3.filmdata.SQLTable;

public class Actor extends SQLTable {
	private static final String[] COL_NAME ;
	private static final int ACTOR_ID = 0,
			FIRST_NAME = 1,
			LAST_NAME = 2,
			LAST_UPDATE = 3;
	private final static String INSERT_INTO_ACTOR, INSERT_INTO_ACTOR_COLUMNS, INSERT_INTO_ACTOR_VALUES;
	
	private String m_FirstName = null;
	private String m_LastName = null;
	
	
	static {
		COL_NAME = new String[4];
		COL_NAME[ACTOR_ID] ="actor_id";
		COL_NAME[FIRST_NAME] = "first_name";
		COL_NAME[LAST_NAME] = "last_name";
		COL_NAME[LAST_UPDATE] = "last_update";
		
		INSERT_INTO_ACTOR_COLUMNS = toColsName(COL_NAME);
		INSERT_INTO_ACTOR_VALUES = replaceableToVal(toReplaceableVal(COL_NAME), COL_NAME[LAST_UPDATE]);
		INSERT_INTO_ACTOR = "INSERT INTO `film` %s VALUES %s";
	}
	
	public Actor() {
		super();
		m_SqlVal = null;
	}
	
	// ==============First-Name=================
	public String getFirstName() {
		return m_FirstName;
	}
	public void setFirstName(String i_FirstName) {
		this.m_FirstName = i_FirstName;
	}
	private void setFirstNameInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[FIRST_NAME], m_FirstName);
	}
	// ==============Last-Name=================
	public String getLastName() {
		return m_LastName;
	}
	public void setLastName(String i_LastName) {
		this.m_LastName = i_LastName;
	}
	private void setLastNameInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[LAST_NAME], m_LastName);
	}
	@Override
	protected boolean setSqlVal() {
		m_SqlVal = INSERT_INTO_ACTOR_VALUES;
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[ACTOR_ID]);
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[LAST_UPDATE]);
		setLastNameInSqlVal();
		setFirstNameInSqlVal();
		
		return true;
	}
	@Override
	public String getSqlInsertQuery() {
		return setSqlVal() ? 
				String.format(INSERT_INTO_ACTOR, INSERT_INTO_ACTOR_COLUMNS, m_SqlVal):
					null;
	}
	@Override
	public boolean isDataValid() {
		return Global.isNotEmpty(m_FirstName) && Global.isNotEmpty(m_LastName);
	}
	
	public void configFromUser() {
		setFirstName(UI.asqActorFirstName());
		setLastName(UI.asqActorLastName());
	}
}
