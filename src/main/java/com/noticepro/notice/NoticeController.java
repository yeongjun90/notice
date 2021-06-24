package com.noticepro.notice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noticepro.models.FileVO;
import com.noticepro.models.NoticeFileVO;
import com.noticepro.models.NoticeVO;
import com.noticepro.models.UserVO;

@RestController
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "notice_add", method = RequestMethod.POST)
	public NoticeVO notice_add(NoticeVO noticeVO, HttpSession session) {
		return noticeService.notice_add(noticeVO, session);
	}

	@RequestMapping(value = "file_add", method = RequestMethod.POST)
	public FileVO file_add(FileVO fileVO) {
		return noticeService.file_add(fileVO);
	}

	@RequestMapping(value = "notice_file_add", method = RequestMethod.POST)
	public Map notice_file_add(NoticeFileVO noticeFileVO) {
		return noticeService.notice_file_add(noticeFileVO);
	}

	@RequestMapping(value = "noticeList", method = RequestMethod.POST)
	public NoticeVO noticeList(NoticeVO noticeVO) {
		return noticeService.noticeList(noticeVO);
	}

	@RequestMapping(value = "notice_delete", method = RequestMethod.POST)
	public Map notice_delete(NoticeVO noticeVO) {
		return noticeService.notice_delete(noticeVO);
	}

	@RequestMapping(value = "notice", method = RequestMethod.POST)
	public NoticeVO notice(NoticeVO noticeVO) {
		return noticeService.notice(noticeVO);
	}

	@RequestMapping(value = "file_download", method = RequestMethod.GET)
	public Map file_download(FileVO fileVO, HttpServletResponse response) throws IOException {
		return noticeService.file_download(fileVO, response);
	}

	@RequestMapping(value = "notice_update", method = RequestMethod.POST)
	public Map notice_update(NoticeVO noticeVO, HttpServletRequest request) {
		String[] flist = request.getParameterValues("flist[]");
		return noticeService.notice_update(noticeVO, flist);
	}
}
