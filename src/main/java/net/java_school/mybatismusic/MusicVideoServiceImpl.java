package net.java_school.mybatismusic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;

import net.java_school.mybatis.MusicVideoMapper;

@Service
public class MusicVideoServiceImpl implements MusicVideoService {

	@Autowired
	private MusicVideoMapper musicVideoMapper;

	@Override
	public int getTotalRecords() {
		return musicVideoMapper.selectCountOfVideos();
	}

	@Override
	public List<MusicVideo> getVideos(Integer startRecord, Integer endRecord) {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("start", startRecord.toString());
		hashmap.put("end", endRecord.toString());

		return musicVideoMapper.selectVideos(hashmap);

	}

	@Override
	public void add(String content) {
		musicVideoMapper.insert(content);
	}

}
