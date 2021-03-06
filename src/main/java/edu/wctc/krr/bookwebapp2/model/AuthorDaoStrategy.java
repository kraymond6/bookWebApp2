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
public interface AuthorDaoStrategy {

    List<Author> getAllAuthors() throws Exception;
    
     Author getAuthorById(Integer authorId) throws Exception;
     
     void deleteAuthorById(Integer authorId) throws Exception;
     
     void saveAuthor(Integer authorId, String authorName) throws Exception;
}
