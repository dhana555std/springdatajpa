package org.dhana.training.springjpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
public class Course extends BaseEntity {
    @NonNull
    @Column(name = "course_name", nullable = false, unique = true)
    private String courseName;

    @ManyToMany(mappedBy = "courses", cascade = ALL, fetch = LAZY)
    private List<Employee> employees;
}
