package com.dao.impl;

import com.dao.ITvDao;

public class TvDaoImplHaixin implements ITvDao {

	@Override
	public void turnOn() {
		System.out.println("Turned On");
	}

	@Override
	public void play() {
		System.out.println("Playing");
	}

	@Override
	public void turnOff() {
		System.out.println("Turned Off");
	}
	
}
