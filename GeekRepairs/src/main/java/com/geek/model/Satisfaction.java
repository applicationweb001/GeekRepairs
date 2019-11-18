package com.geek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.geek.dateAudit.DateAudit;

@Entity
@Table(name="satisfaction")
public class Satisfaction extends DateAudit{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="satisfaction_id")
private long id;

@NotEmpty(message="Ingrese su nivel de satisfacción")
@Column(name="experience")
private String experience;

@NotEmpty(message="Ingrese su nivel de satisfacción")
@Column(name="time")
private String time;

@NotEmpty(message="Ingrese su nivel de satisfacción")
@Column(name="professionalism")
private String professionalism;

@NotEmpty(message="Ingrese su nivel de satisfacción")
@Column(name="quality")
private String quality;

@NotEmpty(message="Ingrese su nivel de satisfacción")
@Column(name="compression")
private String compression;


@OneToOne
@JoinColumn(name="ticket_id",updatable=false, nullable=false)
private Ticket ticket;

public Satisfaction() {
	this.setCreatedAt(new Date());
	this.setUpdatedAt(new Date());
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getExperience() {
	return experience;
}

public void setExperience(String experience) {
	this.experience = experience;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getProfessionalism() {
	return professionalism;
}

public void setProfessionalism(String professionalism) {
	this.professionalism = professionalism;
}

public String getQuality() {
	return quality;
}

public void setQuality(String quality) {
	this.quality = quality;
}

public String getCompression() {
	return compression;
}

public void setCompression(String compression) {
	this.compression = compression;
}

public Ticket getTicket() {
	return ticket;
}

public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}



}
