package org.dhana.training.springjpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode()
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Department extends BaseEntity{
    @NonNull
    @Column(name = "department_name", nullable = false, unique = true)
    private String department_name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Employee> employee;
}
