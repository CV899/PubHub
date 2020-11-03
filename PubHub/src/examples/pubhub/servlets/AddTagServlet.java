package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class AddTagServlet
 */
@WebServlet("/AddTag")
public class AddTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddTagServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess = false;
		String isbn13 = request.getParameter("isbn13");
		String tag = request.getParameter("newTag");
		BookDAO bookDAO = DAOUtilities.getBookDAO();
		Book book = bookDAO.getBookByISBN(isbn13);
		BookTagDAO tagDAO = DAOUtilities.getBookTagDAO();
		Tag bookTag = new Tag(tag);
		
		isSuccess = tagDAO.addTag(book, bookTag);
		
		if(isSuccess) {
			request.getSession().setAttribute("message", "Tag successfully added");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "There was a problem adding this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
