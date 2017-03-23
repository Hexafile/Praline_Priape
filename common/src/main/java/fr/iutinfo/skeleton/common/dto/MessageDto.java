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

	public void setDate(java.sql.Date date) {
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

}
