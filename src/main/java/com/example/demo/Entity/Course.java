package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "batches")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_seq_gen")
    @SequenceGenerator(
            name = "batch_seq_gen",
            sequenceName = "batch_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String language;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer originalPrice;

    @Column(nullable = false)
    private Integer discountPercent;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean isNew = false;

    @Column(nullable = false)
    private Boolean isActive = true;

    // ================= RELATION (FIXED) =================
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Student> students = new ArrayList<>();

    // ================= GETTERS & SETTERS =================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(Integer originalPrice) { this.originalPrice = originalPrice; }

    public Integer getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Integer discountPercent) { this.discountPercent = discountPercent; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Boolean getIsNew() { return isNew; }
    public void setIsNew(Boolean isNew) { this.isNew = isNew; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}
