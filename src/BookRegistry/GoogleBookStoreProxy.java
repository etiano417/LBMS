package BookRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  Handles interaction with text file book store.
 */
public class GoogleBookStoreProxy implements BookStore{

    String url;
    HttpURLConnection con;
    URL GoogleURL;

    public GoogleBookStoreProxy(File file){
        //String url = ""; //??

        // Create a URL and open a connection
        //URL GoogleURL = new URL(url);
        //HttpURLConnection con = (HttpURLConnection) GoogleURL.openConnection();
    }

    /**
     * Search the Google bookstore for a book title containing the passed in string
     */
    public List<Book> search(String name) {
        String query = getQuery(name);
        return null;
    }

    /**
     * Search the Google bookstore for a book title containing the passed in string and author list.
     * If the title string was passed in as an '*' search only for authors.
     */
    public List<Book> search(String name, List<String> authors) {
        String searchAuthors = "";
        for(String a: authors)
            searchAuthors+=a;
        searchAuthors.replaceAll(" ", "+");

        String query = getQuery(name+"+inauthor:"+searchAuthors);  //would only search the first author
        return null;
    }

    /**
     * Search the Google bookstore for a book title containing the passed in string, author list, and isbn.
     * If the title string was passed in as an '*' search only for authors and isbn.
     * If the title string and authors list were passed in as an '*' search only for isbn.
     */
    public List<Book> search(String name, List<String> authors, long isbn){
        String searchAuthors = "";
        for(String a: authors)
            searchAuthors+=a;
        searchAuthors.replaceAll(" ", "+");

        String query = getQuery(name+"+inauthor:"+searchAuthors+"+isbn:"+isbn);
        return null;
    }

    private String getQuery(String searchTerms){
        return "GET https://www.googleapis.com/books/v1/volumes?q=" + searchTerms + "&printType=books&key=AIzaSyATE9oDiwwAFevQkNF428GLMvoIYIestrg";
    }
}
