
package Daos;

import Dtos.BookLoaned;
import Dtos.BookStock;
import Daos.BookStockDao;
import java.util.ArrayList;

/**
 *
 * Ben And Chris
 * @author ben
 */
public interface BookLoanedDaoInterface {
    
    public ArrayList<BookLoaned>getAllBooksOnLoan(BookStock book);
    /**
     * @param book
     * @return
     */
    //return the books out on loan
    public boolean BorrowABook(BookStock book, int userID);
    /**
     *
     * @param bookName
     * @return
     */
    
}
