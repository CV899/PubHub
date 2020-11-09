package examples.pubhub.servlets;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;


@WebServlet("/SearchTag") 
public class SearchTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("searchTags.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tag = request.getParameter("tag");
		BookDAO bookDAO = DAOUtilities.getBookDAO();
		BookTagDAO tagDAO = DAOUtilities.getBookTagDAO();
		Tag bookTag = new Tag(tag);
		
		List<Book> books = tagDAO.getBooksWithTag(bookTag);
		
		if(books.size() == 0) {
			request.getSession().setAttribute("message", "No books found with tag: " + tag);
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("searchTags.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("books", books);
			request.getRequestDispatcher("searchTags.jsp").forward(request, response);
		}
		
		
	}

}
