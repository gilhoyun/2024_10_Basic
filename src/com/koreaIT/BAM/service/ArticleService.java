package com.koreaIT.BAM.service;

import java.util.List;

import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}

	public void writeArticle(int lastArticleId, String regDate, String updateDate, String title, String body) {
		articleDao.writeArticle(lastArticleId, regDate, updateDate, title, body);
	}
	
	public List<Article> getArticles(String searchKeyword) {
		return articleDao.getArticles(searchKeyword);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		articleDao.modifyArticle(foundArticle, title, body);
	}

	public void deleteArticle(Article foundArticle) {
		articleDao.deleteArticle(foundArticle);
	}
	
	public int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = 0;

		try {
			id = Integer.parseInt(cmdBits[2]);
		} catch (Exception e) {
			return 0;
		}
		return id;
	}
}
