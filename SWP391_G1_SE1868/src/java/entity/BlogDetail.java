/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nguyen
 */


public class BlogDetail {
    private int idBlogDetail;
    private int idBlog;
    private String title;
    private String contentFilePath;
    private String createdDate;

    // Constructor
    public BlogDetail() {
    }

    public BlogDetail(int idBlogDetail, int idBlog, String title, String contentFilePath, String createdDate) {
        this.idBlogDetail = idBlogDetail;
        this.idBlog = idBlog;
        this.title = title;
        this.contentFilePath = contentFilePath;
        this.createdDate = createdDate;
    }

    // Getters and Setters
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "BlogDetail{" +
                "idBlogDetail=" + idBlogDetail +
                ", idBlog=" + idBlog +
                ", title='" + title + '\'' +
                ", contentFilePath='" + contentFilePath + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
