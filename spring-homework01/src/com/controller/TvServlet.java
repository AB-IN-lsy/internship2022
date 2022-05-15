package com.controller;

import com.dao.ITvDao;

public class TvServlet {
	private ITvDao dao;
	
	public void setDao(ITvDao dao) {
		this.dao = dao;
	}
	
	public void service() {
		dao.turnOn();
		dao.play();
		dao.turnOff();
	}
	
}
