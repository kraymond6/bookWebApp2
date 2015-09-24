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
}
    
