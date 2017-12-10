package com.app.exchange.domain;

import javax.persistence.*;

@Entity
public class DetailAnnonce {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name="annonce_id")
    private Annonce annonce;
    @OneToOne
    @JoinColumn(name="bien_id")
    private Bien bien;


}
