package pl.jparada.bookbooking.exception;

import org.springframework.http.HttpStatus;

public class BookBorrowedException extends RuntimeException {

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BookBorrowedException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
