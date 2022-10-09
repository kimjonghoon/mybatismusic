package net.java_school.mybatis;

import java.util.HashMap;
import java.util.List;

import net.java_school.mybatismusic.MusicVideo;

import org.apache.ibatis.annotations.Param;

public interface MusicVideoMapper {

	public int selectCountOfVideos(@Param("searchWord") String searchWord);

	public List<MusicVideo> selectVideos(HashMap<String, String> hashmap);

	public void insert(@Param("content") String content);

}
