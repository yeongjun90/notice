package com.noticepro.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.noticepro.common.PageModule;
import com.noticepro.mapper.noticeMapper;
import com.noticepro.models.FileVO;
import com.noticepro.models.NoticeFileVO;
import com.noticepro.models.NoticeVO;
import com.noticepro.models.UserVO;

@Service
public class NoticeService {

	@Autowired
	private noticeMapper noticeMapper;

	public NoticeVO notice_add(NoticeVO noticeVO, HttpSession session) {
		try {
			UserVO userinfo = (UserVO) session.getAttribute("userinfo");
			noticeVO.setUser_id(userinfo.getUser_id());
			noticeMapper.notice_add(noticeVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return noticeVO;
	}

	public FileVO file_add(FileVO fileVO) {
		try {
			MultipartFile multiFile = fileVO.getFile();

			String filename = UUID.randomUUID().toString().replace("-", "");
			String ext = multiFile.getOriginalFilename()
					.substring(multiFile.getOriginalFilename().lastIndexOf(".") + 1);

			String thefile = filename + "." + ext;

			fileVO.setOriginal_name(multiFile.getOriginalFilename());
			fileVO.setFile_name(thefile);

			noticeMapper.file_add(fileVO);

			String fileSavePath = "C:/files/";
			File f = new File(fileSavePath + thefile);

			multiFile.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileVO;
	}

	public Map notice_file_add(NoticeFileVO noticeFileVO) {
		Map ret = new HashMap();
		try {
			noticeMapper.notice_file_add(noticeFileVO);
			ret.put("result", "ok");
		} catch (Exception e) {
			ret.put("result", "fail");
			e.printStackTrace();
		}
		return ret;
	}

	public NoticeVO noticeList(NoticeVO noticeVO) {
		Map ret = new HashMap();

		PageModule pm = new PageModule();

		int total = noticeMapper.noticeCount();
		pm.processPage(noticeVO.getPage(), total, ret, 20);

		Map page = (Map) ret.get("page");
		noticeVO.setPagemap(page);
		noticeVO.setPage(Integer.parseInt(page.get("page") + ""));
		List<NoticeVO> noticeList = noticeMapper.noticeList(noticeVO);
		noticeVO.setNoticeList(noticeList);
		return noticeVO;
	}

	public Map notice_delete(NoticeVO noticeVO) {
		Map ret = new HashMap();
		try {
			noticeMapper.notice_file_delete(noticeVO);
			noticeMapper.notice_delete(noticeVO);
			ret.put("result", "ok");
		} catch (Exception e) {
			ret.put("result", "fail");
			e.printStackTrace();
		}
		return ret;
	}

	public NoticeVO notice(NoticeVO noticeVO) {
		NoticeVO notice = noticeMapper.notice_one(noticeVO);
		List<FileVO> noticeFile = noticeMapper.notice_file(noticeVO);
		notice.setFileList(noticeFile);
		return notice;
	}

	public Map file_download(FileVO fileVO, HttpServletResponse response) throws IOException {
		Map ret = new HashMap();

		ServletOutputStream outStream = null;
		InputStream inputStream = null;
		try {
			outStream = response.getOutputStream();

			inputStream = new FileInputStream("c:/files/" + fileVO.getFile_name());

			response.setContentType("application/octet-stream");

			response.setHeader("Content-Disposition",

					"attachment;filename=\"" + fileVO.getFile_name() + "\"");

			byte[] outByte = new byte[4096];

			while (inputStream.read(outByte, 0, 4096) != -1) {
				outStream.write(outByte, 0, 4096);
			}

			outStream.flush();

			ret.put("result", "ok");
		} catch (Exception e) {

			ret.put("result", "fail");
			throw new IOException();
		} finally {
			inputStream.close();
			outStream.close();
		}

		return ret;
	}

	public Map notice_update(NoticeVO noticeVO, String[] flist) {
		Map ret = new HashMap();
		try {
			for (int i = 0; i < flist.length; i++) {
				noticeVO.setFile_id(Integer.parseInt(flist[i]));
				noticeMapper.notice_file_update(noticeVO);
			}
			noticeMapper.notice_update(noticeVO);
			ret.put("result", "ok");
		} catch (Exception e) {
			ret.put("result", "fail");
			e.printStackTrace();
		}

		return ret;
	}
}
