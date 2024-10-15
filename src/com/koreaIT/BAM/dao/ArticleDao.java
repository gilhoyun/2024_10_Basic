package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {

	private List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}
	
	public void writeArticle(int lastArticleId, String regDate, String updateDate, String title, String body) {
		Article article = new Article(lastArticleId, regDate, updateDate, title, body);
		articles.add(article);
	}
	
	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword.length() > 0) {

			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.getTitle().contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}
		return articles;
	}
	
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		foundArticle.setTitle(title);
		foundArticle.setBody(body);
		foundArticle.setUpdateDate(Util.getDateStr());
	}

	public void deleteArticle(Article foundArticle) {
		articles.remove(foundArticle);
	}
}
