$(document).ready(function() {
    // Initialize the page
    initPage();

    // Search functionality
    setupSearch();

    // Filter functionality
    setupFilter();

    // Mark as read functionality
    setupMarkAsRead();

    // Mark all as read functionality
    setupMarkAllAsRead();
});

/**
 * Initialize the page
 */
function initPage() {
    // Hide loading elements on page load
    $('.loading-overlay, .loading-spinner').hide();
    
    // Sort notifications by date (newest first)
    sortNotifications();
}

/**
 * Sort notifications by creation date (newest first)
 */
function sortNotifications() {
    const notificationsList = $('.notification__list');
    const notifications = $('.notification__item').get();
    
    notifications.sort(function(a, b) {
        const timeA = $(a).find('.notification__time small').text();
        const timeB = $(b).find('.notification__time small').text();
        
        // Parse dates (format: dd/MM/yyyy HH:mm)
        const datePartsA = timeA.split(' ')[0].split('/');
        const timePartsA = timeA.split(' ')[1].split(':');
        const dateA = new Date(
            parseInt(datePartsA[2]), 
            parseInt(datePartsA[1]) - 1, 
            parseInt(datePartsA[0]),
            parseInt(timePartsA[0]),
            parseInt(timePartsA[1])
        );
        
        const datePartsB = timeB.split(' ')[0].split('/');
        const timePartsB = timeB.split(' ')[1].split(':');
        const dateB = new Date(
            parseInt(datePartsB[2]), 
            parseInt(datePartsB[1]) - 1, 
            parseInt(datePartsB[0]),
            parseInt(timePartsB[0]),
            parseInt(timePartsB[1])
        );
        
        // Sort descending (newest first)
        return dateB - dateA;
    });
    
    // Append sorted notifications back to the list
    $.each(notifications, function(i, notification) {
        notificationsList.append(notification);
    });
}

/**
 * Set up search functionality
 */
