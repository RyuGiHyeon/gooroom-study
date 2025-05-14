package gooroom.starter.controller;

import gooroom.starter.domain.Article;
import gooroom.starter.dto.AddArticleRequest;
import gooroom.starter.dto.ArticleResponse;
import gooroom.starter.dto.UpdateArticleRequest;
import gooroom.starter.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    /**
     * 25.05.11
     * 작성자 : 류기현
     * Article 작성
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    /**
     * 25.05.11
     * 작성자 : 류기현
     * Article 전체 조회
     */
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    /**
     * 25.05.11
     * 작성자 : 류기현
     * Article 조회
     */
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    /**
     * 25.05.11
     * 작성자 : 류기현
     * Article 삭제
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    /**
     * 25.05.11
     * 작성자 : 류기현
     * Article 수정
     */
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestBody UpdateArticleRequest request) {

        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
