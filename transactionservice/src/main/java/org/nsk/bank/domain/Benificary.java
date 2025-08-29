package org.nsk.bank.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Benificary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long benificaryId;
    private long customerAccountNumber;
    private String benificaryName;
    private long benificaryAccountNumber;
    private String benificaryBankName;
    private String isActive;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

}
