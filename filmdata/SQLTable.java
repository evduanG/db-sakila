package assig3.filmdata;

import assig3.Global;

public abstract class SQLTable {
	protected String m_SqlVal;
	
	private static final String DOLLAR = " $%s$ ",
			COL_NAME_FORMYT = "`%s` ,",
			FORMAT_BRACKETS = "( %s )",
			COMMA =", ";

	protected abstract boolean setSqlVal();
	public abstract String getSqlInsertQuery();
	public abstract boolean isDataValid();
	
	public static String toReplaceableVal(String[] i_Colms) {
		String res = Global.EMPTY;
		
		for (String col : i_Colms) {
			res += toReplaceableVal(col) + COMMA  ;
		}
		
		return String.format(FORMAT_BRACKETS, removeLaetCommnt(res)) ;
	}
	protected static String replaceableToVal(String i_StrQ, String i_ReplaceableVal) {
		return replaceableToVal(i_StrQ, i_ReplaceableVal, "NULL");
	}
	
	public static String replaceableToVal(String i_StrQ, String 
			i_ReplaceableVal, String i_VarToEnter) {
		return i_StrQ.replace(toReplaceableVal(i_ReplaceableVal), i_VarToEnter);
	}
	public static String toReplaceableVal(String i_Col) {
		return Global.isNotEmpty(i_Col) ? 
				String.format(DOLLAR, i_Col) : Global.EMPTY;
	}
	
	public static String toColsName(String[] i_Colms) {
		String res = "";
		
		for (String col : i_Colms) {
			res += toColName(col);
		}
		
		return String.format(FORMAT_BRACKETS,  removeLaetCommnt(res)) ;
	}
	
	public static String removeLaetCommnt(String i_Str) {
		return i_Str.substring(0, i_Str.length() - COMMA.length());
	}

	public static String toColName(String i_ColName) {
		return Global.isNotEmpty(i_ColName) ? 
				String.format(COL_NAME_FORMYT, i_ColName) : Global.EMPTY;
	}

}
