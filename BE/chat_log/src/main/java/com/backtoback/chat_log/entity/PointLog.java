package com.backtoback.chat_log.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "POINTLOGS")
public class PointLog implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identity로 하면 디비엔진에 따라 오토 인크리먼트가 안먹는다.
	@Column(name = "point_log_seq")
	private Long pointLogSeq;

	@Column(name = "point", nullable = false)
	private Integer point;

	@Column(name = "point_detail", nullable = false)
	@Enumerated(EnumType.STRING)
	private PointDetailType pointDetail;

	@CreatedDate
	// @Column(name = "time", nullable = false)
	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", columnDefinition = "TIMESTAMP", nullable = false)
	private LocalDateTime time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_seq", nullable = false)
	private Member member;

}
