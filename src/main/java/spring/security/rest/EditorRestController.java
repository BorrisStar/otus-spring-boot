package spring.security.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.model.Editor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/editors")
public class EditorRestController {
    private List<Editor> Editors = Stream.of(
            new Editor(1L, "Ivan", "Ivanov"),
            new Editor(2L, "Sergey", "Sergeev"),
            new Editor(3L, "Petr", "Petrov")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Editor> getAll() {
        return Editors;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('editors:read')")
    public Editor getById(@PathVariable Long id) {
        return Editors.stream().filter(editor -> editor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('editors:write')")
    public Editor create(@RequestBody Editor editor) {
        this.Editors.add(editor);
        return editor;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('editors:write')")
    public void deleteById(@PathVariable Long id) {
        this.Editors.removeIf(editor -> editor.getId().equals(id));
    }
}
