package com.practice.my_vocabulary.controller.api;

import com.practice.my_vocabulary.controller.request.VocabularyRequest;
import com.practice.my_vocabulary.model.Vocabulary;
import com.practice.my_vocabulary.service.VocabularyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/vocabularies")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @GetMapping
    public ResponseEntity<List<Vocabulary>> getAll() {
        return ResponseEntity.ok(vocabularyService.getAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Vocabulary create(@Valid @RequestBody VocabularyRequest request) {
        return vocabularyService.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vocabulary> edit(@Valid @RequestBody VocabularyRequest request,
                                           @PathVariable long id) {
        return ResponseEntity.ok(vocabularyService.update(request, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        vocabularyService.delete(id);
    }

    @GetMapping(path = "/search", params = "eng")
    public ResponseEntity<List<Vocabulary>> getVocabularyByEng(@RequestParam String eng) {
        return ResponseEntity.ok(vocabularyService.getVocabularyByEng(eng));
    }

    @GetMapping(path = "/search", params = "thai")
    public ResponseEntity<List<Vocabulary>> getVocabularyByThai(@RequestParam String thai) {
        return ResponseEntity.ok(vocabularyService.getVocabularyByThai(thai));
    }

}
