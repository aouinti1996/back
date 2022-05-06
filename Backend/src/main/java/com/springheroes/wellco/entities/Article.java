package com.springheroes.wellco.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    @NotBlank(message = "The Article Name cannot be empty or Null")
    private String articleName;
    @Nullable
    private String url1;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> sharers;
}
