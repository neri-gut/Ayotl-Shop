package shop.ayotl.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "users_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "second_last_name")
    private String secondLastName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
