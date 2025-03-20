<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>

        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hồ Sơ Người Dùng</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="assets/css/profile.css">
    </head>
    <body class="bg-gray-50">
        <!-- Header with Navigation -->
        <header class="bg-white shadow-sm">
            <div class="container mx-auto px-4 py-4">
                <nav class="flex items-center justify-between">
                    <div class="flex items-center space-x-4">
                        <a href="/" class="text-gray-600 hover:text-gray-900">
                            <i class="fas fa-home"></i>
                        </a>
                        <span class="text-gray-400">/</span>
                        <span class="text-gray-900">Hồ Sơ Người Dùng</span>
                    </div>
                    <div class="flex items-center space-x-4">
                        <a href="#settings" class="text-gray-600 hover:text-gray-900">
                            <i class="fas fa-cog"></i>
                        </a>
                        <a href="#notifications" class="text-gray-600 hover:text-gray-900">
                            <i class="fas fa-bell"></i>
                        </a>
                    </div>
                </nav>
            </div>
        </header>

        <!-- Image Preview Modal -->
        <div id="previewModal" class="preview-modal">
            <div class="preview-content">
                <img id="previewImage" class="preview-image" src="" alt="Preview">
                <div class="file-info mt-4">
                    <p id="fileName"></p>
                    <p id="fileSize"></p>
                    <p id="fileType"></p>
                </div>
                <div class="mt-4 flex justify-end space-x-4">
                    <button onclick="closePreview()" 
                            class="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600">
                        <i class="fas fa-times mr-2"></i>Đóng
                    </button>
                    <button onclick="confirmUpload()" 
                            class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                        <i class="fas fa-check mr-2"></i>Xác Nhận
                    </button>
                </div>
            </div>
        </div>

        <!-- Check Login Status -->
        <c:if test="${empty sessionScope.user}">
            <script>
                alert("Vui lòng đăng nhập để truy cập trang profile!");
                window.location.href = "login.jsp";
            </script>
        </c:if>

        <!-- Loading States -->
        <div class="loading-spinner"></div>
        <div class="loading-overlay"></div>

        <!-- Main Content -->
        <main class="container mx-auto px-4 py-8">
            <!-- Success/Error Messages -->
            <c:if test="${not empty sessionScope.successMessage}">
                <div class="success-message" role="alert">
                    <div class="flex items-center">
                        <i class="fas fa-check-circle mr-2"></i>
                        <span>${sessionScope.successMessage}</span>
                    </div>
                    <button class="absolute top-0 right-0 p-2" onclick="this.parentElement.remove()">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
                <% session.removeAttribute("successMessage"); %>
            </c:if>

            <div class="max-w-4xl mx-auto">
                <!-- Profile Header -->
                <div class="text-center mb-8">
                    <h1 class="text-3xl font-bold text-gray-900">Hồ Sơ Của Tôi</h1>
                    <p class="text-gray-600 mt-2">Quản lý thông tin cá nhân của bạn</p>
                </div>

                <!-- Profile Content -->
                <div class="bg-white rounded-xl shadow-lg overflow-hidden">
                    <div class="md:flex">
                        <!-- Left Side - Profile Image -->
                        <div class="md:w-1/3 bg-gray-50 p-8 border-r border-gray-200">
                            <div class="image-upload-container mb-6">
                                <img id="profileImage" 
                                     src="${empty customer.profileImage ? 'assets/img/profile/default-avatar.svg' : customer.profileImage}"
                                     alt="Profile"
                                     class="profile-image">
                                <div class="image-upload-overlay" onclick="document.getElementById('imageInput').click()">
                                    <i class="fas fa-camera fa-lg text-white"></i>
                                </div>
                                <div class="upload-loading"></div>
                            </div>
                            <input type="file" 
                                   id="imageInput" 
                                   name="profileImage" 
                                   accept="image/jpeg,image/png"
                                   class="hidden"
                                   onchange="handleImageSelect(this)">
                            <div id="imageError" class="file-error hidden"></div>

                            <div class="text-center">
                                <h2 class="text-xl font-semibold text-gray-900 mb-2">${customer.fullName}</h2>
                                <p class="text-gray-600 mb-6">${customer.email}</p>

                                <!-- Action Buttons -->
                                <button onclick="toggleEditMode()" 
                                        class="view-mode btn btn-primary w-full mb-3">
                                    <i class="fas fa-edit mr-2"></i>Chỉnh Sửa
                                </button>

                                <div class="edit-mode hidden space-y-3">
                                    <button type="submit" 
                                            form="profileForm"
                                            class="btn btn-primary w-full">
                                        <i class="fas fa-save mr-2"></i>Lưu
                                    </button>
                                    <button onclick="cancelEdit()"
                                            class="btn btn-secondary w-full">
                                        <i class="fas fa-times mr-2"></i>Hủy
                                    </button>
                                </div>
                            </div>
                        </div>

                        <!-- Right Side - Profile Details -->
                        <div class="md:w-2/3 p-8">
                            <!-- View Mode -->
                            <div class="view-mode space-y-6">
                                <div class="profile-card">
                                    <h3 class="text-gray-700 mb-3">
                                        <i class="fas fa-user mr-2 text-primary"></i>Thông Tin Cá Nhân
                                    </h3>
                                    <div class="grid md:grid-cols-2 gap-4">
                                        <div>
                                            <label class="text-gray-600 text-sm">Họ và Tên</label>
                                            <p class="text-gray-900 font-medium" data-field="fullName">${customer.fullName}</p>
                                        </div>
                                        <div>
                                            <label class="text-gray-600 text-sm">Email</label>
                                            <p class="text-gray-900 font-medium">${customer.email}</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="profile-card">
                                    <h3 class="text-gray-700 mb-3">
                                        <i class="fas fa-phone mr-2 text-primary"></i>Liên Hệ
                                    </h3>
                                    <div class="grid md:grid-cols-2 gap-4">
                                        <div>
                                            <label class="text-gray-600 text-sm">Số Điện Thoại</label>
                                            <p class="text-gray-900 font-medium" data-field="phoneNumber">${customer.phoneNumber}</p>
                                        </div>
                                        <div>
                                            <label class="text-gray-600 text-sm">Địa Chỉ</label>
                                            <p class="text-gray-900 font-medium" data-field="address">${customer.address}</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="profile-card">
                                    <h3 class="text-gray-700 mb-3">
                                        <i class="fas fa-info-circle mr-2 text-primary"></i>Thông Tin Khác
                                    </h3>
                                    <div class="grid md:grid-cols-2 gap-4">
                                        <div>
                                            <label class="text-gray-600 text-sm">Ngày Sinh</label>
                                            <p class="text-gray-900 font-medium" data-field="birthDate">${customer.birthDate}</p>
                                        </div>
                                        <div>
                                            <label class="text-gray-600 text-sm">Giới Tính</label>
                                            <p class="text-gray-900 font-medium" data-field="gender">${customer.gender == 'Male' ? 'Nam' : 'Nữ'}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Edit Mode Form -->
                            <form id="profileForm" 
                                  action="profile" 
                                  method="post" 
                                  enctype="multipart/form-data" 
                                  class="edit-mode hidden space-y-6">

                                <!-- Personal Information -->
                                <div class="form-section">
                                    <h3 class="text-gray-700 mb-4">
                                        <i class="fas fa-user mr-2 text-primary"></i>Thông Tin Cá Nhân
                                    </h3>
                                    <div class="grid md:grid-cols-2 gap-4">
                                        <div class="form-group">
                                            <label class="form-label" for="fullName">
                                                Họ và Tên
                                            </label>
                                            <div class="input-group">
                                                <span class="input-icon">
                                                    <i class="fas fa-user"></i>
                                                </span>
                                                <input type="text" 
                                                       id="fullName" 
                                                       name="fullName" 
                                                       value="${customer.fullName}"
                                                       class="form-control"
                                                       required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="form-label" for="phoneNumber">
                                                Số Điện Thoại
                                            </label>
                                            <div class="input-group">
                                                <span class="input-icon">
                                                    <i class="fas fa-phone"></i>
                                                </span>
                                                <input type="tel" 
                                                       id="phoneNumber" 
                                                       name="phoneNumber" 
                                                       value="${customer.phoneNumber}"
                                                       class="form-control"
                                                       required>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Contact Information -->
                                <div class="form-section">
                                    <h3 class="text-gray-700 mb-4">
                                        <i class="fas fa-map-marker-alt mr-2 text-primary"></i>Địa Chỉ
                                    </h3>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-icon">
                                                <i class="fas fa-home"></i>
                                            </span>
                                            <input type="text" 
                                                   id="address" 
                                                   name="address" 
                                                   value="${customer.address}"
                                                   class="form-control"
                                                   required>
                                        </div>
                                    </div>
                                </div>

                                <!-- Other Information -->
                                <div class="form-section">
                                    <h3 class="text-gray-700 mb-4">
                                        <i class="fas fa-info-circle mr-2 text-primary"></i>Thông Tin Khác
                                    </h3>
                                    <div class="grid md:grid-cols-2 gap-4">
                                        <div class="form-group">
                                            <label class="form-label" for="birthDate">
                                                Ngày Sinh
                                            </label>
                                            <div class="input-group">
                                                <span class="input-icon">
                                                    <i class="fas fa-calendar"></i>
                                                </span>
                                                <input type="date" 
                                                       id="birthDate" 
                                                       name="birthDate" 
                                                       value="${customer.birthDate}"
                                                       class="form-control"
                                                       required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="form-label" for="gender">
                                                Giới Tính
                                            </label>
                                            <div class="input-group">
                                                <span class="input-icon">
                                                    <i class="fas fa-venus-mars"></i>
                                                </span>
                                                <select id="gender" 
                                                        name="gender" 
                                                        class="form-control"
                                                        required>
                                                    <option value="Male" ${customer.gender == 'Male' ? 'selected' : ''}>Nam</option>
                                                    <option value="Female" ${customer.gender == 'Female' ? 'selected' : ''}>Nữ</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="assets/js/profile.js"></script>
    </body>
</html>