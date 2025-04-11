package vn.iotstar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/image", "/video","/upload"}) // ?fname=abc.png
public class ImageVideoController extends HttpServlet {
	//public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repositoryDoAnCNTT\\uteshop\\src\\main\\webapp\\Images";
	public String UPLOAD_DIRECTORY;
	
	public String UPLOAD_DIRECTORY_USE;
	

	 @Override
	    public void init() throws ServletException {
	        UPLOAD_DIRECTORY = getServletContext().getRealPath("/Images");
	        UPLOAD_DIRECTORY_USE = UPLOAD_DIRECTORY + "/" ;
	    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("image")) {
			String fileName = req.getParameter("fname");
			File file = new File(UPLOAD_DIRECTORY_USE + fileName);
			resp.setContentType("image/jpeg");
			if (file.exists()) {
				IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
			}
		}
		if(url.contains("video"))
		{
			String fileName = req.getParameter("fname");
			File file = new File(UPLOAD_DIRECTORY_USE + fileName);
			resp.setContentType("video/mp4");
			
			if (file.exists()) {

				IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
			}
		}
	}
}
