package Daos;

import Dtos.BookLoaned;
import Dtos.BookStock;
import Dtos.Users;
import Daos.BookStockDao;
import Daos.UsersDao;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aleksander Matraszek
 */
public class BookLoanedDao extends Dao implements BookLoanedDaoInterface {

    public BookLoanedDao(String databaseName) {
        super(databaseName);
    }
    //expermenting
    

    @Override
    public ArrayList<BookLoaned> getAllBooksOnLoan(BookStock book) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookLoaned> bksLoaned = new ArrayList();
        BookStock bk = null;
        Users usr = null;
        int num = book.getBookID();
        BookStockDao bksDao = new BookStockDao("libraryca");
        UsersDao uDao = new UsersDao("libraryca");
        try {
            con = getConnection();

            String query = "Select * from bookloaned where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, num);
            rs = ps.executeQuery();

            while (rs.next()) {
                String title = book.getBookName();
                String uname = rs.getString("userID");
                bk = bksDao.getABookByName(title);
                int uid = Integer.parseInt(uname);
                usr = uDao.getUSerbyId(uid);
                
                BookLoaned bl = new BookLoaned(rs.getInt("loanID"), bk, usr);
                bksLoaned.add(bl);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllBooksOnLoan() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllBooksLoaned() method: " + e.getMessage());
            }
        }
        return bksLoaned;
    }

    @Override
    public boolean BorrowABook(BookStock book, int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try {
            con = getConnection();
            String query = "Insert into bookloaned (bookID, userID) values (?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, book.getBookID());
            ps.setInt(2, userID);
            rowsAffected = ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("Exception occured in the BorrowABook method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the BorrowABook() method: " + e.getMessage());
            }
        }
        if(rowsAffected >0 ){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public ArrayList<BookLoaned>getAllBooksOnLoanByUser(int id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookLoaned> loanedBook = new ArrayList();
        
        BookStock bk = null;
        Users usr = null;
        BookStockDao bksDao = new BookStockDao("libraryca");
        UsersDao uDao = new UsersDao("libraryca");
        try
        {
            con = getConnection();
            
            String query = "Select * from bookloaned where userID = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next())
            {
               String queryBookId = rs.getString("bookID");
               int bkid = Integer.parseInt(queryBookId);
               bk = bksDao.getABookById(bkid);
               
               usr = uDao.getUSerbyId(id);
               
               BookLoaned book = new BookLoaned(rs.getInt("loanID"), bk, usr);
               loanedBook.add(book);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getABookByName() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllBooksOnLoanByUser() method: " + e.getMessage());
            }
    }
        return loanedBook;
    }
    
    @Override
    public boolean RemovefromTable(BookStock book, int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();
            String query = "DELETE FROM bookLoaned WHERE bookId = ? AND userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, book.getBookID());
            ps.setInt(2, userID);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the ReturnABook method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the BorrowABook() method: " + e.getMessage());
            }
        }
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
}
