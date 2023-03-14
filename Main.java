package assig3;
// Yatsi mattok 209572106
// Eviatar Duany 313167413

import assig3.SQL.DataBassProvider;

public class Main {	
	public static DataBassProvider k_Mysql = new DataBassProvider("mysql", "localhost", 3306);
	
	public static void main(String [] args) {
		(new Progrem()).start();

	}
}
