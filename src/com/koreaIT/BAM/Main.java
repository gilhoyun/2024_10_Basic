package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("=== 프로그램 시작 ===");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {

			System.out.print("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("article write")) {

				System.out.print("제목: ");
				String title = sc.nextLine();

				System.out.print("내용: ");
				String body = sc.nextLine();

				lastArticleId++;

				Article article = new Article(lastArticleId, title, body);

				articles.add(article);

				System.out.println(lastArticleId + "번 게시물이 생성되었습니다.");

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("번호  |    제목 ");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d     |    %s\n", article.lastArticleId, article.title);
					
				}

			}

			if (cmd.equals("exit")) {

				break;
			}
		}

		sc.close();

		System.out.println("=== 프로그램 종료 ===");
	}
}

class Article {
	int lastArticleId;
	String title;
	String body;
	
	
	Article(int id, String title, String body){
		this.lastArticleId = id;
		this.title = title;
		this.body = body;
		
	}

}
