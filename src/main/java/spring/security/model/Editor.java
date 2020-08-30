package spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Editor {
    private Long id;
    private String firstName;
    private String lastName;
}
