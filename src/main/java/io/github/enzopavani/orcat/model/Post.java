package io.github.enzopavani.orcat.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "post", schema = "public")
@Data
@ToString(exclude = "originalAuthor")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @CreatedDate
    @Column(name = "post_birthdate")
    private LocalDate postBirthdate;

    @LastModifiedDate
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private Author originalAuthor;

}
