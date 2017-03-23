package fr.iutinfo.skeleton.common.dto;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversationDto implements Principal {
    final static Logger logger = LoggerFactory.getLogger(UserDto.class);
	private int demandeurId;
	private int conseilleId;
	private boolean termine;
	private int note;

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

	public String getName() {
		return "conversation";
	}

}
