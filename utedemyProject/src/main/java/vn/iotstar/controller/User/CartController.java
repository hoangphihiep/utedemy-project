package vn.iotstar.controller.User;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CartService;
import vn.iotstar.service.ICartService;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/cart","/user/addcart","/user/deletecart"})
public class CartController extends HttpServlet{
	
	ICartService cart_service = new CartService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("account");
		int userId = u.getId();
		
		Cart cart = cart_service.findByUserId(userId);
		System.out.println("Testcart"+cart);
		session.setAttribute("cart", cart);
		
		resp.sendRedirect("/utedemyProject/views/user/cartpage.jsp");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
