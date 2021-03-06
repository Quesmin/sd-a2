package sd.a2.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString
@Getter
@Setter
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(
            name = "id"
    )

    private String id;
    private String email;
    private String passwordHash;

    public User(String id) {
        this.id = id;
    }

    public User(String id, String email, String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
