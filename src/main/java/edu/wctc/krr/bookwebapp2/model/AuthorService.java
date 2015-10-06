/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.krr.bookwebapp2.model;

import java.util.List;

/**
 *
 * @author Kallie
 */
public class AuthorService {
    private AuthorDaoStrategy dao;


    public AuthorService(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    public final List<Author> getAllAuthors() throws Exception {
        return dao.getAllAuthors();
    }
    
    public final Author getAuthorById(String authorId) throws Exception{
        return dao.getAuthorById(Integer.parseInt(authorId));
    }
    
    public final void deleteAuthorById(String authorId) throws Exception{
        dao.deleteAuthorById(Integer.parseInt(authorId));
    }
    
    public final void saveAuthor(String authorId, String authorName) throws Exception{
        Integer id = null;
        if(authorId == null || authorId.isEmpty()){
            id = null;
        } else {
            id = Integer.parseInt(authorId);
        }
        dao.saveAuthor(id, authorName);
    }
}
    
