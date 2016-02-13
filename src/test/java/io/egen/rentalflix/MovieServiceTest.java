package io.egen.rentalflix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * JUnit test cases for MovieService
 */
public class MovieServiceTest {
	
	private static MovieService service = null;
	
	@BeforeClass
	public static void setUp() {
		service = MovieService.getInstance();
	}
	
	@Before
	public void addMovies() {
		
		for(int i = 0 ; i < 2 ; i++) {
			Movie movie = new Movie("Deadpool",2016,"English");
			Movie returnMovie = service.create(movie);
			
			assertNotNull(movie.getId());
			assertEquals(movie.getLanguage(), returnMovie.getLanguage());
			assertEquals(movie.getTitle(), returnMovie.getTitle());
			assertEquals(movie.getYear(), returnMovie.getYear());
		}
		
	}
	
	@After
	public void wipeData() {
		service.wipeData();
	}
	
	@Test
	public void testGetInstance() {		
		//Singleton check
		service = MovieService.getInstance();
		assertEquals(service, MovieService.getInstance());	
	}
	
	@Test
	public void testFindAll() {
		List<Movie> movies = service.findAll();
		assertEquals(2, movies.size());
	}
	
	@Test
	public void testCreate() {
		
		for(int i = 0 ; i < 2 ; i++) {
			Movie movie = new Movie("ABC",2016,"English");
			Movie returnMovie = service.create(movie);
			
			assertNotNull(movie.getId());
			assertEquals(movie.getLanguage(), returnMovie.getLanguage());
			assertEquals(movie.getTitle(), returnMovie.getTitle());
			assertEquals(movie.getYear(), returnMovie.getYear());
		}		
	}
	
	@Test
	public void testFindByName() {
		List<Movie> movies = service.findByName("Deadpool");
		
		assertEquals(2, movies.size());
		for(Movie movie : movies) {
			assertEquals("Deadpool", movie.getTitle());
		}		
	}
	
	@Test
	public void testUpdate() {
		List<Movie> movies = service.findByName("Deadpool");
		
		for(Movie movie : movies) {
			movie.setTitle("ABC");
			service.update(movie);
		}
		
		movies = service.findByName("ABC");
		assertEquals(2, movies.size());
		
		movies = service.findByName("Deadpool");
		assertEquals(0, movies.size());
		
		try {
			Movie movie = service.update(new Movie("Dummy value",2011,"Dummy value"));
			Assert.fail();
		}
		catch(Exception e) {
			assertEquals(true, e instanceof IllegalArgumentException);
		}
		
		
		
	}
	
	@Test
	public void testDelete() {
		List<Movie> movies = service.findByName("Deadpool");
		
		for(Movie movie : movies) {
			movie.setTitle("ABC");
			service.delete(movie.getId());
		}
				
		movies = service.findByName("Deadpool");
		assertEquals(0, movies.size());
		
		try {
			Movie movie = service.delete("-1");
			Assert.fail();
		}
		catch(Exception e) {
			assertEquals(true, e instanceof IllegalArgumentException);
		}
		
	}
	
	@Test
	public void testRentMovie() {
		List<Movie> movies = service.findByName("Deadpool");
		
		for(Movie movie : movies) {
			movie.setTitle("ABC");
			boolean success = 
					service.rentMovie(movie.getId(),"Pravin");
			assertEquals(true, success);
		}
		
		
		for(Movie movie : movies) {
			movie.setTitle("ABC");
			try {
				boolean success = 
					service.rentMovie(movie.getId(),"Pravin");
				Assert.fail();
			}
			catch (Exception e) {
				assertEquals(true, e instanceof IllegalArgumentException);
			}			
		}
	}
}
