package com.hz.world.account.domain.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @outhor lujian
 * @create 2018-06-11 16:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchUserByKeywordResponse implements Serializable {
    private List<User> docs = new ArrayList<>();

    @Data
    public static class User {
        private long id;
        private String nickname;
        private String headImg;
    }

    @Data
    public static class UserDTO {
        private long userId;
        private String nickname;
        private String headImg;
    }

    public List<UserDTO> getUserDTOs() {
        List<UserDTO> userDTOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(docs)) {
            return userDTOS;
        }
        for (SearchUserByKeywordResponse.User user : docs) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getId());
            userDTO.setHeadImg(user.getHeadImg());
            userDTO.setNickname(user.getNickname());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
