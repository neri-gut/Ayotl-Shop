package shop.ayotl.backend.model.customization;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import shop.ayotl.backend.model.Product;

@Entity
@Table(name = "customizations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @Column(name = "fragrance")
    private ProductFragrance fragrance;

    @NotNull
    @Column(name = "presentation")
    private ProductPresentation presentation;
}
