package assig3.filmdata;

import assig3.Global;
import assig3.UI.UI;

public class Movie extends SQLTable {
	private static final String[] COL_NAME ;

	private static final int TITLE = 0,
			DESCRIPTION = 1,
			RELEASE_YEAR = 2,
			LANGUAGE_ID = 3,
			ORIGINAL_LANGUAGE_ID = 4,
			LENGTH = 5,
			RATING = 6,
			SPECIAL_FEATURES = 7,
			LAST_UPDATE =8;
	
	private static final String INSERT_INTO_FILM_COLUMNS, INSERT_INTO_FILM_VALUES, 
		INSERT_INTO_film, SELECT_LANGUAGE_ID;
	
	static{
		COL_NAME = new String[9];
		
		COL_NAME[TITLE] = "title";
		COL_NAME[DESCRIPTION] = "description";
		COL_NAME[RELEASE_YEAR]= "release_year";
		COL_NAME[ORIGINAL_LANGUAGE_ID] = "original_language_id";
		COL_NAME[LANGUAGE_ID] = "language_id";
		COL_NAME[LENGTH] = "length";
		COL_NAME[RATING] = "rating";
		COL_NAME[SPECIAL_FEATURES] = "special_features";
		COL_NAME[LAST_UPDATE] = "last_update";
		
		INSERT_INTO_FILM_COLUMNS = toColsName(COL_NAME);
		INSERT_INTO_FILM_VALUES = replaceableToVal(toReplaceableVal(COL_NAME), COL_NAME[LAST_UPDATE]);
		SELECT_LANGUAGE_ID = String.format("SELECT `language_id` FROM `language` WHERE name = %s",
				toReplaceableVal(COL_NAME[LANGUAGE_ID]));
		INSERT_INTO_film = "INSERT INTO `film` %s VALUES %s";
	};
	private String m_Tite, m_Description , m_Language , m_OriginalLanguage ;
	private int m_Length = -1 , m_ReleaseYear = -1;
	private ERating m_Rating = null;
	private ESpecialFeatures m_SpecialFeatures= null;

	public Movie() {
		super();
		m_Tite = null;
		m_Description = null;
		m_Language = null;
		m_OriginalLanguage = null;
		m_SqlVal  = null;
	}
	
	public void configFromUser() {
		setTite(UI.asqTitle());
		setDescription(UI.asqDescription());
		setReleaseYear(UI.asqReleaseYear());
		setLanguage(UI.asqLanguage());
		setOriginalLanguage(UI.asqLanguageOriginal());
		setLength(UI.asqLength());
		setRating(UI.asqRating());
		setSpecialFeatures(UI.asqSpecialFeatures());
	}
	
	// ================= Tite ==============================
	
	public String getTite() {
		return m_Tite;
	}
	
	public void setTite(String i_Tite) {
		this.m_Tite = i_Tite;
	}
	
	private void setTiteInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[TITLE], m_Tite);
	}
	
	// ==================Description=====================
	public String getDescription() {
		return m_Description;
	}
	
	public void setDescription(String i_Description) {
		this.m_Description = i_Description;
	}
	
	private void setDescriptionInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[DESCRIPTION], m_Description);
	}
	// ===============Release-Year=====================
	public int getReleaseYear() {
		return m_ReleaseYear;
	}
	
	
	public void setReleaseYear(int i_ReleaseYear) {
		this.m_ReleaseYear = i_ReleaseYear;
	}
	
	public void setReleaseYearInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[RELEASE_YEAR], String.valueOf(m_ReleaseYear));
	}
	// ================Language=========================
	public String getLanguage() {
		return m_Language;
	}
	
	public void setLanguage(String i_Language) {		
		this.m_Language =  Global.isNotEmpty(i_Language) ? Global.toCapitalized(i_Language): null ;
	}
	
	public void setLanguageInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[LANGUAGE_ID], sqlSlecFromLanguage(m_Language));
	}
	
	private String sqlSlecFromLanguage(String i_Language) {
		String s =SELECT_LANGUAGE_ID;
		return String.format("( %s )",replaceableToVal(s, COL_NAME[LANGUAGE_ID], i_Language));
	}
	// ==============Original-Language=================
	public String getOriginalLanguage() {
		return m_OriginalLanguage;
	}
	
	public void setOriginalLanguage(String i_OriginalLanguage) {
		this.m_OriginalLanguage = i_OriginalLanguage;
	}
	public void setOriginalLanguageInSqlVal() {
		String toReplace = Global.isNotEmpty(m_OriginalLanguage) ?
				sqlSlecFromLanguage(m_OriginalLanguage) : "NULL";
		
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[ORIGINAL_LANGUAGE_ID], toReplace);
	}

	
	// =================Length======================
	public int getLength() {
		return m_Length;
	}
	
	public void setLength(int i_Length) {
		this.m_Length = i_Length;
	}
	public void setLengthInSqlVal() {
		m_SqlVal = replaceableToVal(m_SqlVal, COL_NAME[LENGTH], String.valueOf(m_Length));
	}

	// ================Rating=======================
	public ERating getRating() {
		return m_Rating;
	}
	
	public void setRating(ERating i_Rating) {
		this.m_Rating = i_Rating;
	}
	public void setRatingInSqlVal() {
		m_SqlVal = m_Rating != null ? 
				replaceableToVal(m_SqlVal, COL_NAME[RATING], String.valueOf(m_Rating)) :
					replaceableToVal(m_SqlVal, COL_NAME[RATING]);
	}
	
	// ==============Special-Features========================
	public ESpecialFeatures getSpecialFeatures() {
		return m_SpecialFeatures;
	}
	
	public void setSpecialFeatures(ESpecialFeatures i_SpecialFeatures) {
		this.m_SpecialFeatures = i_SpecialFeatures;
	}
	
	public void setSpecialFeaturesInSqlVal() {
		m_SqlVal =  m_SpecialFeatures != null ? 
				replaceableToVal(m_SqlVal, COL_NAME[SPECIAL_FEATURES], String.valueOf(m_SpecialFeatures)):
					replaceableToVal(m_SqlVal, COL_NAME[SPECIAL_FEATURES]);
	}
	// ==============Sql-Val========================
	
	public boolean  setSqlVal() {
		boolean isDataValid = isDataValid();
		
		if (isDataValid) {
			m_SqlVal = INSERT_INTO_FILM_VALUES ;
			
			setTiteInSqlVal();
			setDescriptionInSqlVal();
			setReleaseYearInSqlVal();
			setLanguageInSqlVal();
			setOriginalLanguageInSqlVal();
			setLengthInSqlVal();
			setRatingInSqlVal();
			setSpecialFeaturesInSqlVal();
		}
		
		return isDataValid;
	}

	// ===============================================
	public String getSqlInsertQuery() {
		return setSqlVal() ?
				String.format(INSERT_INTO_film, INSERT_INTO_FILM_COLUMNS, m_SqlVal):
					null;
	}

	// ===============================================
	public boolean isDataValid() {
		return Global.isNotEmpty(m_Tite) && 
				Global.isNotEmpty(m_Description) && 
				Global.isValidYear(m_ReleaseYear); 
	}

}
