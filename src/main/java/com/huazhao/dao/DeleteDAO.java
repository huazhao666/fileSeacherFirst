package com.huazhao.dao;

import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 18:51
 */
public class DeleteDAO {
    public void delete(List<Integer> idList){
        try {
            Connection c = DBUtil.getConnection();
            List<String> list = idList.stream()
                    .map(id -> "?").collect(Collectors.toList());
            String ret = String.join(",",list);
            String sql = String.format("DELETE FROM file_meta WHERE id IN (%s)",ret);
            try (PreparedStatement ps = c.prepareStatement(sql)){
                for(int i = 0;i < idList.size();i++){
                    ps.setInt(i+1,idList.get(i));
                }
                int i = ps.executeUpdate();
                LogUtil.log("执行 SQL: %s, %s, 收到影响的行数是: %d ", sql, idList, i);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
