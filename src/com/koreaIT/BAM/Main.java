package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class Main {

	static int lastArticleId;
	static List<Article> articles;
	static int lastMemberId;
	static List<Member> members;

	static {
		lastArticleId = 0;
		articles = new ArrayList<>();
		lastMemberId = 0;
		members = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}
			
			if(cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			
			if(cmd.equals("member join")) {
				int id = lastMemberId++;
				lastMemberId = id;
				
				String loginId = null;
				String loginPw = null;
				String name = null;
				
				while(true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine().trim();// trim()- 공백검사(앞뒤공백)
					
					if(loginId.length() == 0) { // 길이가 없다면 아무 입력도 하지 않은 경우
						System.out.println("아이디를 입력해주세요");
						continue;
					}
					
					if(loginIdDup(loginId) == false) {
						System.out.println("사용중인 아이디입니다.");
						continue;
					}
					
					System.out.println(loginId + "사용 가능한 아이디입니다.");
					break;
					
				}
					
				while(true) {				
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine().trim();
					
					if(loginPw.length() == 0) { // 길이가 없다면 아무 입력도 하지 않은 경우
						System.out.println("비밀번호를 입력해주세요");
						continue;
					}
					
					System.out.printf("비밀번호확인 : ");
					String loginPwCheck = sc.nextLine();
					
					if(loginPw.equals(loginPwCheck) == false) {
						System.out.println("비밀번호를 다시 입력해주세요.");
						continue;
					}
					break;
				}
				
				
				while(true) {
					System.out.printf("이름 : ");
					name = sc.nextLine().trim();
					
					if(name.length() == 0) { // 길이가 없다면 아무 입력도 하지 않은 경우
						System.out.println("이름을 입력해주세요");
						continue;
					}
					break;
				}
				
				
				Member member = new Member(lastMemberId, Util.getDateStr(), loginId, loginPw, name);
				
				members.add(member);
				
				System.out.printf("["+ name +"]"+ "님 회원가입을 축하합니다. \n");
				
				
				
			}else if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				lastArticleId++;

				Article article = new Article(lastArticleId, Util.getDateStr(), Util.getDateStr(), title, body);

				articles.add(article);

				System.out.printf("%d번 게시물이 생성되었습니다\n", lastArticleId);

			} else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}

				String searchKeyword = cmd.substring("article list".length()).trim();

				List<Article> printArticles = articles;

				if (searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);

					printArticles = new ArrayList<>();

					for (Article article : articles) {
						if (article.getTitle().contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}
	
		            System.out.println("번호	|	제목	|	작성일	");
					for (int i = printArticles.size() - 1; i >= 0; i--) {
						Article article = printArticles.get(i);
						System.out.printf("%d	|	%s	|	%s	\n", article.getId(), article.getTitle(), article.getRegDate());
					}

			} else if (cmd.startsWith("article detail ")) {
				int id = getCmdNum(cmd);

				if (id == 0) {
					System.out.println("올바른 형식이 아닙니다");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("번호 : %d\n", foundArticle.getId());
				System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
				System.out.printf("수정일 : %s\n", foundArticle.getUpdateDate());
				System.out.printf("제목 : %s\n", foundArticle.getTitle());
				System.out.printf("내용 : %s\n", foundArticle.getBody());

			} else if (cmd.startsWith("article modify ")) {
				int id = getCmdNum(cmd);

				if (id == 0) {
					System.out.println("올바른 형식이 아닙니다");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();

				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				foundArticle.setUpdateDate(Util.getDateStr());

				System.out.printf("%d번 게시물이 수정되었습니다\n", id);

			} else if (cmd.startsWith("article delete ")) {

				int id = getCmdNum(cmd);

				if (id == 0) {
					System.out.println("올바른 형식이 아닙니다");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(foundArticle);

				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);

			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}

	private static boolean loginIdDup(String loginId) {
		for (Member member : members) {
			if(member.getLoginId().equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	private static int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = 0;

		try {
			id = Integer.parseInt(cmdBits[2]);
		} catch (Exception e) {
			return 0;
		}
		return id;
	}

	private static Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트용 게시물 데이터 3개를 생성하였습니다");
		for (int i = 1; i <= 3; i++) {
			articles.add(new Article(++lastArticleId, Util.getDateStr(), Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}
}





