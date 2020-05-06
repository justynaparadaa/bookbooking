package pl.jparada.bookbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jparada.bookbooking.repository.model.Book;
import pl.jparada.bookbooking.service.BookService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok().body(allBooks);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
        Book bookById = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookById);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.getBookById(id);
        bookService.deleteBookById(id);
    }

    @GetMapping("/books/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable(value = "title") String title) {
        return ResponseEntity.ok().body(bookService.getBookByName(title));
    }

    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable(value = "author") String author) {
        return ResponseEntity.ok().body(bookService.getBookByAuthor(author));
    }

}
