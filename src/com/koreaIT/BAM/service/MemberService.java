package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.MemberDao;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}
	
	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}
	
	public void joinMember(int lastMemberId, String regDate, String loginId, String loginPw, String name) {
		memberDao.joinMember(lastMemberId, regDate, loginId, loginPw, name);
	}
}