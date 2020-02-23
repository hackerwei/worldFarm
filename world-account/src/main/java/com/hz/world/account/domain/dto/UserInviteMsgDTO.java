package com.hz.world.account.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-06 21:14
 */
@Data
public class UserInviteMsgDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long userId;
    private long inviteUserId;
    public UserInviteMsgDTO(long userId, long inviteUserId) {
        this.userId = userId;
        this.inviteUserId = inviteUserId;
    }

}
