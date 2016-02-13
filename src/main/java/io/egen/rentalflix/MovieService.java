package io.egen.rentalflix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service implementing IFlix interface
 * You can use any Java collection type to store movies
 */
public class MovieService implements IFlix {
	
	private static ConcurrentHashMap<String, Movie> movieDatabase = null;
	private static MovieService instance = null;
	
	static {
		movieDatabase = new ConcurrentHashMap<String,Movie>();
		instance = new MovieService();
	}
	
	private MovieService() {
		
	}
	
	public static MovieService getInstance() {
		return instance;
	}

	@Override
	public List<Movie> findAll() {
		return new ArrayList<Movie>(movieDatabase.values());
	}

	@Override
	public List<Movie> findByName(String name) {
		
		List<Movie> movies = new ArrayList<Movie>();
		for(String key : Collections.list(movieDatabase.keys())) {
			if(movieDatabase.get(key).getTitle().equals(name)) {
				movies.add(movieDatabase.get(key));
			}
		}
		
		return movies;
	}

	@Override
	public Movie create(Movie movie) {
		Movie realMovie = new Movie(movie.getTitle(),movie.getYear(),movie.getLanguage());
		movieDatabase.put(realMovie.getId(), realMovie);
		return realMovie;
	}

	@Override
	public Movie update(Movie movie) {
		
		if(movieDatabase.containsKey(movie.getId())) {
			movieDatabase.put(movie.getId(), movie);
		}
		else
			throw new IllegalArgumentException();
		
		return movie;
	}

	@Override
	public Movie delete(String id) {
		
		Movie movie = movieDatabase.get(id);
		
		if(!movieDatabase.containsKey(id))
			throw new IllegalArgumentException();
		else
			movieDatabase.remove(id);
		return movie;
	}

	@Override
	public boolean rentMovie(String movieId, String user) {
		
		if(movieDatabase.get(movieId).isRented() || !movieDatabase.containsKey(movieId))
			throw new IllegalArgumentException();
		else {
			movieDatabase.get(movieId).setRented(true);			
		}			
		return true;
	}
	
	public void wipeData() {
		movieDatabase = new ConcurrentHashMap<String, Movie>();
	}
}
