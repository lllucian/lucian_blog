package com.lucian.back.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/07/21 10:33
 **/
@Data
public class UserFormVO {
    private String id;

    private String username;

    private String email;

    private String enabled;

    private String locked;

    private List<String> roleIds;
}
