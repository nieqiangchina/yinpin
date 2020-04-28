package yinpin;

import java.sql.Connection;
import java.sql.SQLException;

public class OperationPaper {
    private static Connection connection=Connect_MySQL.getConnection();

    public void addYuyin(Yuyin yuyin) {//增
        // connection = Connect_MySQL.getConnection();
        String sql = "insert into yuyin (path, result, err_msg, sn, corpus_no, err_no) values(?, ?, ?, ?, ?, ?)";

        java.sql.PreparedStatement ptmt = null;
        try {
            ptmt = connection.prepareStatement(sql);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        try {
            ptmt.setString(1, yuyin.getPath());
            ptmt.setString(2, yuyin.getResult());
            ptmt.setString(3, yuyin.getErr_msg());
            ptmt.setString(4, yuyin.getSn());
            ptmt.setString(5, yuyin.getCorpus_no());
            ptmt.setString(6, yuyin.getErr_no());
            ptmt.execute();//执行给定的SQL语句，该语句可能返回多个结果

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
