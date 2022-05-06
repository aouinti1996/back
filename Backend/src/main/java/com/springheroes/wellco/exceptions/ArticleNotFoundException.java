package com.springheroes.wellco.exceptions;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String categoryName) {
        super(categoryName);
    }
}
