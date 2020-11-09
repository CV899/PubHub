package examples.pubhub.utilities;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookDAOImpl;
import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.dao.BookTagDAOImpl;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class TestTagDAO {

	public static void main(String[] args) {
		
		BookTagDAO tagDAO = new BookTagDAOImpl();
		BookDAO bookDAO = new BookDAOImpl();
		
		byte[] content = {2, 4, 5};
		LocalDate date = LocalDate.now();
		
		//Book book = new Book("1111111111113", "Test Book", "Dr. Test", content, date);
		
		//bookDAO.addBook(book);
		
		Tag tag = new Tag("fiction");
		
		//tagDAO.addTag(book, tag);
		Book book = bookDAO.getBookByISBN("1111111111111");
		List<Tag> tags = tagDAO.getTagsForBook(book);
		
		for(int i = 0; i < tags.size(); i++) {
			Tag b = tags.get(i);
			System.out.println(b);
		}
		

	}

}
