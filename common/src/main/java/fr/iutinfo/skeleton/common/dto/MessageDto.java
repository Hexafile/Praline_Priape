package fr.iutinfo.skeleton.common.dto;

import java.security.Principal;

public class MessageDto implements Principal{
	int idConv;
	int idWriter;
	private java.sql.Date date;
	String msg;
	
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

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = new java.sql.Date(date.getTime());
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

}
