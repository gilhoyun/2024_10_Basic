package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				lastArticleId++;

				Article article = new Article(lastArticleId, title, body);

				articles.add(article);

				System.out.printf("%d번 게시물이 생성되었습니다\n", lastArticleId);

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}

				System.out.println("번호	|	제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}
			} else if(cmd.startsWith("article detail ")) {
				
				String[] cmdSplit = cmd.split(" ");
				int id = Integer.parseInt(cmdSplit[2]);
				
				Article foundArticle = null;
				
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
						
					if(article.id == id) {
						foundArticle = article;	
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 없습니다.");
					continue;
				}
				System.out.println("게시물 상세보기");
				System.out.println("번호: " + foundArticle.id);
				System.out.println("제목: " + foundArticle.title);
				System.out.println("내용: " + foundArticle.body);
				
			}
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
}

class Article {
	int id;
	String title;
	String body;

	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}
