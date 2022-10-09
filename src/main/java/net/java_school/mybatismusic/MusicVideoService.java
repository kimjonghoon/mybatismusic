package net.java_school.mybatismusic;

import java.util.List;

public interface MusicVideoService {
	
	public int getTotalRecordCount(String searchWord);

	public List<MusicVideo> getVideos(String searchWord, Integer startRecord, Integer endRecord);

	public void add(String content);
}
