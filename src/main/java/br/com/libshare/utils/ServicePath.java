package br.com.libshare.utils;

public final class ServicePath {

	///////////////////////////////////////////////////////////////
	// ROOT PATH
	///////////////////////////////////////////////////////////////

	public static final String ALL = "/**";

	public static final String ROOT_PATH = "/api";

	public static final String PUBLIC_ROOT_PATH = ROOT_PATH + "/public";

	public static final String PRIVATE_ROOT_PATH = ROOT_PATH + "/private";

	///////////////////////////////////////////////////////////////
	// PRIVATE PATHS
	///////////////////////////////////////////////////////////////

	public static final String PERMISSION_PATH = PRIVATE_ROOT_PATH + "/permission";

	public static final String USER_PATH = PRIVATE_ROOT_PATH + "/user";

	public static final String PROFILE_PATH = PRIVATE_ROOT_PATH + "/profile";

	public static final String BOOK_CASE_PATH = PRIVATE_ROOT_PATH + "/bookcase";

	public static final String SHARING_PORTAL_PATH = PRIVATE_ROOT_PATH + "/sharingportal";

	public static final String SHARING_ITEM_PORTAL_PATH = PRIVATE_ROOT_PATH + "/sharingItempPortal";

	public static final String FAVORITES_PATH = PRIVATE_ROOT_PATH + "/favorites";

	public static final String FRIENDS_PATH = PRIVATE_ROOT_PATH + "/friends";

	///////////////////////////////////////////////////////////////
	// PUBLIC PATHS
	///////////////////////////////////////////////////////////////

	public static final String LOGIN_PATH = PUBLIC_ROOT_PATH + "/login";

	public static final String LOGOUT_PATH = PUBLIC_ROOT_PATH + "/logout";

	public static final String BOOKS_PATH = "/books";		

}
