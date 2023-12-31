package com.backtoback.business.team.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "TEAMS")
public class Team implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_seq")
	private Long teamSeq;

	@Column(name = "team_name", nullable = false, length = 30, unique = true)
	private String teamName;

	@Column(name = "team_code", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private TeamCode teamCode;

}
