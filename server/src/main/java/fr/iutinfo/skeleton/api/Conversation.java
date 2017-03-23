package fr.iutinfo.skeleton.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ConversationDto;

public class Conversation implements Principal {
	final static Logger logger = LoggerFactory.getLogger(User.class);
	private String name;
	private int demandeurId;
	private int conseilleId;
	private boolean termine;
	private int note;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public int getDemandeurId() {
		return demandeurId;
	}

	public void setDemandeurId(int demandeurId) {
		this.demandeurId = demandeurId;
	}

	public int getConseilleId() {
		return conseilleId;
	}

	public void setConseilleId(int conseilleId) {
		this.conseilleId = conseilleId;
	}

	public boolean isTermine() {
		return termine;
	}

	public void setTermine(boolean termine) {
		this.termine = termine;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
	
	public void initFromDto(ConversationDto dto){
		this.setConseilleId(dto.getConseilleId());
		this.setDemandeurId(dto.getDemandeurId());
		this.setName(dto.getName());
		this.setNote(dto.getNote());
		this.setTermine(dto.isTermine());
	}
	
	public ConversationDto convertToDto(){
		ConversationDto dto = new ConversationDto();
		dto.setConseilleId(this.getConseilleId());
		dto.setDemandeurId(this.getDemandeurId());
		dto.setName(this.getName());
		dto.setNote(this.getNote());
		dto.setTermine(this.isTermine());
		return dto;
	}

}
