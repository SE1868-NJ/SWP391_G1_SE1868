
    <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title> Notifications</title>

        <!-- Preload critical assets -->
        <link rel="preload" href="assets/css/notification.css" as="style">
        <link rel="preload" href="assets/js/notification.js" as="script">

 

        <!-- TailwindCSS -->
        <script src="https://cdn.tailwindcss.com"></script>

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    </head>

    <body>
          <c:if test="${empty sessionScope.user}">
        <script>
            alert("Vui lòng đăng nhập để truy cập trang thông báo!");
            window.location.href = "login.jsp"; // Redirect to login page
        </script>
    </c:if>
        <%@include file="header.jsp" %>

        <!-- Notification Section Begin -->
        <section class="notification-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="notification__actions">
                            <button id="markAllAsRead" class="btn btn-primary"><i class="fa fa-check-double"></i> Mark All as Read</button>
                            <div class="notification__filter">
                                <select id="notificationFilter" class="form-control">
                                    <option value="all">All Notifications</option>
                                    <option value="unread">Unread</option>
                                    <option value="read">Read</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="notification__list">
                            <c:choose>
                                <c:when test="${not empty notifications}">
                                    <c:forEach var="notification" items="${notifications}">
                                        <div class="notification__item ${notification.status == 'Unread' ? 'unread' : 'read'}" data-id="${notification.notificationID}">
                                            <div class="notification__image">
                                                <c:choose>
                                                    <c:when test="${not empty notification.imageURL}">
                                                        <img src="${notification.imageURL}" alt="Notification Image">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="notification__icon">
                                                            <i class="fa fa-bell"></i>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="notification__content">
                                                <div class="notification__message">${notification.message}</div>
                                                <div class="notification__time">
                                                    <small>
                                                        <fmt:formatDate value="${notification.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                                                    </small>
                                                </div>
                                            </div>
                                            <div class="notification__actions">
                                                <button class="btn btn-sm mark-as-read" data-id="${notification.notificationID}">
                                                    <i class="fa ${notification.status == 'Unread' ? 'fa-circle' : 'fa-check-circle'}"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="notification__empty">
                                        <div class="notification__empty-icon">
                                            <i class="fa fa-bell-slash"></i>
                                        </div>
                                        <h4>No notifications yet</h4>
                                        <p>You don't have any notifications at the moment.</p>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Notification Section End -->

        <%@include file="footer.jsp" %>
    </body>
</html>
