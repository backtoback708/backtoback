package com.backtoback.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name = "teams")
=======
@Table(name = "TEAMS")
>>>>>>> d3e242c9852359e36e08f18e14d7f969c3bdb292
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity로 하면 디비엔진에 따라 오토 인크리먼트가 안먹는다.
    @Column(name = "team_seq")
    private Long teamSeq;

    @Column(name = "team_name", nullable = false, length = 30, unique = true)
    private String teamName;

}
