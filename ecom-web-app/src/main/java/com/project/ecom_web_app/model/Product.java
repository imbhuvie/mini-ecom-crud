package com.project.ecom_web_app.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
//    To change the date formate to dd-mm-yyyy.
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy")
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;
    @Lob //for large object as Image.
    @Column(length = 1000)
    private byte[] imageDate;
    private String imageName;
    private String imageType;

}
