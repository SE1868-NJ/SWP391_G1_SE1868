// Form Validation
const validateForm = {
    fullName: (value) => {
        return value.length >= 2 && value.length <= 50;
    },
    phoneNumber: (value) => {
        return /^\d{10,11}$/.test(value);
    },
    address: (value) => {
        return value.length > 0 && value.length <= 200;
    },
    password: (value) => {
        if (!value) return true; // Optional field
        return value.length >= 8 && /\d/.test(value);
    }
};

// Image handling functions
function handleImageSelect(input) {
    const file = input.files[0];
    if (!file) return;

    // Reset previous errors
    hideError();

    // Validate file
    if (!validateImage(file)) {
        return;
    }

    // Show preview modal
    showPreview(file);
}

function validateImage(file) {
    const maxSize = 5 * 1024 * 1024; // 5MB
    const allowedTypes = ['image/jpeg', 'image/png'];

    if (!allowedTypes.includes(file.type)) {
        showError('Chỉ chấp nhận file JPG hoặc PNG');
        return false;
    }

    if (file.size > maxSize) {
        showError('Kích thước file không được vượt quá 5MB');
        return false;
    }

    return true;
}

function showError(message) {
    const errorElement = document.getElementById('imageError');
    errorElement.textContent = message;
    errorElement.classList.remove('hidden');
}

function hideError() {
    const errorElement = document.getElementById('imageError');
    errorElement.classList.add('hidden');
}

function showPreview(file) {
    const reader = new FileReader();
    reader.onload = function(e) {
        document.getElementById('previewImage').src = e.target.result;
        document.getElementById('fileName').textContent = `Tên file: ${file.name}`;
        document.getElementById('fileSize').textContent = `Kích thước: ${formatFileSize(file.size)}`;
        document.getElementById('fileType').textContent = `Loại file: ${file.type}`;

        const modal = document.getElementById('previewModal');
        modal.style.display = 'flex';
        // Add active class after a short delay for animation
        setTimeout(() => modal.classList.add('active'), 10);
    };
    reader.readAsDataURL(file);
}

function formatFileSize(bytes) {
    if (bytes < 1024) return bytes + ' bytes';
    if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(2) + ' KB';
    return (bytes / (1024 * 1024)).toFixed(2) + ' MB';
}

function closePreview() {
    const modal = document.getElementById('previewModal');
    modal.classList.remove('active');
    setTimeout(() => {
        modal.style.display = 'none';
        document.getElementById('imageInput').value = '';
    }, 300);
}

function confirmUpload() {
    const profileImage = document.getElementById('profileImage');
    const previewImage = document.getElementById('previewImage');
    profileImage.src = previewImage.src;
    closePreview();
    showToast('Ảnh đã được chọn, nhấn Lưu để cập nhật', 'success');
}

// Show loading animation during upload
function showLoading() {
    document.querySelector('.loading-overlay').style.display = 'block';
    document.querySelector('.loading-spinner').style.display = 'block';
}

function hideLoading() {
    document.querySelector('.loading-overlay').style.display = 'none';
    document.querySelector('.loading-spinner').style.display = 'none';
}

// Toggle between view and edit modes with smooth transition
function toggleEditMode() {
    const viewModeElements = document.querySelectorAll('.view-mode');
    const editModeElements = document.querySelectorAll('.edit-mode');

    // Store current scroll position
    const scrollPos = window.scrollY;

    viewModeElements.forEach(el => {
        el.style.opacity = '0';
        setTimeout(() => {
            el.classList.add('hidden');
        }, 300);
    });

    setTimeout(() => {
        editModeElements.forEach(el => {
            el.classList.remove('hidden');
            setTimeout(() => {
                el.style.opacity = '1';
            }, 10);
        });
        // Restore scroll position
        window.scrollTo(0, scrollPos);
    }, 300);
}

// Cancel edit and reset form with smooth transition
function cancelEdit() {
    const form = document.getElementById('profileForm');
    const viewModeElements = document.querySelectorAll('.view-mode');
    const editModeElements = document.querySelectorAll('.edit-mode');

    // Store scroll position
    const scrollPos = window.scrollY;

    editModeElements.forEach(el => {
        el.style.opacity = '0';
        setTimeout(() => {
            el.classList.add('hidden');
            form.reset();
        }, 300);
    });

    setTimeout(() => {
        viewModeElements.forEach(el => {
            el.classList.remove('hidden');
            setTimeout(() => {
                el.style.opacity = '1';
            }, 10);
        });
        // Restore scroll position
        window.scrollTo(0, scrollPos);
    }, 300);

    // Reset image if changed
    const profileImage = document.getElementById('profileImage');
    if (window.originalImageSrc && profileImage.src !== window.originalImageSrc) {
        profileImage.src = window.originalImageSrc;
    }
}

// Enhanced toast message system
function showToast(message, type = 'success') {
    // Remove existing toasts
    const existingToasts = document.querySelectorAll('.toast-message');
    existingToasts.forEach(toast => toast.remove());

    // Create new toast
    const toast = document.createElement('div');
    toast.className = `fixed top-4 right-4 p-4 rounded-lg shadow-lg z-50 toast-message flex items-center ${
        type === 'success' ? 'bg-green-500' : 'bg-red-500'
    } text-white`;

    // Add icon based on type
    const icon = document.createElement('i');
    icon.className = `fas ${type === 'success' ? 'fa-check-circle' : 'fa-exclamation-circle'} mr-2`;
    toast.appendChild(icon);

    // Add message
    const text = document.createElement('span');
    text.textContent = message;
    toast.appendChild(text);

    document.body.appendChild(toast);

    // Animate in
    requestAnimationFrame(() => {
        toast.style.transform = 'translateY(0)';
        toast.style.opacity = '1';
    });

    // Remove after delay
    setTimeout(() => {
        toast.style.transform = 'translateY(-20px)';
        toast.style.opacity = '0';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', function() {
    // Store original image src for cancel operation
    const profileImage = document.getElementById('profileImage');
    if (profileImage) {
        window.originalImageSrc = profileImage.src;
    }

    // Enhanced form validation
    const form = document.getElementById('profileForm');
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();

            // Validate all fields
            const errors = [];
            const formData = new FormData(this);

            for (const [field, validate] of Object.entries(validateForm)) {
                const value = formData.get(field);
                if (value && !validate(value)) {
                    errors.push(getErrorMessage(field));
                }
            }

            if (errors.length > 0) {
                showToast(errors.join('\n'), 'error');
                return;
            }

            // Show loading animation
            showLoading();

            // Submit the form
            this.submit();
        });
    }

    // Auto hide messages after 5 seconds
    document.querySelectorAll('.success-message, .error-message').forEach(message => {
        setTimeout(() => {
            message.style.opacity = '0';
            setTimeout(() => message.remove(), 300);
        }, 5000);
    });
});

// Helper function for error messages
function getErrorMessage(field) {
    const messages = {
        fullName: 'Họ tên phải từ 2-50 ký tự',
        phoneNumber: 'Số điện thoại không hợp lệ (10-11 số)',
        address: 'Địa chỉ không được để trống và tối đa 200 ký tự',
        password: 'Mật khẩu phải có ít nhất 8 ký tự và chứa số'
    };
    return messages[field] || 'Thông tin không hợp lệ';
}