package com.huazhao.service;

import com.huazhao.dao.InItDAO;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 18:52
 */
public class DBService {
    private final InItDAO dao = new InItDAO();
    public void init() {
        dao.init();
    }
}
