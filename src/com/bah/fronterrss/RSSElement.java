package com.bah.fronterrss;

public class RSSElement {
	
	String title;
	String date;
	String author;
	String roomName;
	String descripton;
	
	public RSSElement() {
		super();
		this.setTitle(title);
		this.setDate(date);
		this.setAuthor(author);
		this.setRoomName(roomName);
		this.setDescripton(descripton);
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

}
