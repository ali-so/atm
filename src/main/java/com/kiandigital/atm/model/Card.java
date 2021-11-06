package com.kiandigital.atm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "atm_card")
public class Card {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_no", nullable = false, length = 16)
    private String cardNo;

    @Column(name = "pin", length = 300)
    private String pin;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked;

    @OneToOne(mappedBy = "card")
    private Account account;

}
