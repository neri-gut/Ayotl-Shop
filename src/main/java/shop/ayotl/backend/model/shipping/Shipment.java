package shop.ayotl.backend.model.shipping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import shop.ayotl.backend.model.order.Order;

import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "status")
    private ShipmentStatus status;
}
