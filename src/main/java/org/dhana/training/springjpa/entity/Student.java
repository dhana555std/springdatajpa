package org.dhana.training.springjpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "students")
public class Student extends BaseEntity {
    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
}

