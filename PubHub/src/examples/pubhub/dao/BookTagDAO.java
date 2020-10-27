package examples.pubhub.dao;
import java.util.*;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
/**
 * Interface for our Data Access Object to handle database queries related to book tags.
 */
public interface BookTagDAO {
	public boolean addTag(Book book, Tag tag);
	public boolean removeTag(Book book, Tag tag);
	
	public List<Tag> getTagsForBook(Book book);
	public List<Book> getBooksWithTag(Tag tag);
}
