package shop.ayotl.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import shop.ayotl.backend.model.order.Order;

import java.math.BigDecimal;

@Entity
@Table(name = "product_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;
}
