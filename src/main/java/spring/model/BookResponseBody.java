package spring.model;

import lombok.Data;
import spring.dto.BookDto;

@Data
public class BookResponseBody {
    String msg;
    BookDto bookDto;
}
