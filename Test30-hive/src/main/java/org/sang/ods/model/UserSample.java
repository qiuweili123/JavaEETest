package org.sang.ods.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 */
@Data
public class UserSample implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userNum;

    private String userName;

    private String userGender;

    private Integer userAge;

}