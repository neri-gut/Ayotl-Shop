package shop.ayotl.backend.model.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import shop.ayotl.backend.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "date")
    @CreatedDate
    private LocalDate date;

    @NotNull
    @Column(name = "total")
    private BigDecimal total;

    @NotNull
    @Column(name = "status")
    private OrderStatus status;
}
