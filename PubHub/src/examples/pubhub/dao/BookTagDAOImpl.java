package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class BookTagDAOImpl implements BookTagDAO {
	
	Connection connection = null;
	PreparedStatement stmt = null; 
	

	@Override
	public boolean addTag(Book book, Tag tag) {
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES(?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag.getTagName());
			
			if(stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeTag(Book book, Tag tag) {
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "DELETE book_tags WHERE isbn_13=? AND tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag.getTagName());
			
			if(stmt.executeUpdate() != 0) 
				return true;
			else
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public List<Tag> getTagsForBook(Book book) {
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Tag tag = new Tag();
				tag.setTagName(rs.getString("tag_name"));
				tags.add(tag);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}

	@Override
	public List<Book> getBooksWithTag(Tag tag) {
		
		List<Book> books = new ArrayList<>();
		
		try {
			
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books WHERE isbn_13 IN("
					+ "SELECT isbn_13 FROM book_tags WHERE tag_name=?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getTagName());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		

		return books;
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
