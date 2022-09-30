package net.java_school.mybatismusic;

import java.util.List;

public interface MusicVideoService {
	
	public int getTotalRecords();

	public List<MusicVideo> getVideos(Integer startRecord, Integer endRecord);

	public void add(String content);
}
