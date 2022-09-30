package net.java_school.mybatismusic;

public class MusicVideo {
	private int no;
	private String content;

	public MusicVideo() {}

	public MusicVideo(int no, String content) {
		this.no = no;
		this.content = content;
	}

	public int getNo() {
	       return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}	
