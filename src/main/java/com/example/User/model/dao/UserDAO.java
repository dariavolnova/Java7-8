package com.example.User.model.dao;

import com.example.User.model.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
    private static  final String URL = "jdbc:postgresql://localhost:5432/JavaMVC";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";
    private  static Connection connection;
    static {
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Class Postgresql in Java not found!");
        }
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e){
            System.err.println("Wrong URL,USERNAME or PASSWORD");
        }
    }
    private static int users_count;
    private List<User> users;
    public UserDAO()
    {
        users = new ArrayList<>();
    }
    public List<User> index(){
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM usermvc";
            ResultSet set = statement.executeQuery(query);
            while(set.next()){
                User user = new User();
                user.setId(set.getInt("id"));
                user.setAge(set.getInt("age"));
                user.setUsername(set.getString("username"));
                user.setPassword(set.getString("password"));
                boolean found = false;
                for (User slot : users) {
                    if (slot.getId() == set.getInt("id")) {
                        found = true;
                        break;
                    }
                }

                if (!found){
                    users.add(user);
                }
            }
        } catch (SQLException e){
            System.err.println("SQL query do not run");
        }
        return users;
    }

    public static int getUsers_count() {
        return users_count;
    }

    public static void setUsers_count(int users_count) {
        UserDAO.users_count = users_count;
    }

    public User show(int id){
        return  users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
    public void save(User user){
        try{
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO usermvc(username,password,age) VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"',"+user.getAge()+")";
            statement.executeUpdate(SQL);
        }
        catch (SQLException e){
            System.err.println("SQL query do not run");
        }
    }
    public void update(int id, User user){
        User tmp = show(id);
        tmp.setUsername(user.getUsername());
        tmp.setPassword(user.getPassword());
        tmp.setAge(user.getAge());
        System.out.println(id);
        try{
            Statement statement = connection.createStatement();
            String SQL ="UPDATE usermvc SET username = '"+user.getUsername()+"', password='"+user.getPassword()+"', age="+user.getAge()+" WHERE id="+id;
            statement.executeUpdate(SQL);
        }
        catch (SQLException e){
            System.err.println("SQL query do not run");
        }
    }
    public void delete(int id){
        users.removeIf(user -> user.getId() == id);
        try{
            Statement statement = connection.createStatement();
            String SQL ="DELETE FROM usermvc WHERE id="+id;
            statement.executeUpdate(SQL);
        }
        catch (SQLException e){
            System.err.println("SQL query do not run");
        }
    }
}