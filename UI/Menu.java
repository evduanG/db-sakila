package assig3.UI;

public class Menu {
	public final static String LINE_SEPARATOR = "==========================\n",
			TITLE_MENU = "MENU",
			OPTION_1 = "add a movie",
			OPTION_2 = "add player",
			OPTION_3 = "Adding information about an actor who played in the movie",
			OPTION_4 = "perform user queries",
			OPTION_5 = "running a parametric query",
			OPTION_6 = "finish",
			Q_LINE = "Please enter your choice\n",
			FORMT = "%d\t %s\n",
			TITLE_FORMT = LINE_SEPARATOR +"\t\t%s\n"+LINE_SEPARATOR;
	
	public final static String[] MAIN_MENU = { OPTION_1, OPTION_2, OPTION_3, OPTION_4, OPTION_5, OPTION_6};
	
	private String[] m_OptionList;
	private String m_Title;
	private String m_Qline;

	public Menu() {
		this(MAIN_MENU, TITLE_MENU, Q_LINE);
	}

	public Menu(String[] i_OptionList, String i_Title, String i_Qline) {
		setTitle(i_Title);
		setOptionList(i_OptionList);
		m_Qline = i_Qline;
	}

	public String[] getOptionList() {
		return m_OptionList;
	}
	
	public void setOptionList(String[] i_OptionList) {
		m_OptionList = new String[i_OptionList.length];
		
		for (int i = 0; i < i_OptionList.length; i++) {
			m_OptionList[i] = String.format(FORMT, i+1,  i_OptionList[i]) ;
		}
	}
	
	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String i_Title) {
		this.m_Title = String.format(TITLE_FORMT, i_Title);
	}
	
	public void show() {
		System.out.println(m_Title);
		for (int i = 0; i < m_OptionList.length; i++) {
			System.out.print(m_OptionList[i]);
		}
		System.out.println(m_Qline);
	}

}
