/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.krr.bookwebapp2.model;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kallie
 */
public class AuthorDao implements AuthorDaoStrategy {

//    private DBStrategy db;
//    private String driver;
//    private String url;
//    private String userName;
//    private String password;
    private DataSource ds;
    private DBStrategy db;
//    public AuthorDao(DBStrategy db, String driver, String url, String userName, String password){
//        this.db = db;
//        this.driver = driver;
//        this.url = url;
//        this.userName = userName;
//        this.password = password;
//    }

    public AuthorDao(DataSource ds, DBStrategy db){
        this.ds = ds;
        this.db = db;
    }
    public List<Author> getAllAuthors() throws Exception {
        db.openConnection(ds);
        List<Author> records = new ArrayList<>();

        List<Map<String, Object>> rawData = db.findAllRecords("author");
        for (Map rawRec : rawData) {
            Author author = new Author();
            Object obj = rawRec.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));

            String name = rawRec.get("author_name") == null ? "" : rawRec.get("author_name").toString();
            author.setAuthorName(name);

            obj = rawRec.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date) rawRec.get("date_added");
            author.setDateAdded(dateAdded);
            records.add(author);
        }

        // Actually closes connection
        db.closeConnection();

        return records;
    }

    @Override
    public final Author getAuthorById(Integer authorId) throws Exception {
       db.openConnection(ds);

        Map<String, Object> rawRec = db.findById("author", "author_id", authorId);
        Author author = new Author();
        author.setAuthorId((Integer) rawRec.get("author_id"));
        author.setAuthorName(rawRec.get("author_name").toString());
        author.setDateAdded((Date) rawRec.get("date_created"));

        return author;
    }

    @Override
    public final void deleteAuthorById(Integer authorId) throws Exception {
        db.openConnection(ds);
        db.deleteSingleRecordPS("author", "author_id", authorId);
    }

    @Override
    public void saveAuthor(Integer authorId, String authorName) throws Exception {
        db.openConnection(ds);
        if (authorId == null || authorId.equals(0)) {
            db.insertRecord("author", Arrays.asList("author_name", "date_created"), Arrays.asList(authorName, new Date()));
        } else {
            db.updateRecord("author", Arrays.asList("author_name"), Arrays.asList(authorName), "author_id", authorId);
        }
    }
}
