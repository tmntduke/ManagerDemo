package com.tmnt.DAO;

import com.tmnt.Entity.NewInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmnt on 2015/12/27.
 */
public class ManagerDAO {
    public boolean insert(String name, String password, String email) {
        Connection conn = DBConnection.getConn();
        String sql = "insert into Manager (name,password,email) values (?,?,?)";
        int count = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, email);
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean queryUser(String username) {
        Connection connection = DBConnection.getConn();
        String sql = "select id from Manager where name=?";
        ResultSet set = null;
        boolean cheeck = false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            set = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cheeck = set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cheeck) {
            return true;
        } else {
            return false;
        }
    }

    public boolean queryId(String name, String password) {
        Connection conn = DBConnection.getConn();
        String sql = "select id from Manager where name=? and password=?";
        ResultSet set = null;
        boolean check = false;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            set = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            check = set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertNew(NewInfo newInfo) {
        Connection connection = DBConnection.getConn();
        String sql = "insert into Newlist (author,title,text,image) values(?,?,?,?)";
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newInfo.getAuthor());
            statement.setString(2, newInfo.getTitle());
            statement.setString(3, newInfo.getText());
            statement.setString(4, newInfo.getImage());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<NewInfo> queryNew() {
        Connection connection = DBConnection.getConn();
        ResultSet set = null;
        String sql = "select * from Newlist";
        List<NewInfo> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            int i = 0;
            //ResultSetMetaData rsmd = set.getMetaData() ;
            Statement s = connection.createStatement();
            ResultSet ss = s.executeQuery("SELECT COUNT (*)  FROM Newlist");
            if (ss.next()) {
                i = ss.getInt(1);
            }
            System.out.print(i);
            int count = 0;
            NewInfo[] newInfo = new NewInfo[i];
            while (set.next()) {
                newInfo[count] = new NewInfo();
                newInfo[count].setAuthor(set.getString("author"));
                newInfo[count].setTitle(set.getString("title"));
                newInfo[count].setText(set.getString("text"));
                newInfo[count].setImage(set.getString("image"));
                list.add(newInfo[count]);
                count++;
            }
            System.out.print("  list " + list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(String author, String title, String text, String image, int id) {
        Connection connection = DBConnection.getConn();
        int count = 0;
        String sql = "update Newlist set author=?,title=?,text=?,image=? where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author);
            statement.setString(2, title);
            statement.setString(3, text);
            statement.setString(4, image);
            statement.setInt(5, id);
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int queryNewId(String author, String title) {
        Connection connection = DBConnection.getConn();
        String sql = "select id from Newlist where author=? and title=?";
        ResultSet set = null;
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author);
            statement.setString(2, title);
            set = statement.executeQuery();
            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean deleteNew(int id) {
        Connection connection = DBConnection.getConn();
        String sql = "delete from Newlist where id=?";
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count!=0){
            return true;
        }
        else {
            return false;
        }
    }
    public List<NewInfo> pageBar(int start,int end){
      String sql="select * from(\n" +
              "   select t.* ,rownum rn \n" +
              "   from (select * from Newlist  )t\n" +
              "   where rownum <=?\n" +
              ")\n" +
              "where rn>=?";
        ResultSet set=null;
        NewInfo[] newInfos=new NewInfo[5];
        List<NewInfo>list=new ArrayList<>();
        int count=0;
        Connection connection=DBConnection.getConn();
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,end);
            statement.setInt(2,start);
            set=statement.executeQuery();
            while (set.next()){
                newInfos[count]=new NewInfo();
                newInfos[count].setAuthor(set.getString("author"));
                newInfos[count].setTitle(set.getString("title"));
                newInfos[count].setText(set.getString("text"));
                newInfos[count].setImage(set.getString("image"));
                list.add(newInfos[count]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int queryCount(){
        String sql="SELECT COUNT (*)  FROM Newlist";
        PreparedStatement s= null;
        ResultSet ss=null;
        int i=0;
        try {
            s = getStatment(sql);
            ss= s.executeQuery();
            if (ss.next()) {
                i = ss.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;

    }



    public PreparedStatement getStatment(String sql) throws Exception{
        Connection connection=DBConnection.getConn();
        PreparedStatement statement=connection.prepareStatement(sql);
        return statement;
    }
}
