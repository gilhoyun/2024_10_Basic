package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.service.MemberService;
import com.koreaIT.BAM.util.Util;

public class MemberController extends Controller {

	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.memberService = new MemberService();
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
		}
	}
	
	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String name = null;
		
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력 정보입니다");
				continue;
			}
			
			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);
			
			if (isLoginIdDup) {
				System.out.printf("[ %s ] 은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("[ %s ] 은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력 정보입니다");
				continue;
			}
			
			System.out.printf("비밀번호 확인 : ");
			String loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			break;
		}
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수 입력 정보입니다");
				continue;
			}
			
			break;
		}

		lastId++;
		
		memberService.joinMember(lastId, Util.getDateStr(), loginId, loginPw, name);

		System.out.printf("[ %s ] 님의 가입이 완료되었습니다\n", name);
	}
	
	@Override
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개를 생성하였습니다");
		for (int i = 1; i <= 3; i++) {
			memberService.joinMember(++lastId, Util.getDateStr(), "test" + i, "test" + i, "유저" + i);
		}
	}
}
