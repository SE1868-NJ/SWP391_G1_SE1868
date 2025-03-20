document.addEventListener("DOMContentLoaded", function () {
    // Elements
    const searchInput = document.querySelector('.search-input');
    const searchButton = document.querySelector('.search-button');
    const productsContainer = document.querySelector('.products-container');
    const loadingSpinner = document.querySelector('.loading-spinner');
    const loadingOverlay = document.querySelector('.loading-overlay');
    let searchTimeout;
    let totalPrice = 0;
    let totalQuantity = 0;

    // Show/Hide Loading with logging
    function showLoading() {
        console.log('Loading started');
        loadingSpinner.style.display = 'block';
        loadingOverlay.style.display = 'block';
    }

    function hideLoading() {
        console.log('Loading completed');
        loadingSpinner.style.display = 'none';
        loadingOverlay.style.display = 'none';
    }

    // Toast Notifications
    function showToast(message, type = 'success') {
        console.log(`Showing toast: ${message} (${type})`);
        const toast = document.createElement('div');
        toast.className = `toast toast-${type}`;
        toast.textContent = message;
        document.body.appendChild(toast);

        setTimeout(() => {
            toast.style.opacity = '0';
            setTimeout(() => toast.remove(), 300);
        }, 3000);
    }

    // Update total display
    function updateTotalDisplay() {
        let selectedProducts = document.querySelectorAll(".productCheckbox:checked");
        let selectedTotalPrice = 0;
        let selectedTotalQuantity = 0;

        selectedProducts.forEach((checkbox) => {
            let productCard = checkbox.closest(".product-card");
            let productPrice = parseFloat(productCard.querySelector(".product-price").dataset.price);
            let quantityInput = productCard.querySelector(".quantityInput");
            let quantity = parseInt(quantityInput.value) || 1;

            selectedTotalPrice += productPrice * quantity;
            selectedTotalQuantity += quantity;
        });

        document.getElementById("totalPriceText").textContent =
            `Tổng thanh toán (${selectedTotalQuantity} Sản phẩm): ₫${selectedTotalPrice.toLocaleString()} VND`;
    }

    // Update total price for a single product
    function updateTotalPriceForProduct(productCard) {
        const quantityInput = productCard.querySelector(".quantityInput");
        const totalPriceElement = productCard.querySelector(".totalPrice");
        const priceElement = productCard.querySelector(".product-price");
        const stockQuantityElement = productCard.querySelector(".stock-quantity");
        
        let quantity = parseInt(quantityInput.value) || 1;
        let productPrice = parseFloat(priceElement.dataset.price);
        let originalStock = parseInt(stockQuantityElement.dataset.originalStock);

        if (quantity < 1) {
            quantity = 1;
            quantityInput.value = 1;
        }

        if (quantity > originalStock) {
            quantity = originalStock;
            quantityInput.value = originalStock;
            showToast('Số lượng đã đạt giới hạn tồn kho', 'warning');
        }

        // Cập nhật số lượng tồn kho hiển thị
        let remainingStock = originalStock - quantity;
        stockQuantityElement.textContent = `Số lượng hàng: ${remainingStock}`;

        let total = productPrice * quantity;
        totalPriceElement.textContent = total.toLocaleString() + ' VND';
        
        updateTotalDisplay();
    }

    // Setup Quantity Controls
    function setupQuantityControls() {
        document.querySelectorAll(".product-card").forEach(productCard => {
            const quantityInput = productCard.querySelector(".quantityInput");
            const decreaseBtn = productCard.querySelector(".decreaseBtn");
            const increaseBtn = productCard.querySelector(".increaseBtn");

            decreaseBtn.addEventListener("click", function(e) {
                e.preventDefault();
                let currentValue = parseInt(quantityInput.value) || 1;
                if (currentValue > 1) {
                    quantityInput.value = currentValue - 1;
                    updateTotalPriceForProduct(productCard);
                }
            });

            increaseBtn.addEventListener("click", function(e) {
                e.preventDefault();
                let currentValue = parseInt(quantityInput.value) || 1;
                quantityInput.value = currentValue + 1;
                updateTotalPriceForProduct(productCard);
            });

            quantityInput.addEventListener("change", function() {
                let value = parseInt(this.value) || 1;
                if (value < 1) value = 1;
                this.value = value;
                updateTotalPriceForProduct(productCard);
            });
        });
    }

    // Setup Checkbox Handlers
    function setupCheckboxHandlers() {
        const selectAllCheckbox = document.getElementById("selectAllCheckboxFooter");
        const productCheckboxes = document.querySelectorAll(".productCheckbox");

        if (selectAllCheckbox) {
            selectAllCheckbox.addEventListener("change", function() {
                productCheckboxes.forEach(checkbox => {
                    checkbox.checked = this.checked;
                });
                updateTotalDisplay();
            });
        }

        productCheckboxes.forEach(checkbox => {
            checkbox.addEventListener("change", () => {
                updateTotalDisplay();
                if (selectAllCheckbox) {
                    selectAllCheckbox.checked = 
                        document.querySelectorAll(".productCheckbox:checked").length === productCheckboxes.length;
                }
            });
        });
    }

    // Delete Selected Products
    document.getElementById("deleteSelectedBtn").addEventListener("click", function() {
        let selectedProducts = document.querySelectorAll(".productCheckbox:checked");
        if (selectedProducts.length === 0) {
            showToast('Vui lòng chọn sản phẩm để xóa', 'warning');
            return;
        }

        if (confirm('Bạn có chắc muốn xóa các sản phẩm đã chọn?')) {
            selectedProducts.forEach((checkbox) => {
                let productCard = checkbox.closest(".product-card");
                let productId = productCard.dataset.productId;
                productCard.classList.add('deleting');
                setTimeout(() => deleteFavorite(productId), 300);
            });
        }
    });

    // Delete Favorite Handler
    function deleteFavorite(productId, productCard) {
        showLoading();
        fetch('/DeleteFavoriteController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'productId=' + productId
        })
        .then(response => {
            if (!response.ok) throw new Error('Delete request failed');
            return response.text();
        })
        .then(result => {
            hideLoading();
            if (result === 'success') {
                showToast('Sản phẩm đã được xóa khỏi danh sách yêu thích');
                const productCard = document.querySelector(`[data-product-id="${productId}"]`);
                productCard.remove();
                updateTotalDisplay();
            }
        })
        .catch(error => {
            hideLoading();
            showToast('Có lỗi xảy ra khi xóa sản phẩm', 'error');
        });
    }

    // Search Handler with Debounce
    function handleSearch() {
        const query = searchInput.value.trim();
        clearTimeout(searchTimeout);

        if (!query) {
            showToast('Vui lòng nhập từ khóa tìm kiếm', 'warning');
            return;
        }

        searchTimeout = setTimeout(() => {
            showLoading();
            fetch('/SearchFavoriteController', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `query=${encodeURIComponent(query)}&page=1`
            })
            .then(response => response.json())
            .then(data => {
                hideLoading();
                if (data.products.length === 0) {
                    showToast('Không tìm thấy sản phẩm phù hợp', 'warning');
                    productsContainer.innerHTML = `
                        <div class="empty-state">
                            <i class="fas fa-search fa-3x"></i>
                            <p>Không tìm thấy sản phẩm nào phù hợp với từ khóa "${query}"</p>
                        </div>
                    `;
                } else {
                    showToast(`Tìm thấy ${data.totalResults} sản phẩm`, 'success');
                    updateProductList(data);
                }
            })
            .catch(error => {
                hideLoading();
                showToast('Có lỗi xảy ra khi tìm kiếm sản phẩm', 'error');
            });
        }, 300);
    }

    // Update Product List (from original code, mostly unchanged)
    function updateProductList(data) {
        productsContainer.style.opacity = '0';
        setTimeout(() => {
            productsContainer.innerHTML = data.products.map(product => `
                <div class="product-card" data-product-id="${product.productId}">
                    <div class="flex items-center w-1/4">
                        <input class="productCheckbox mr-2" type="checkbox" data-product-id="${product.productId}" />
                        <div>
                            <span class="block font-bold">${product.shopName}</span>
                            <div class="flex items-center mt-2">
                                <img alt="Product image" class="mr-2" height="60" src="${product.imageURL}" width="60"/>
                                <div>
                                    <span class="block font-bold">${product.name}</span>
                                    <span class="block text-sm text-gray-500 stock-quantity" data-original-stock="${product.stockQuantity}">Số lượng hàng: ${product.stockQuantity}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="flex items-center space-x-8 w-3/4">
                        <span class="text-gray-500 line-through w-1/5 text-center">469.000VND</span>
                        <span class="text-red-500 product-price w-1/5 text-center" data-price="${product.price}">${product.formattedPrice}VND</span>
                        <div class="flex items-center w-1/5 quantity-controls">
                            <button class="decreaseBtn border border-gray-300 px-2">-</button>
                            <input class="quantityInput w-12 text-center border-t border-b border-gray-300" type="text" value="1"/>
                            <button class="increaseBtn border border-gray-300 px-2">+</button>
                        </div>
                        <span class="totalPrice text-red-500 w-1/5 text-center">${product.formattedPrice}VND</span>   
                        <button class="deleteBtn w-1/5 text-red-500" data-product-id="${product.productId}">Xóa</button>
                    </div>
                </div>
            `).join('');

            productsContainer.style.opacity = '1';
            setupQuantityControls();
            setupCheckboxHandlers();
            setupDeleteHandlers();
            updatePagination(data.currentPage, data.totalPages);
        }, 300);
    }


    // Setup Delete Handlers (from original code, mostly unchanged)
    function setupDeleteHandlers() {
        document.querySelectorAll('.deleteBtn').forEach(btn => {
            btn.addEventListener('click', function() {
                const productId = this.dataset.productId;
                if (confirm('Bạn có chắc muốn xóa sản phẩm này khỏi danh sách yêu thích?')) {
                    deleteFavorite(productId);
                }
            });
        });
    }

    // Update Pagination (from original code, mostly unchanged)
    function updatePagination(currentPage, totalPages) {
        const paginationContainer = document.querySelector('.pagination-wrapper');
        let paginationHTML = '';

        if (currentPage > 1) {
            paginationHTML += `<li class="pagination-item"><a href="#" data-page="${currentPage - 1}">«</a></li>`;
        }

        for (let i = 1; i <= totalPages; i++) {
            paginationHTML += `
                <li class="pagination-item ${i === currentPage ? 'active' : ''}">
                    <a href="#" data-page="${i}">${i}</a>
                </li>
            `;
        }

        if (currentPage < totalPages) {
            paginationHTML += `<li class="pagination-item"><a href="#" data-page="${currentPage + 1}">»</a></li>`;
        }

        paginationContainer.innerHTML = paginationHTML;

        paginationContainer.querySelectorAll('.pagination-item a').forEach(link => {
            link.onclick = (e) => {
                e.preventDefault();
                const page = parseInt(link.dataset.page);
                handlePageChange(page);
            };
        });
    }

    // Handle Page Change (from original code, mostly unchanged)
    function handlePageChange(page) {
        const query = searchInput.value.trim();
        showLoading();

        fetch(`/SearchFavoriteController?query=${encodeURIComponent(query)}&page=${page}`, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            hideLoading();
            updateProductList(data);
            window.scrollTo(0, 0);
        })
        .catch(error => {
            hideLoading();
            showToast('Có lỗi xảy ra khi tải trang', 'error');
            console.error('Pagination error:', error);
        });
    }

    // Initial setup
    setupQuantityControls();
    setupCheckboxHandlers();
    updateTotalDisplay();

    // Event Listeners
    searchInput.addEventListener('input', handleSearch);
    searchButton.addEventListener('click', handleSearch);
    searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            handleSearch();
        }
    });

    console.log('Favorites page initialized successfully');
});