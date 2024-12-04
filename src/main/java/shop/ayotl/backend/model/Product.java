package shop.ayotl.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @NotNull
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_mime_type")
    private String imageMimeType;

    @NotNull
    @Setter(AccessLevel.NONE)
    private LocalDate createdAt;

    @NotNull
    @Setter(AccessLevel.NONE)
    private LocalDate updatedAt;

    @PrePersist
    private void fillDates() {
        final var today = LocalDate.now();

        if (createdAt == null) {
            this.createdAt = today;
        }

        this.updatedAt = today;
    }
}
