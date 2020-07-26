package com.spring.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.HomeController;
import com.spring.board.service.BoardService;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.UserVo;

@Controller
public class LoginController {
	
	@Autowired
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/board/login.do", method = RequestMethod.GET)
	public String login(Locale locale) throws Exception {
		logger.info("login.do");
		
		return "/board/login";
	}
	
	// 회원가입 페이지이동
	@RequestMapping(value = "/board/signup.do", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) throws Exception{
		logger.info("signup.do");
		
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		codeList = boardService.codeSelectPhone();
		
		model.addAttribute("codeListPhone", codeList);
		
		return "/board/signup";
	}
	
	// 회원가입
	@ResponseBody
	@RequestMapping(value = "/board/signupAction.do", method = RequestMethod.POST)
	public int signupAction(UserVo userVo, Model model) throws Exception {
		logger.info("signupAction.do");
		System.out.println(userVo.toString());
		
		
		
		int result = boardService.signup(userVo);
		
		
//		return "redirect:/board/login.do";
		return result;
		
	}
	
	// idChkAction
	@ResponseBody
	@RequestMapping(value = "/board/idChkAction.do", method = RequestMethod.POST)
	public int idChk(UserVo userVo) throws Exception {
		logger.info("idChkAction.do");
		int result = boardService.idChk(userVo);
		
		
		return result;
	}
	
	// 로그인
	@RequestMapping(value = "/board/loginAction.do", method = RequestMethod.POST)
	public String login(UserVo userVo
			,HttpServletRequest req
			) throws Exception {
		
		logger.info("login.do");
		System.out.println("login userVo"+userVo.toString());
		HttpSession session = req.getSession();
		UserVo login = boardService.login(userVo);
		if (login == null) {
			session.setAttribute("user", null);
		} else {
			session.setAttribute("user", login);
		}
		System.out.println("login session : "+login.toString());
		return "redirect:/board/boardList.do";
	}
	
	// 로그인 Chk
	@ResponseBody
	@RequestMapping(value = "/board.loginChkAction.do", method = RequestMethod.POST)
	public int loginChk(UserVo userVo) throws Exception {
		
		int loginChk = boardService.loginChk(userVo);
		
		return loginChk;
	}
	
	// 로그아웃
	@RequestMapping(value = "/board/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/board/boardList.do";
	}
	
}
