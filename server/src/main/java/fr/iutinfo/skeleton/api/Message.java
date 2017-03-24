package fr.iutinfo.skeleton.api;

import java.security.Principal;

import fr.iutinfo.skeleton.common.dto.MessageDto;

public class Message implements Principal {
	int idConv;
	int idWriter;
	private java.util.Date date;
	String msg;
	
	public Message(int i,String string) {
		idWriter=i;
		date=new java.util.Date();
		msg=string;
	}
	
	public Message() {}

	public int getIdConv() {
		return idConv;
	}

	public void setIdConv(int idConv) {
		this.idConv = idConv;
	}

	public int getIdWriter() {
		return idWriter;
	}

	public void setIdWriter(int idWriter) {
		this.idWriter = idWriter;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getName() {
		return "message";
	}
	
	public void initFromDto(MessageDto dto){
		this.setIdConv(dto.getIdConv());
		if(dto.getDate()!=null){
			this.setDate(new java.util.Date(dto.getDate().getTime()));
		}
		this.setIdWriter(dto.getIdWriter());
		this.setMsg(dto.getMsg());
	}
	
	public MessageDto convertToDto(){
		MessageDto dto = new MessageDto();
		dto.setDate(date);
		dto.setIdConv(idConv);
		dto.setIdWriter(idWriter);
		dto.setMsg(msg);
		return dto;
	}

}
