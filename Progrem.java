package assig3;
import java.util.EventObject;

import assig3.ChoiceState.*;
import assig3.SQL.JDBC;
import assig3.UI.UI;

public class Progrem implements FinishListener{
	private String m_Phat;
	private JDBC m_Db;
	private boolean m_IsRuninng;
	
	public Progrem() {
		m_Phat = UI.getPhat();
		m_Db = new JDBC();
		boolean isConnect = false;
		
		try {
			m_Db.connect(m_Phat);
			isConnect = true;
		}catch (Exception e) {
			System.err.println("ERROR :" +e.getMessage());
		}
		
		if (!isConnect) {
			// do Poxes
			try {
				m_Db.connect(Main.k_Mysql.getURL("sakila"));
				isConnect = true;
			}catch (Exception e) {
				System.err.println("ERROR :" +e.getMessage());
				System.out.println("cont connect ");
				close();
				System.exit(1);
			}
		}

		UserChoiceState.addFinishChoiceListener(this);
		UserChoiceState.setJDBC(m_Db);
		setRuninng(false);
	}

	public void start() {
		setRuninng(m_Db.isConnection());
		run();
		close();
	}

	public void run() {
		while (isRuninng()) {
			(UserChoiceState.creat(UI.getUserChoice())).choiceAction();
		}
	}

	public void close() {
		System.out.println("Bye Bye");
		m_Db.close();	
		UI.close();
		UserChoiceState.close();
	}

	@Override
	public void stopListener(EventObject event) {
		setRuninng(false);
	}

	public boolean isRuninng() {
		return m_IsRuninng && m_Db.isConnection();
	}

	public void setRuninng(boolean m_IsRuninng) {
		this.m_IsRuninng = m_IsRuninng;
	}

}
