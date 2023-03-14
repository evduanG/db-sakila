package assig3.SQL;

public class DataBassProvider {
	public final String m_Name;
	public final String m_Host;
	public final int m_Port; 
	private final static String k_FOTMT = "jdbc:%s://%s:%d";

	public DataBassProvider(String i_Name, String i_Host, int i_Port) 
	{
		m_Name = i_Name;
		m_Host = i_Host;
		m_Port = i_Port;
	}

	public String getURL() {
		return String.format(k_FOTMT, m_Name, m_Host, m_Port);
	}
	
	public String getURL(String i_DbName) {
		return String.format("%s/%s",getURL(), i_DbName);
	}
	
}
