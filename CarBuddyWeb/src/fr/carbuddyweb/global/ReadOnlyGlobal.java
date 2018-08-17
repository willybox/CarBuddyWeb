package fr.carbuddyweb.global;

public class ReadOnlyGlobal {

    /**
     * ==============================
     *          SPECIAL KEYS
     * ==============================
     */
    public static final String ATT_DAO_FACTORY = "daofactory";
    /** Set to true in order to remove all println debug */
    public static final boolean NO_DEBUG = false;
    
    /**
     * ==============================
     *           ATTRIBUTES
     * ==============================
     */
    /** -- GENERAL -- */
	public static final String ID = "id";

    /** -- USER -- */
	public static final String USER_NAME = "username";
	public static final String E_MAIL = "email";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PW = "confirmPW";

    /** -- PERSON -- */
	public static final String NAME = "name";
	public static final String FIRSTNAME = "firstname";
	public static final String SEX = "sex";
	public static final String PHONE = "phone";
	public static final String BIRTHDAY = "birthday";

    /** -- ADDRESS -- */
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	public static final String POSTAL = "postal";
	public static final String STREET = "street";
}
