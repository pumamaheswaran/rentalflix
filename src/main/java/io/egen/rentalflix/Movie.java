package io.egen.rentalflix;

import java.util.UUID;

/**
 * Entity representing a movie.
 * Fields: id, title, year, language
 */
public class Movie {
	
	private String id;
	private String title;
	private int year;
	private String language;
	private boolean rented;
	
	public Movie(String title, int year, String language) {
		super();
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.year = year;
		this.language = language;
		this.rented = false;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}	
}
