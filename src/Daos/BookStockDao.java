package Daos;

/**
 *
 * @author Chris
 */
import Dtos.BookStock;
import Daos.BookStockDaoInterface;
import Dtos.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class BookStockDao extends Dao implements BookStockDaoInterface {

    public BookStockDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<BookStock> getAllBooks() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookStock> bookStock = new ArrayList();
        
        try
        {
            con = getConnection();
            
            String query = "Select * from bookstock";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                BookStock book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
                bookStock.add(book);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllBooks() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllBooks() method: " + e.getMessage());
            }
    }
        return bookStock;
}

    @Override
    public BookStock getABookByName(String bookName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookStock book = null;
        try
        {
            con = getConnection();
            
            String query = "Select * from bookstock where bookName = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, bookName);
            rs = ps.executeQuery();
            while(rs.next())
            {
                book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
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
                System.out.println("Exception occured in the finally section of the getABookByName() method: " + e.getMessage());
            }
    }
        return book;
    }
    
    @Override
    public BookStock getABookById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookStock book = null;
        try
        {
            con = getConnection();
            
            String query = "Select * from bookstock where bookID = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
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
                System.out.println("Exception occured in the finally section of the getABookByName() method: " + e.getMessage());
            }
    }
        return book;
    }

    @Override
    public ArrayList<BookStock> getABookContainingName(String bookName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookStock> bookStock = new ArrayList();
        try
        {
            con = getConnection();
            
            String query = "Select * from bookStock where bookName LIKE ? ";
            ps = con.prepareStatement(query);
            ps.setString(1,"%" +  bookName + "%");
            rs = ps.executeQuery();
            while(rs.next())
            {
                BookStock book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
                bookStock.add(book);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getABookContainingName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getABookContainingName() method: " + e.getMessage());
            }
    }
        return bookStock;
    }

    @Override
    public boolean ReturnABook(String bookName) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "UPDATE bookstock set copies + 1 WHERE bookName = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, bookName);
         
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the EditingABook() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the EditingABook() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean takeOutABook(int bookID)
    {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "UPDATE bookstock set copies - 1 where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the decreasingCopies() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the decreasingCopies() Member method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean AddingABook(BookStock b) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "Insert into bookStock values(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, b.getBookID());
            ps.setString(2, b.getBookName());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getPublisher());
            ps.setInt(5, b.getCopies());
       
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the AddingABook() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the AddingABook() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    /*
        Admin controlled methods
    */
    @Override
    public boolean EditingABook(int bookID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "UPDATE bookstock set bookID WHERE bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
         
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the EditingABook() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the EditingABook() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean IncreasingCopiesBook(int bookID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "UPDATE bookstock set copies + 1 where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the increasingCopiesAdmin() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the IncreasingCopiesAdmin() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean DescresingCopiesBook(int bookId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();
            //Updates the copies to lower "set copies -1" 
            String query = "UPDATE bookstock set copies - 1 where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the decreasingCopiesAdmin() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the decreasingCopies() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }
    @Override
    public boolean RemoveABook(int bookID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        try
        {
            con = getConnection();
            
            String query = "DELETE FROM bookStock where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
            rowsAffected = ps.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the removeABook() method: " + e.getMessage());
        } finally {
            try {
                
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the removeABook() method: " + e.getMessage());
            }
    }
        if(rowsAffected < 0)
        {
            return true;
        }
        else
            return false;
    }
}
