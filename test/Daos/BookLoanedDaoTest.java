/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.BookLoaned;
import Dtos.BookStock;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class BookLoanedDaoTest {
    
    public BookLoanedDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllBooksOnLoan method, of class BookLoanedDao.
     */
    @Test
    public void testGetAllBooksOnLoan() {
        System.out.println("getAllBooksOnLoan");
        BookStock book = null;
        BookLoanedDao instance = null;
        ArrayList<BookLoaned> expResult = null;
        ArrayList<BookLoaned> result = instance.getAllBooksOnLoan(book);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of BorrowABook method, of class BookLoanedDao.
     */
    @Test
    public void testBorrowABook() {
        System.out.println("BorrowABook");
        BookStock book = null;
        int userID = 0;
        BookLoanedDao instance = null;
        boolean expResult = false;
        boolean result = instance.BorrowABook(book, userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
