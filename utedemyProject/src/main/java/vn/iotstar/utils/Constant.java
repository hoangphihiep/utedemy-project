package vn.iotstar.utils;

import jakarta.servlet.ServletContext;

public class Constant {

		public static final String SESSION_USERNAME = "username";
		public static final String COOKIE_REMEMBER = "username";
		
		//public static final String DIR = getServletContext().getRealPath("/Images")+ "/";
	    public static String UPLOAD_DIRECTORY;
	    public static String DIR;
		

	    public static void initUploadDirectory(ServletContext context) {
	        UPLOAD_DIRECTORY = context.getRealPath("/Images") + "/";
	        DIR = UPLOAD_DIRECTORY; // hoặc thêm gì tùy bạn
	    }

}
