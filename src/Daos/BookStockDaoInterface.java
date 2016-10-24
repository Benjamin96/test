
package Daos;

/**
 *
 * Ben And Chris
 * @author Chris
 */
import Dtos.BookStock;
import Dtos.BookStock;
import Dtos.BookStock;
import java.util.ArrayList;

public interface BookStockDaoInterface {
    public ArrayList<BookStock>getAllBooks();
    //return all books
    public BookStock getABookByName(String bookName);
    //getting a book by its name
    public BookStock getABookById(int id);
    //getting a book by its id
    public ArrayList<BookStock>getABookContainingName(String bookName);
    //getting a book by its name wildcard
    public boolean ReturnABook(String bookName);
    //return a book (should change copies in databasee)
    public boolean takeOutABook(int bookID);
    
    //take out a book decreasing the copies
    
    public boolean RemoveABook(int bookID);
    //should remove a book from libary (record compeltly removed)(admin only)
    public boolean AddingABook(BookStock b);
    //Adding a book to database
    public boolean EditingABook(int bookID);
    //admin should be able to edit a record(admin only)
    public boolean IncreasingCopiesBook(int bookID);
    //admin increses the book copies(admin only)
    public boolean DescresingCopiesBook(int bookId);
    //admin decreses number of book copies(admin only)
    
}
