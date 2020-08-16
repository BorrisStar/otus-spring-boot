package spring.model;

import lombok.Data;
import spring.dto.BookDto;

import java.util.List;

@Data
public class BooksResponseBody {
    String msg;
    List<BookDto> result;
}
