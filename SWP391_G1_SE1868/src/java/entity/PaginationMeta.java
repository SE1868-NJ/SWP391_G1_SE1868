package entity;

/**
 * Model class representing pagination metadata
 */
public class PaginationMeta {
    private int currentPage;
    private int totalPages;
    private int totalItems;
    private int itemsPerPage;
    
    public PaginationMeta() {
    }
    
    public PaginationMeta(int currentPage, int totalPages, int totalItems, int itemsPerPage) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getTotalPages() {
        return totalPages;
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    public int getTotalItems() {
        return totalItems;
    }
    
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    
    public int getItemsPerPage() {
        return itemsPerPage;
    }
    
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
    
    @Override
    public String toString() {
        return "PaginationMeta{" +
                "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", itemsPerPage=" + itemsPerPage +
                '}';
    }
}