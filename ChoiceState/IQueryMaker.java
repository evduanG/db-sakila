package assig3.ChoiceState;

public interface IQueryMaker {
	/**
	 * 
	 * @return
	 */
	public boolean setSqlVal();
	
	/**
	 * 
	 * @return
	 */
	public  String getSqlInsertQuery();
	/**
	 * 
	 * @return
	 */
	public  boolean isDataValid();
	
	public void configFromUser() ;
	
	public boolean isSuccess();
}
