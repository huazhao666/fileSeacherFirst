package com.huazhao.dao;

import com.huazhao.model.FileMeta;
import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 18:52
 */
public class SaveDAO {
    public void save(List<FileMeta> fileList){
        try{
            Connection c = DBUtil.getConnection();
            String sql =  "INSERT INTO file_meta(name,path,is_directory,pinyin,pinyin_first" +
                    ",size,last_modified) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                for(FileMeta fileMeta : fileList){
                    ps.setString(1,fileMeta.getName());
                    ps.setString(2,fileMeta.getPath());
                    ps.setBoolean(3,fileMeta.getDirectory());
                    ps.setString(4,fileMeta.getPinyin());
                    ps.setString(5,fileMeta.getPinyinFirst());
                    ps.setLong(6,fileMeta.getLength());
                    ps.setLong(7,fileMeta.getLastModifiedTimeStamp());
                    int i = ps.executeUpdate();
                    LogUtil.log("执行SQL:%s, %s,收到影响的行数是 %d",sql,fileMeta,i);
                }

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
