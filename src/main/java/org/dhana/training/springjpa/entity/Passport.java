package org.dhana.training.springjpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class Passport extends BaseEntity{
    @NonNull
    @Column(name = "passport_number", nullable = false, unique = true)
    private String passportNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @OneToOne(mappedBy = "passport")
    private Employee employee;
}
