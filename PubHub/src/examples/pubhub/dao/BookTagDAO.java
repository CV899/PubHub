package examples.pubhub.dao;
import java.util.*;

import examples.pubhub.model.Book;
/**
 * Interface for our Data Access Object to handle database queries related to book tags.
 */
public interface BookTagDAO {
	public boolean addTag(String isbn, String tagName);
	public boolean removeTag(String isbn, String tagName);
	
	public List<String> getTags(String isbn);
	public List<Book> getBooksWithTag(String tagName);
}
