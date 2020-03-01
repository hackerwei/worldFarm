package com.hz.world.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private boolean finish;

}
