package com.torrang.springbootdev.service;

import com.torrang.springbootdev.domain.Article;
import com.torrang.springbootdev.dto.AddArticleRequest;
import com.torrang.springbootdev.dto.UpdateArticleRequest;
import com.torrang.springbootdev.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    // 볼르고 글 저장
    public Article save(AddArticleRequest addArticleRequest) {
        return blogRepository.save(addArticleRequest.toEntity());
    }

    // 모든 블로그 글 가져오기
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 가져오기
    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 블로그 글 수정하기
    @Transactional
    public Article update(long id, UpdateArticleRequest updateArticleRequest) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(updateArticleRequest.getTitle(), updateArticleRequest.getContent());

        return article;
    }

    // 블로그 글 삭제하기
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }
}
