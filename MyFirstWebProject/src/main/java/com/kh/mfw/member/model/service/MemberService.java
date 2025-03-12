package com.kh.mfw.member.model.service;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	

	public MemberDTO login(MemberDTO memberDTO) {
		/*
		 * if(memberDTO.getMemberId().length() > 10 || !
		 * memberDTO.getMemberId().matches("/^[A-Za-z0-9]/")) {
		 * 
		 * } if(memberDTO.getMemberPw().length() > 20 || !
		 * memberDTO.getMemberPw().matches("/^[A-Za-z0-9]/")) {
		 * 
		 * }
		 */
		//서비스단에서 유효성 검사하기(Validation)
		MemberDTO loginMember = new MemberDAO().login(memberDTO);	
		return loginMember;
	}
}
