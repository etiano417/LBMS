package VisitorRegistry;

import BookRegistry.Book;
import Transaction.Borrow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to test the visitor registry subsystem
 */
public class VisitorRegistryTest {
    public static void main(String[] args){

        VisitorRegistry vr = new VisitorRegistry();

        //Test visitor registration
        assert !vr.RegisterVisitor("Eric","Tino","1234","1234").equals("duplicate");
        assert vr.RegisterVisitor("Eric","Tino","1234","1234").equals("duplicate");

        //Test visitor arrival
        String id = vr.RegisterVisitor("John","Doe","4444","5555");
        assert vr.beginVisit(id, LocalDateTime.now()).equals("success");
        assert vr.beginVisit("notanid", LocalDateTime.now()).equals("invalid id");
        assert vr.beginVisit(id, LocalDateTime.now()).equals("duplicate");

        //Test undo visitor arrival
        vr.beginVisit(id, LocalDateTime.now());
        assert vr.undoBeginVisit(id).equals("success");

        //test isVisiting()
        String id2 = vr.RegisterVisitor("Some","Guy","4444","5555");
        vr.beginVisit(id2, LocalDateTime.now());
        assert vr.isVisiting(id2);
        assert !vr.isVisiting("notanid");

        //test ending visits
        String id3 = vr.RegisterVisitor("Yngwie","Malmsteen","4444","5555");
        vr.beginVisit(id3, LocalDateTime.now());
        assert vr.endVisit(id3,LocalTime.now()).equals("success");
        assert vr.endVisit("notanid",LocalTime.now()).equals("invalid id");

        //Test undoing an end visit by creating a few visits and making sure the most recent one is undone
        vr.beginVisit(id3, LocalDateTime.now().minusDays(5));
        vr.endVisit(id3, LocalTime.now());

        vr.beginVisit(id3, LocalDateTime.now());
        vr.endVisit(id3, LocalTime.now().plusHours(2));
        assert vr.undoEndVisit(id3).equals("success");

        //Test borrowing books
        List<String> authors = new ArrayList<>();
        authors.add("yes");
        Borrow b = new Borrow(new Book(123,"yes",authors,"yes", LocalDate.now(),100),LocalDate.now());
        String id4 = vr.RegisterVisitor("King","Diamond","4444","5555");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook("notanid",b).equals("invalid id");

        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("book limit exceeded");

        //Test returning book
        assert vr.returnBook(id4,b).equals("success");
        assert vr.returnBook("notanid",b).equals("invalid id");

        //Test owes money by creating an a visitor and having them return an overdue book
        boolean owesMoney = false;
        String id5 = vr.RegisterVisitor("Matt","Damon", "1234", "1234");
        Borrow b2 = new Borrow(new Book(123,"yes",authors,"yes", LocalDate.now(),100),LocalDate.now());
        vr.borrowBook(id5, b2);
        for (int i = 0; i < 10; i++) {
            b2.advanceDay();
        }
        vr.returnBook(id5,b2);
        for (Visitor v : vr.getVisitors()) {
            if (v.getVisitorID().equals(id5) && v.getAmountOwed() > 0) {
                owesMoney = true;
            }
        }
        assert owesMoney;

        //Test paying a fine
        assert vr.payFine(id5, -5).equals("invalid-amount");
        assert vr.payFine(id5, 500).equals("invalid-amount");
        assert vr.payFine(id5, 10).equals("success");
    }
}
