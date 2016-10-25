package Dtos;

import java.util.Objects;

/**
 *
 * Ben And Chris
 * @author Ben
 */


public class Users implements Comparable<Users> {
    private int user_id;
    private String username;
    private String password;
    private String Fname;
    private String Lname;
    private String Address;
    private String email;
    private int booksLoaned;
    private int Admin;

    public Users() {
    }

    public Users(String username, String password, String Fname, String Lname, String Address, String email, int booksLoaned, int Admin) {

        this.username = username;
        this.password = password;
        this.Fname = Fname;
        this.Lname = Lname;
        this.Address = Address;
        this.email = email;
        this.booksLoaned = booksLoaned;
        this.Admin = Admin;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return email;
    }

    public int getBooksLoaned() {
        return booksLoaned;
    }

    public int getAdmin() {
        return Admin;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBooksLoaned(int booksLoaned) {
        this.booksLoaned = booksLoaned;
    }

    public void setAdmin(int Admin) {
        this.Admin = Admin;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.user_id;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "user_id= " + user_id + " username= " + username + " password= " + password + " Fname= " + Fname + " Lname= " + Lname + " Address= " + Address + " email= " + email + " booksLoaned= " + booksLoaned + " Admin= " + Admin;
    }

    @Override
    public int compareTo(Users o) {
        if(this.user_id > o.user_id)
        {
            return +1;
        }
        if(this.user_id < o.user_id)
        {
            return -1;
        }
        else 
            return 0;
    }
    
    
    
}
