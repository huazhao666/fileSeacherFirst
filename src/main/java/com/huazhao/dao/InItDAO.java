package com.huazhao.dao;

import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 18:51
 */
public class InItDAO {
    private String[] getSQLs(){
        try (InputStream is = InItDAO.class.getClassLoader().getResourceAsStream("init.sql")){
            Scanner sc = new Scanner(is,"UTF-8");
            StringBuffer sb = new StringBuffer();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                sb.append(line);
            }
            return sb.toString().split(";");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void init() {
        try {
            Connection c = DBUtil.getConnection();
            String[] sqLs = getSQLs();
            for(String sql: sqLs){
                LogUtil.log("开始执行sql：" + sql);
                try (PreparedStatement ps = c.prepareStatement(sql)){
                    if(sql.toUpperCase().startsWith("SELECT")){
                        try(ResultSet rs = ps.executeQuery()){
                            ResultSetMetaData metaData = rs.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            int rowCount = 0;
                            while (rs.next()){
                                StringBuffer sb = new StringBuffer("|");
                                for (int i = 1; i <= columnCount; i++) {
                                    sb.append(rs.getString(i)).append("|");
                                }
                                LogUtil.log(sb.toString());
                                rowCount++;
                            }
                            LogUtil.log("查询出 %d 行",rowCount);
                        }
                    }else {
                        int i = ps.executeUpdate();
                        LogUtil.log("收到影响的行一共有 %d 行: ", i);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
