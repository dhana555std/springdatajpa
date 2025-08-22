package org.dhana.training.springjpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.dhana.training.springjpa.utils.EmployeeStatus.EmployeeStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "employees", schema = "training",
        indexes = {
            @Index(name = "idx_gender", columnList = "gender"),
        },
        uniqueConstraints = {
            @UniqueConstraint(name = "uc_employee_email", columnNames = {"email"}),
            @UniqueConstraint(name = "uc_employee_name", columnNames = {"name"})
        })
public class Employee extends BaseEntity{
    @NonNull
    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false, columnDefinition = "char(1)")
    private Character gender;

    @NonNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NonNull
    @Column(name = "salary", nullable = false)
    private long salary;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passport_number", referencedColumnName = "passport_number",
                unique = true, foreignKey = @ForeignKey(name = "fk_employee_passport"))
    @ToString.Exclude
    private Passport passport;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_name", referencedColumnName = "department_name",
                foreignKey = @ForeignKey(name = "fk_employee_department"))
    @ToString.Exclude
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_courses",
               joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"),
               inverseJoinColumns = @JoinColumn(name = "course_name", referencedColumnName = "course_name"),
               foreignKey = @ForeignKey(name = "fk_employee_course"),
               inverseForeignKey = @ForeignKey(name = "fk_course_employee"))
    @ToString.Exclude
    private List<Course> courses;
}
