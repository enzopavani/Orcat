package io.github.enzopavani.orcat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author", schema = "public")
@Data
@ToString(exclude = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Email
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Past
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "originalAuthor", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @CreatedDate
    @Column(name = "account_birthdate")
    private LocalDate accountBirthdate;

    @LastModifiedDate
    @Column(name = "last_update")
    private LocalDate lastUpdate;
}
