package fr.iutinfo.skeleton.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conversation implements Principal {
	final static Logger logger = LoggerFactory.getLogger(User.class);
	
	private int demandeurId;
	private int conseilleId;
	private boolean termine;
	private int note;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
