package com.noticepro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.noticepro.models.FileVO;
import com.noticepro.models.NoticeFileVO;
import com.noticepro.models.NoticeVO;

public interface noticeMapper {

	@Select("select count(*) c from notice")
	public int noticeCount();

	@Select("select *,(select id from user where user.user_id=notice.user_id) id from notice order by notice_id desc limit #{page},20")
	public List<NoticeVO> noticeList(NoticeVO noticeVO);

	@Select("select * from notice where notice_id=#{notice_id}")
	public NoticeVO notice_one(NoticeVO noticeVO);

	@Select("select *,(select file_name from files where files.file_id=notice_file.file_id) file_name,(select original_name from files where files.file_id=notice_file.file_id) original_name from notice_file where notice_id=#{notice_id}")
	public List<FileVO> notice_file(NoticeVO noticeVO);

	@Insert("insert into notice(user_id,title,content,wdate,lastupdate) values(#{user_id},#{title},#{content},now(),now())")
	@Options(useGeneratedKeys = true, keyProperty = "notice_id")
	public int notice_add(NoticeVO noticeVO);

	@Insert("insert into files(original_name,file_name,wdate) values(#{original_name},#{file_name},now())")
	@Options(useGeneratedKeys = true, keyProperty = "file_id")
	public int file_add(FileVO fileVO);

	@Insert("insert into notice_file(notice_id,file_id) values(#{notice_id},#{file_id})")
	public int notice_file_add(NoticeFileVO noticeFileVO);

	@Update("update notice set title=#{title},content=#{content},lastupdate=now() where notice_id=#{notice_id}")
	public int notice_update(NoticeVO noticeVO);

	@Delete("delete from notice where notice_id=#{notice_id}")
	public int notice_delete(NoticeVO noticeVO);

	@Delete("delete from notice_file where notice_id=#{notice_id}")
	public int notice_file_delete(NoticeVO noticeVO);

	@Delete("delete from notice_file where notice_id=#{notice_id} and file_id=#{file_id}")
	public int notice_file_update(NoticeVO noticeVO);
}
