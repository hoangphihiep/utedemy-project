package vn.iotstar.controller.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

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
import vn.iotstar.utils.CSRFUtil;
import vn.iotstar.utils.Constant;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5,
    maxRequestSize = 1024 * 1024 * 10
)
@WebServlet(urlPatterns = {"/user/registerTeacher","/user/contract","/user/homeRegisterTeacher"})
public class RegisterTeacherController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public IUserService userService = new UserService();
    
    private static final int MAX_TEXT_LENGTH = 1000;
    private static final int MAX_DESCRIPTION_LENGTH = 2000;
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    
    // ✅ REGEX PATTERNS ĐỂ PHÁT HIỆN XSLT/XML INJECTION
    private static final Pattern XSLT_PATTERN = Pattern.compile(
        "(<xsl:|xmlns:|<\\?xml|<!\\[CDATA\\[|<!DOCTYPE|<!ENTITY|system-property|document\\(|eval\\()",
        Pattern.CASE_INSENSITIVE
    );
    
    private static final Pattern XSS_PATTERN = Pattern.compile(
        "(<script|javascript:|onerror=|onload=|<iframe|<embed|<object)",
        Pattern.CASE_INSENSITIVE
    );
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String csrfToken = CSRFUtil.generateToken(req);
        req.setAttribute("csrfToken", csrfToken);
        
        if (url.contains("/user/registerTeacher")) {
            req.getRequestDispatcher("/views/user/teacherRegister1.jsp").forward(req, resp);
        }
        if (url.contains("/user/contract")) {
            req.getRequestDispatcher("/views/user/contract.jsp").forward(req, resp);
        }
        if (url.contains("/user/homeRegisterTeacher")) {
            req.getRequestDispatcher("/views/user/teacherRegister.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        if (url.contains("/user/registerTeacher")) {
            handleRegisterTeacher(req, resp);
        }
    }
    
    private void handleRegisterTeacher(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("account") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
                "Bạn cần đăng nhập để thực hiện chức năng này");
            return;
        }
        
        // ✅ VALIDATE CSRF TOKEN
        if (!CSRFUtil.validateToken(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token không hợp lệ");
            return;
        }
        
        User user = (User) session.getAttribute("account");
        
        try {
            // ✅ LẤY VÀ VALIDATE INPUT
            String taxCode = req.getParameter("taxCode");
            String idCardNumber = req.getParameter("idCardNumber");
            String bankAccount = req.getParameter("bankAccount");
            String description = req.getParameter("description");
            String socialUrl = req.getParameter("socialUrl");
            String issuePlace = req.getParameter("issuePlace");
            String bankName = req.getParameter("bankName");
            String issueDate = req.getParameter("issueDate");
            
            // ✅ KIỂM TRA XSLT/XML INJECTION TRƯỚC KHI SANITIZE
            if (containsXSLTInjection(description) || containsXSLTInjection(socialUrl) ||
                containsXSLTInjection(issuePlace) || containsXSLTInjection(bankName)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                    "Dữ liệu chứa nội dung không hợp lệ (XSLT/XML injection detected)");
                return;
            }
            
            // ✅ KIỂM TRA XSS TRƯỚC KHI SANITIZE
            if (containsXSS(description) || containsXSS(socialUrl) ||
                containsXSS(issuePlace) || containsXSS(bankName)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                    "Dữ liệu chứa nội dung không hợp lệ (XSS detected)");
                return;
            }
            
            // ✅ SANITIZE INPUT
            taxCode = sanitizeInput(taxCode, MAX_TEXT_LENGTH);
            idCardNumber = sanitizeInput(idCardNumber, MAX_TEXT_LENGTH);
            bankAccount = sanitizeInput(bankAccount, MAX_TEXT_LENGTH);
            description = sanitizeInput(description, MAX_DESCRIPTION_LENGTH);
            socialUrl = sanitizeInput(socialUrl, MAX_TEXT_LENGTH);
            issuePlace = sanitizeInput(issuePlace, MAX_TEXT_LENGTH);
            bankName = sanitizeInput(bankName, MAX_TEXT_LENGTH);
            issueDate = sanitizeInput(issueDate, 20);
            
            // ✅ VALIDATE CÁC TRƯỜNG BẮT BUỘC
            if (isNullOrEmpty(taxCode) || isNullOrEmpty(idCardNumber) || 
                isNullOrEmpty(bankAccount) || isNullOrEmpty(description)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                    "Vui lòng điền đầy đủ thông tin bắt buộc");
                return;
            }
            
            // ✅ VALIDATE FORMAT
            if (!isValidTaxCode(taxCode)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã số thuế không hợp lệ");
                return;
            }
            
            if (!isValidIdCard(idCardNumber)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Số CCCD không hợp lệ");
                return;
            }
            
            if (!isValidBankAccount(bankAccount)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Số tài khoản không hợp lệ");
                return;
            }
            
            if (!isNullOrEmpty(socialUrl) && !isValidUrl(socialUrl)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "URL không hợp lệ");
                return;
            }
            
            // ✅ TẠO TEACHER OBJECT
            Teacher teacher = new Teacher();
            teacher.setId(user.getId());
            teacher.setBankAccountNumber(bankAccount);
            teacher.setTaxCode(taxCode);
            teacher.setDescription(description);
            teacher.setSocialUrl(socialUrl);
            teacher.setIdentityCard(idCardNumber);
            
            // ✅ XỬ LÝ UPLOAD FILE
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            
            String frontImageName = processImageUpload(req, "image", uploadPath);
            teacher.setFrontIdentityUrl(frontImageName != null ? frontImageName : "avatar.png");
            
            String backImageName = processImageUpload(req, "backImage", uploadPath);
            teacher.setBackIdentityUrl(backImageName != null ? backImageName : "avatar.png");
            
            // ✅ LƯU VÀO DATABASE
            userService.registerTeacher(user, teacher);
            resp.sendRedirect(req.getContextPath() + "/teacher/course");
            
        } catch (IllegalStateException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, 
                "File upload vượt quá giới hạn cho phép (5MB)");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Đã xảy ra lỗi khi xử lý yêu cầu");
        }
    }
    
    /**
     * ✅ PHÁT HIỆN XSLT/XML INJECTION
     */
    private boolean containsXSLTInjection(String input) {
        if (input == null) {
            return false;
        }
        return XSLT_PATTERN.matcher(input).find();
    }
    
    /**
     * ✅ PHÁT HIỆN XSS
     */
    private boolean containsXSS(String input) {
        if (input == null) {
            return false;
        }
        return XSS_PATTERN.matcher(input).find();
    }
    
    /**
     * ✅ SANITIZE INPUT - LOẠI BỎ CÁC KÝ TỰ NGUY HIỂM
     */
    private String sanitizeInput(String input, int maxLength) {
        if (input == null) {
            return null;
        }
        
        // Trim và truncate
        input = input.trim();
        if (input.length() > maxLength) {
            input = input.substring(0, maxLength);
        }
        
        // ✅ LOẠI BỎ CÁC KÝ TỰ ĐẶC BIỆT (HTML entities)
        input = input.replaceAll("&", "&amp;")
                     .replaceAll("<", "&lt;")
                     .replaceAll(">", "&gt;")
                     .replaceAll("\"", "&quot;")
                     .replaceAll("'", "&#x27;")
                     .replaceAll("/", "&#x2F;");
        
        // ✅ LOẠI BỎ NULL BYTES
        input = input.replaceAll("\0", "");
        
        return input;
    }
    
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * ✅ VALIDATE MÃ SỐ THUẾ (10-14 chữ số)
     */
    private boolean isValidTaxCode(String taxCode) {
        return taxCode != null && taxCode.matches("^[0-9]{10,14}$");
    }
    
    /**
     * ✅ VALIDATE SỐ CCCD (9-12 chữ số)
     */
    private boolean isValidIdCard(String idCard) {
        return idCard != null && idCard.matches("^[0-9]{9,12}$");
    }
    
    /**
     * ✅ VALIDATE SỐ TÀI KHOẢN NGÂN HÀNG
     */
    private boolean isValidBankAccount(String bankAccount) {
        return bankAccount != null && bankAccount.matches("^[0-9]{6,20}$");
    }
    
    /**
     * ✅ VALIDATE URL
     */
    private boolean isValidUrl(String url) {
        return url != null && url.matches("^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$");
    }
    
    /**
     * ✅ XỬ LÝ UPLOAD FILE AN TOÀN
     */
    private String processImageUpload(HttpServletRequest req, String partName, String uploadPath) 
            throws IOException, ServletException {
        try {
            Part imagePart = req.getPart(partName);
            
            if (imagePart == null || imagePart.getSize() == 0) {
                return null;
            }
            
            if (imagePart.getSize() > MAX_FILE_SIZE) {
                throw new IllegalStateException("File " + partName + " quá lớn");
            }
            
            String filename = Paths.get(imagePart.getSubmittedFileName())
                                   .getFileName()
                                   .toString();
            
            if (!isValidImageFile(filename)) {
                throw new IllegalArgumentException("File không hợp lệ: " + filename);
            }
            
            int index = filename.lastIndexOf(".");
            String ext = filename.substring(index + 1).toLowerCase();
            
            if (!isAllowedExtension(ext)) {
                throw new IllegalArgumentException("Định dạng file không được hỗ trợ: " + ext);
            }
            
            // ✅ TẠO TÊN FILE NGẪU NHIÊN ĐỂ TRÁNH PATH TRAVERSAL
            String newFileName = System.currentTimeMillis() + "_" + 
                                (int)(Math.random() * 10000) + "." + ext;
            imagePart.write(uploadPath + "/" + newFileName);
            
            return newFileName;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private boolean isValidImageFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }
        
        return !filename.contains("..") && 
               !filename.contains("/") && 
               !filename.contains("\\") &&
               !filename.contains("\0") &&
               filename.length() < 255;
    }
    
    private boolean isAllowedExtension(String ext) {
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "bmp", "webp"};
        for (String allowed : allowedExtensions) {
            if (allowed.equals(ext)) {
                return true;
            }
        }
        return false;
    }
}