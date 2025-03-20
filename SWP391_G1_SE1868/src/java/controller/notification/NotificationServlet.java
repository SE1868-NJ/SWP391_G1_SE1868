package controller.notification;



import entity.Customer;
import entity.Notification;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import models.NotificationDAO;


@WebServlet("/notification")
public class NotificationServlet extends HttpServlet {
   
private NotificationDAO notificationDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         // Get session and check for user login
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        // If the customer is not logged in, redirect to the login page
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get customer ID
        int customerId = customer.getCustomerId();

        // Fetch notifications for this customer
        NotificationDAO dao = new NotificationDAO();
        List<Notification> notifications = dao.getNotificationsByCustomerId(customerId);

        // Set the notifications as a request attribute
       request.setAttribute("notifications", notifications);


        // Forward to the JSP page to display the notifications
        RequestDispatcher dispatcher = request.getRequestDispatcher("notification.jsp");
        dispatcher.forward(request, response);
    
    }
    

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle form submission for updating notification status
        int notificationID = Integer.parseInt(request.getParameter("notificationID"));
        String status = request.getParameter("status");

        // Update the notification status
        NotificationDAO dao = new NotificationDAO();
        dao.updateNotificationStatus(notificationID, status);

        // Redirect back to the notification list
        response.sendRedirect("notifications");
    }
    
}