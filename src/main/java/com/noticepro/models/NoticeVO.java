package com.noticepro.models;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {

	private int notice_id;
	private int user_id;
	private String title;
	private String content;
	private String wdate;
	private String lastupdate;
	private String id;

	private List<String> file_name;

	private MultipartFile file;

	private int page;

	private Map pagemap;

	private List<NoticeVO> noticeList;
	private List<FileVO> fileList;

	private int file_id;

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public List<NoticeVO> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<NoticeVO> noticeList) {
		this.noticeList = noticeList;
	}

	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map getPagemap() {
		return pagemap;
	}

	public void setPagemap(Map pagemap) {
		this.pagemap = pagemap;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public List<String> getFile_name() {
		return file_name;
	}

	public void setFile_name(List<String> file_name) {
		this.file_name = file_name;
	}

}
