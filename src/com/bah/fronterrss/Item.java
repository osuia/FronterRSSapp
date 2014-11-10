package com.bah.fronterrss;

public class Item {
	private String title;
	private String date;
	private String room;
	
	public Item(String title, String date, String room) {
		super();
		this.setTitle(title);
		this.setDate(date);
		this.setRoom(room);
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
