package vn.iotstar.controller.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/teacher/addTarget","/teacher/add","/teacher/adBasicInformation"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/teacher/addTarget")) 
		{
			req.getRequestDispatcher("/views/teacher/addTarget.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/add")) 
		{
			req.getRequestDispatcher("/views/teacher/add.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/adBasicInformation")) 
		{
			req.getRequestDispatcher("/views/teacher/adBasicInformation.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/teacher/addTarget")) 
		{
			// Đọc dữ liệu JSON từ request
	        StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = req.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        
	        String data = buffer.toString();
	        
	        // Parse JSON
	        Gson gson = new Gson();
	        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
	        
	        try {
	            // Xử lý dữ liệu mục tiêu
	            JsonArray objectivesArray = jsonObject.getAsJsonArray("objectives");
	            List<String> objectives = new ArrayList<>();
	            
	            for (JsonElement element : objectivesArray) {
	                objectives.add(element.getAsString());
	                System.out.println ("Mục tiêu: " + element.getAsString());
	            }
	            
	            // Lấy đối tượng và lời chào
	            String targetAudience = jsonObject.get("targetAudience").getAsString();
	            String welcomeMessage = jsonObject.get("welcomeMessage").getAsString();
	            
	            System.out.println ("Đối tượng: " + targetAudience);
	            System.out.println ("Lời chào: " + welcomeMessage);        
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            // Trả về lỗi
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");
	            
	            JsonObject responseObj = new JsonObject();
	            responseObj.addProperty("success", false);
	            responseObj.addProperty("message", "Lỗi: " + e.getMessage());
	            
	            resp.getWriter().write(gson.toJson(responseObj));
	        }
	    }
	}
}
