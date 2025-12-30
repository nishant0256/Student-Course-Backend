package com.example.demo.DTO;

import java.time.LocalDate;

public class CourseDto {

    private Long id;
    private String title;
    private String subtitle;
    private String category;
    private String language;
    private String description;
    private String imageUrl;
    private Integer price;
    private Integer originalPrice;
    private Integer discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isNew;
    private Boolean isActive;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // ===== BOOLEAN FIX (CRITICAL) =====

    // ✔ Used by Jackson / ModelMapper
    public Boolean isNew() {
        return isNew;
    }

    public Boolean isActive() {
        return isActive;
    }

    // ✔ Backward compatibility (keep these)
    public Boolean getIsNew() {
        return isNew;
    }
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
