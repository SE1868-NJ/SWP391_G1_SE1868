package entity;

import java.time.LocalDate;
import java.util.Date;

public class BlogDetail {

    private int idBlogDetail;
    private int idBlog;
    private String title;
    private String content; // Thay contentFilePath thành content
    private LocalDate createdDate;
    private String imageUrl;

    // Constructor mặc định
    public BlogDetail() {
    }

    // Constructor đầy đủ
    public BlogDetail(int idBlogDetail, int idBlog, String title, String content, LocalDate createdDate, String imageUrl) {
        this.idBlogDetail = idBlogDetail;
        this.idBlog = idBlog;
        this.title = title;
        this.content = content; // Thay contentFilePath thành content
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

    public String getContent() { // Thay getContentFilePath thành getContent
        return content;
    }

    public void setContent(String content) { // Thay setContentFilePath thành setContent
        this.content = content;
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
                + ", content='" + content + '\'' // Thay contentFilePath thành content
                + ", createdDate=" + createdDate
                + ", imageUrl='" + imageUrl + '\''
                + '}';
    }
}