package com.hz.world.common.enums;

public enum HomeTaskCodeEnum {
    SCENE_1(1, "NEWHOME_REWARD_1","SHARE_REWARD_1"),
    SCENE_2(2, "NEWHOME_REWARD_2","SHARE_REWARD_2"),
    SCENE_3(3, "NEWHOME_REWARD_3","SHARE_REWARD_3"),
    SCENE_4(4, "NEWHOME_REWARD_4","SHARE_REWARD_4"),
    SCENE_5(5, "NEWHOME_REWARD_5","SHARE_REWARD_5"),
    SCENE_6(6, "NEWHOME_REWARD_6","SHARE_REWARD_6"),
    SCENE_7(7, "NEWHOME_REWARD_7","SHARE_REWARD_7"),
    SCENE_8(8, "NEWHOME_REWARD_8","SHARE_REWARD_8"),
    SCENE_9(9, "NEWHOME_REWARD_9","SHARE_REWARD_9"),
    SCENE_10(10, "NEWHOME_REWARD_10","SHARE_REWARD_10");

    HomeTaskCodeEnum(final int code, final String newHomeCode, final String homeShareCode) {
        this.code = code;
        this.newHomeCode = newHomeCode;
        this.homeShareCode = homeShareCode;
    }

    private int code;
    private String newHomeCode;
    private String homeShareCode;

    public int getCode() {
        return code;
    }

	public String getNewHomeCode() {
		return newHomeCode;
	}

	public void setNewHomeCode(String newHomeCode) {
		this.newHomeCode = newHomeCode;
	}

	public String getHomeShareCode() {
		return homeShareCode;
	}

	public void setHomeShareCode(String homeShareCode) {
		this.homeShareCode = homeShareCode;
	}

	 public static String getHomeShare(int code) {
	        switch (code) {

	            case 1:
	                return SCENE_1.getHomeShareCode();
	            case 2:
	                return SCENE_2.getHomeShareCode();
	            case 3:
	                return SCENE_3.getHomeShareCode();
	            case 4:
	                return SCENE_4.getHomeShareCode();
	            case 5:
	                return SCENE_5.getHomeShareCode();
	            case 6:
	                return SCENE_6.getHomeShareCode();
	            case 7:
	                return SCENE_7.getHomeShareCode();
	            case 8:
	                return SCENE_8.getHomeShareCode();
	            case 9:
	                return SCENE_9.getHomeShareCode();
	            case 10:
	                return SCENE_10.getHomeShareCode();
	            default:
	                return "未知";
	        }

	    }
	 public static String getNewHome(int code) {
	        switch (code) {

	            case 1:
	                return SCENE_1.getNewHomeCode();
	            case 2:
	                return SCENE_2.getNewHomeCode();
	            case 3:
	                return SCENE_3.getNewHomeCode();
	            case 4:
	                return SCENE_4.getNewHomeCode();
	            case 5:
	                return SCENE_5.getNewHomeCode();
	            case 6:
	                return SCENE_6.getNewHomeCode();
	            case 7:
	                return SCENE_7.getNewHomeCode();
	            case 8:
	                return SCENE_8.getNewHomeCode();
	            case 9:
	                return SCENE_9.getNewHomeCode();
	            case 10:
	                return SCENE_10.getNewHomeCode();
	            default:
	                return "未知";
	        }

	    }

}
