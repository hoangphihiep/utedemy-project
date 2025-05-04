package vn.iotstar.controller.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Teacher;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;
import vn.iotstar.utils.Constant;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/user/registerTeacher","/user/contract","/user/homeRegisterTeacher"})
public class RegisterTeacherController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/user/registerTeacher")) 
		{
			req.getRequestDispatcher("/views/user/teacherRegister1.jsp").forward(req, resp);
		}
		if (url.contains("/user/contract")) 
		{
			req.getRequestDispatcher("/views/user/contract.jsp").forward(req, resp);
		}
		if (url.contains("/user/homeRegisterTeacher")) 
		{
			req.getRequestDispatcher("/views/user/teacherRegister.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if (url.contains("/user/registerTeacher")) 
		{
			User user = (User)session.getAttribute("account");
			req.setAttribute("fullname", user.getFullname());
			String taxCode = req.getParameter("taxCode");
			String idCardNumber = req.getParameter("idCardNumber");
			//String address = req.getParameter("address");
			//String issueDate = req.getParameter("issueDate");
			//String issuePlace = req.getParameter("issuePlace");
			//String bankName = req.getParameter("bankName");
			String bankAccount = req.getParameter("bankAccount");
			String description = req.getParameter("description");
			String socialUrl = req.getParameter("socialUrl");
			
			Teacher teacher = new Teacher();
			teacher.setId(user.getId());
			teacher.setBankAccountNumber(bankAccount);
			teacher.setTaxCode(taxCode);
			teacher.setDescription(description);
			teacher.setSocialUrl(socialUrl);
			teacher.setIdentityCard(idCardNumber);
		
			
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			
			try {
				Part frontImagePart = req.getPart("image");
				if (frontImagePart.getSize() > 0) {
					String filename = Paths.get(frontImagePart.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					System.out.println (filename);
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					frontImagePart.write(uploadPath + "/" + fname);

					teacher.setFrontIdentityUrl(fname);
				} else {
					teacher.setFrontIdentityUrl("avatar.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				Part backImagePart = req.getPart("backImage");
				if (backImagePart.getSize() > 0) {
					String filename = Paths.get(backImagePart.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					System.out.println (filename);
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					backImagePart.write(uploadPath + "/" + fname);

					teacher.setBackIdentityUrl(fname);
				} else {
					teacher.setBackIdentityUrl("avatar.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			userService.registerTeacher(user, teacher);
			resp.sendRedirect(req.getContextPath() + "/teacher/course");
		}
	}

}