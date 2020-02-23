package com.hz.world.api.h5.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class VideoOutDTO implements Serializable{
	
	private static final long serialVersionUID = -1493211329942520129L;
	
	private long videoId;
	private String fileUrl;
	private String thumbnailUrl;
	private String videoTitle;
	private int viewCnt; 
	private int likeCnt;
}