function setupSearch() {
    $('#searchNotification').on('input', function() {
        const searchTerm = $(this).val().toLowerCase();
        
        // Filter notifications based on search term
        $('.notification__item').each(function() {
            const message = $(this).find('.notification__message').text().toLowerCase();
            
            if (message.includes(searchTerm)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
        
        // Show empty state if no results
        if ($('.notification__item:visible').length === 0) {
            if ($('.notification__empty').length === 0) {
                $('.notification__list').append(`
                    <div class="notification__empty search-empty">
                        <div class="notification__empty-icon">
                            <i class="fa fa-search"></i>
                        </div>
                        <h4>No results found</h4>
                        <p>We couldn't find any notifications matching your search.</p>
                    </div>
                `);
            }
        } else {
            $('.notification__empty.search-empty').remove();
        }
    });
}

/**
 * Set up filter functionality
 */
function setupFilter() {
    $('#notificationFilter').on('change', function() {
        const filter = $(this).val();
        
        if (filter === 'all') {
            $('.notification__item').show();
        } else if (filter === 'unread') {
            $('.notification__item').hide();
            $('.notification__item.unread').show();
        } else if (filter === 'read') {
            $('.notification__item').hide();
            $('.notification__item:not(.unread)').show();
        }
        
        // Show empty state if no results
        if ($('.notification__item:visible').length === 0) {
            if ($('.notification__empty.filter-empty').length === 0) {
                $('.notification__list').append(`
                    <div class="notification__empty filter-empty">
                        <div class="notification__empty-icon">
                            <i class="fa fa-filter"></i>
                        </div>
                        <h4>No ${filter} notifications</h4>
                        <p>You don't have any ${filter} notifications at the moment.</p>
                    </div>
                `);
            }
        } else {
            $('.notification__empty.filter-empty').remove();
        }
    });
}

/**
 * Set up mark as read functionality
 */
function setupMarkAsRead() {
    $(document).on('click', '.mark-as-read', function() {
        const button = $(this);
        const notificationId = button.data('id');
        const notificationItem = button.closest('.notification__item');
        const isUnread = notificationItem.hasClass('unread');
        const newStatus = isUnread ? 'Read' : 'Unread';
        
        // Show loading state
        showLoading();
        
        // Send AJAX request to update status
        $.ajax({
            url: 'notification',
            type: 'POST',
            data: {
                notificationID: notificationId,
                status: newStatus
            },
            success: function() {
                // Update UI
                if (isUnread) {
                    notificationItem.removeClass('unread');
                    button.find('i').removeClass('fa-circle').addClass('fa-check-circle');
                    showToast('success', 'Notification marked as read');
                } else {
                    notificationItem.addClass('unread');
                    button.find('i').removeClass('fa-check-circle').addClass('fa-circle');
                    showToast('success', 'Notification marked as unread');
                }
                
                // Apply current filter
                $('#notificationFilter').trigger('change');
                
                // Hide loading state
                hideLoading();
            },
            error: function(xhr, status, error) {
                showToast('error', 'Error updating notification status: ' + error);
                hideLoading();
            }
        });
    });
}

/**
 * Set up mark all as read functionality
 */
function setupMarkAllAsRead() {
    $('#markAllAsRead').on('click', function() {
        const unreadNotifications = $('.notification__item.unread');
        
        if (unreadNotifications.length === 0) {
            showToast('info', 'No unread notifications');
            return;
        }
        
        // Confirm action
        if (!confirm('Are you sure you want to mark all notifications as read?')) {
            return;
        }
        
        // Show loading state
        showLoading();
        
        // Get all unread notification IDs
        const notificationIds = [];
        unreadNotifications.each(function() {
            notificationIds.push($(this).data('id'));
        });
        
        // Update each notification
        let completedRequests = 0;
        let failedRequests = 0;
        
        notificationIds.forEach(function(id) {
            $.ajax({
                url: 'notification',
                type: 'POST',
                data: {
                    notificationID: id,
                    status: 'Read'
                },
                success: function() {
                    completedRequests++;
                    checkIfDone();
                },
                error: function() {
                    failedRequests++;
                    checkIfDone();
                }
            });
        });
        
        function checkIfDone() {
            if (completedRequests + failedRequests === notificationIds.length) {
                // Update UI for all successfully updated notifications
                if (completedRequests > 0) {
                    unreadNotifications.removeClass('unread');
                    unreadNotifications.find('.mark-as-read i')
                        .removeClass('fa-circle')
                        .addClass('fa-check-circle');
                    
                    // Show success message
                    showToast('success', `${completedRequests} notification${completedRequests !== 1 ? 's' : ''} marked as read`);
                }
                
                // Show error if any failed
                if (failedRequests > 0) {
                    showToast('error', `Failed to update ${failedRequests} notification${failedRequests !== 1 ? 's' : ''}`);
                }
                
                // Apply current filter
                $('#notificationFilter').trigger('change');
                
                // Hide loading state
                hideLoading();
            }
        }
    });
}

/**
 * Show loading state
 */
function showLoading() {
    $('.loading-overlay, .loading-spinner').show();
}

/**
 * Hide loading state
 */
function hideLoading() {
    $('.loading-overlay, .loading-spinner').hide();
}

/**
 * Show toast notification
 * @param {string} type - Type of toast (success, error, info)
 * @param {string} message - Message to display
 */
function showToast(type, message) {
    // Remove existing toasts
    $('.toast').remove();
    
    // Create toast element
    const toast = $(`
        <div class="toast ${type}">
            <div class="toast-icon">
                <i class="fa fa-${type === 'success' ? 'check-circle' : type === 'error' ? 'exclamation-circle' : 'info-circle'}"></i>
            </div>
            <div class="toast-message">${message}</div>
            <div class="toast-close">
                <i class="fa fa-times"></i>
            </div>
        </div>
    `);
    
    // Add to body
    $('body').append(toast);
    
    // Show toast
    setTimeout(() => {
        toast.addClass('show');
    }, 10);
    
    // Auto hide after 3 seconds
    setTimeout(() => {
        toast.removeClass('show');
        setTimeout(() => {
            toast.remove();
        }, 300);
    }, 3000);
    
    // Close on click
    toast.find('.toast-close').on('click', function() {
        toast.removeClass('show');
        setTimeout(() => {
            toast.remove();
        }, 300);
    });
}
