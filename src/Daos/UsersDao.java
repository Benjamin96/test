
package Daos;

/**
 *
 * @author Ben
 */
import Dtos.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends Dao implements UsersDaoInterface{

    public UsersDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<Users> getAllUsers() {
       Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllUsers() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public ArrayList<Users> getAllStandardUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users where Admin = false";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllStandardUsers() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllStandardUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public ArrayList<Users> getAllAdminUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users Where Admin = true";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllAdminUsers() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllAdminUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public boolean RegistorUser(Users u) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "Insert into users values(?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFname());
            ps.setString(4, u.getLname());
            ps.setString(5, u.getAddress());
            ps.setString(6, u.getEmail());
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            

                       
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the RegistorUser() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the RegistorUser() method");
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
    public Users LogingInUser(String name, String password) {
       Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        
        try{
            con = getConnection();

            String query = "SELECT * from users Where username = ? AND password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
            u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
            }
 
        }catch (SQLException e) {
            System.out.println("Exception occured in the LogingInUser() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the RemoveUser() method");
                e.getMessage();
                
            }
        }
        return u;
    }
    

    @Override
    public Users getUserbyName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try{
            con = getConnection();

            String query = "Select * from users Where username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getUserbyName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getUserbyName() method: " + e.getMessage());
            }
        }
        
        return u;
    }
    
    public Users getUSerbyId(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try{
            con = getConnection();

            String query = "Select * from users Where userid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getUserbyName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getUserbyName() method: " + e.getMessage());
            }
        }
        
        return u;
    }

    @Override
    public ArrayList<Users> getUserContaingName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users Where username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getUserContaingName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getUserContaingName() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public boolean RemoveUser(int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "DELETE FROM users Where userID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            
            

                       
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the RemoveUser() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the RemoveUser() method");
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

    
}
