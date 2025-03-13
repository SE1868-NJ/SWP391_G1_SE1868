package entity;

import java.time.LocalDate;
import java.util.Date;

public class BlogDetail {

    private int idBlogDetail;
    private int idBlog;
    private String title;
    private String contentFilePath;
    private LocalDate createdDate; // Đổi từ String sang LocalDate
    private String imageUrl;

    // Constructor mặc định
    public BlogDetail() {
    }

    // Constructor đầy đủ
    public BlogDetail(int idBlogDetail, int idBlog, String title, String contentFilePath, LocalDate createdDate, String imageUrl) {
        this.idBlogDetail = idBlogDetail;
        this.idBlog = idBlog;
        this.title = title;
        this.contentFilePath = contentFilePath;
        this.createdDate = createdDate;
        this.imageUrl = imageUrl;
    }

    // Getters và Setters
    public int getIdBlogDetail() {
        return idBlogDetail;
    }

    public void setIdBlogDetail(int idBlogDetail) {
        this.idBlogDetail = idBlogDetail;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentFilePath() {
        return contentFilePath;
    }

    public void setContentFilePath(String contentFilePath) {
        this.contentFilePath = contentFilePath;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    // Getter phụ để dùng trong JSP
    public Date getCreatedDateAsUtilDate() {
        return createdDate != null ? java.sql.Date.valueOf(createdDate) : null;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BlogDetail{"
                + "idBlogDetail=" + idBlogDetail
                + ", idBlog=" + idBlog
                + ", title='" + title + '\''
                + ", contentFilePath='" + contentFilePath + '\''
                + ", createdDate=" + createdDate
                + ", imageUrl='" + imageUrl + '\''
                + '}';
    }
}