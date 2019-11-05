package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Article;
import com.geek.repository.ArticleRepository;
import com.geek.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();
		articleRepository.findAll().iterator().forEachRemaining(articles::add);
		return articles;
	}

	@Override
	public Article createArticle(Article article) {
		Article newArticle;
		newArticle = articleRepository.save(article);
		return newArticle;
	}

	@Override
	public Article updateArticle(Long id, Article articleDetails) {
		Article article = findById(id);

		article.setCategory(articleDetails.getCategory());
		article.setTitle(articleDetails.getTitle());
		article.setAuthor(articleDetails.getAuthor());
		article.setDescription(articleDetails.getDescription());
		article.setContent(articleDetails.getContent());

		articleRepository.save(article);
		return article;
	}

	@Override
	public void deleteArticle(Long articleId) {
		articleRepository.delete(findById(articleId));
	}

	@Override
	public Article findById(Long id) {
		Optional<Article> article = articleRepository.findById(id);

		if (!article.isPresent()) {
            throw new ResourceNotFoundException("There is no Article with ID = " + id);
        }

		return article.get();

	}
	
	 /**
     * tests whether there is an article with te same title and author in the database
     * @param article
     * @return true if there is no article with the same author and title in the database
     */
    @Override
    public boolean titleAndAuthorValid(Article article){
        List<Article> articles = new ArrayList<>();
        articleRepository.findByTitleAndAuthor(article.getTitle(),article.getAuthor())
                .iterator().forEachRemaining(articles::add);
        if (!articles.isEmpty()) { return false;}
        else {return true;}
    }
	
    @Override
    public Article getLatestEntry(){
        List<Article> articles = getAllArticles();
        if(articles.isEmpty()){
            return null;
        }
        else{
            Long latestArticleID = articleRepository.findTopByOrderByIdDesc();
            return findById(latestArticleID);
        }
    }
	
	 //Pagination
    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}