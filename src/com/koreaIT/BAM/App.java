package com.koreaIT.BAM;

import java.util.Scanner;

import com.koreaIT.BAM.controller.ArticleController;
import com.koreaIT.BAM.controller.Controller;
import com.koreaIT.BAM.controller.MemberController;

public class App {
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		memberController.makeTestData();
		articleController.makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			String[] cmdBits = cmd.split(" ");
			String controllerName = cmdBits[0];
			
			Controller controller = null;
			
			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}
			
			String methodName = cmdBits[1];
			controller.doAction(cmd, methodName);
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
}
