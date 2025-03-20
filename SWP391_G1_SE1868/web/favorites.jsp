
    <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>favorites</title>
</head>

<body>
     <%@include file="header.jsp" %>
  


    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="assets/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Sản Phẩm Yêu Thích</h2>
                        <div class="breadcrumb__option">
                            <a href="/HomePage">Home</a>
                            <span>Sản Phẩm Yêu Thích</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <div class="container mx-auto p-4">
        <!-- Header -->
        <div class="flex items-center justify-between mb-4">
            <div class="flex items-center">
                <img alt="Shopee logo" class="mr-2" height="40" src="assets/img/logo.png" width="40"/>
                <h1 class="text-xl font-bold text-orange-500">
                    Ogani
                </h1>
                <span class="ml-2 text-lg">
                    Sản Phẩm Yêu Thích
                </span>
            </div>
            <div class="flex items-center">
                <input class="border border-gray-300 p-2 rounded-l-md w-96" placeholder="Tìm sản phẩm, thương hiệu, và tên shop" type="text"/>
                <button class="bg-orange-500 text-white p-2 rounded-r-md">
                    <i class="fas fa-search">
                    </i>
                </button>
            </div>
        </div>
        <div class="bg-white p-4 rounded-md shadow-md">
            <!-- Header -->
            <div class="bg-white p-4 rounded-md shadow-md">
                <!-- Tiêu đề của danh sách sản phẩm -->
                <div class="flex items-center justify-between border-b pb-2 mb-2">
                    <div class="flex items-center w-1/4">
                        <!-- Checkbox ở tiêu đề để chọn tất cả sản phẩm -->
                        <input class="mr-2" type="checkbox" id="selectAllCheckboxFooter"/>
                        <span class="font-bold">Sản Phẩm</span>
                    </div>
                    <div class="flex items-center space-x-8 w-3/4">
                        <span class="font-bold w-1/5 text-center">Giá Gốc</span>
                        <span class="font-bold w-1/5 text-center">Đơn Giá</span>
                        <span class="font-bold w-1/5 text-center">Số Lượng</span>
                        <span class="font-bold w-1/5 text-center">Số Tiền</span>
                        <span class="font-bold w-1/5 text-center">Thao Tác</span>
                    </div>
                </div>

                <!-- Products container -->
                <div class="products-container">
                    <!-- Loop for each product -->
                    <c:forEach var="favorite" items="${favoriteProducts}">
                        <div class="product-card flex items-center justify-between border-b pb-2 mb-2" data-product-id="${favorite.productId}">
                            <div class="flex items-center w-1/4">
                                <!-- Checkbox cho từng sản phẩm -->
                                <input class="productCheckbox mr-2" type="checkbox" data-product-id="${favorite.productId}" />
                                <div>
                                    <span class="block font-bold"><p>${favorite.shopName}</p></span>
                                    <div class="flex items-center mt-2">
                                        <img alt="Product image" class="mr-2" height="60" src="${favorite.imageURL}" width="60"/>
                                        <div>
                                            <span class="block font-bold">${favorite.name}</span>
                                            <span class="block text-sm text-gray-500">Số lượng hàng: ${favorite.stockQuantity}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="flex items-center space-x-8 w-3/4">
                                <span class="text-gray-500 line-through w-1/5 text-center">469.000VND</span>
                                <span class="text-red-500 product-price w-1/5 text-center" data-price="${favorite.price}">${favorite.formattedPrice}VND</span>
                                <div class="flex items-center w-1/5">
                                    <button class="decreaseBtn border border-gray-300 px-2">-</button>
                                    <input class="quantityInput w-12 text-center border-t border-b border-gray-300" type="text" value="1"/>
                                    <button class="increaseBtn border border-gray-300 px-2">+</button>
                                </div>
                                <span class="totalPrice text-red-500 w-1/5 text-center">${favorite.formattedPrice}VND</span>   
                                <button class="deleteBtn w-1/5 text-red-500" data-product-id="${favorite.productId}">Xóa</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Pagination -->
                <ul class="pagination-wrapper">
                    <c:if test="${currentPage > 1}">
                        <li class="pagination-item"><a href="?page=${currentPage - 1}">«</a></li>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="pagination-item ${i == currentPage ? 'active' : ''}">
                            <a href="?page=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <li class="pagination-item"><a href="?page=${currentPage + 1}">»</a></li>
                    </c:if>
                </ul>

                <!-- Footer -->
                <div class="flex items-center justify-between mt-4">
                    <div class="flex items-center">
                        <input class="mr-2" type="checkbox" id="selectAllCheckboxFooter"/>
                        <span class="font-bold">
                            Chọn Tất Cả 
                        </span>
                        <button class="ml-4 text-blue-500" id="deleteSelectedBtn">
                            Xóa
                        </button>
                        <button class="ml-4 text-blue-500">
                            Bỏ sản phẩm không hoạt động
                        </button>
                    </div>
                    <div class="flex items-center">
                        <span class="mr-4">
                            Voucher
                        </span>
                        <button class="border border-gray-300 px-4 py-2">
                            Chọn hoặc nhập mã
                        </button>
                    </div>
                </div>

                <!-- Tổng thanh toán -->
                <div class="flex items-center justify-between mt-4">
                    <div class="flex items-center">
                        <span class="text-red-500" id="totalPriceText">
                            Tổng thanh toán (0 Sản phẩm): ₫0
                        </span>
                    </div>
                    <button class="bg-orange-500 text-white px-6 py-2 rounded-md">
                        Chuyển vào giỏ hàng
                    </button>
                </div>
            </div>

        </div>
    </div>
      <%@include file="footer.jsp" %>
    
</body>

</html>