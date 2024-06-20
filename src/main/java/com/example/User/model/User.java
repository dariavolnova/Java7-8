package com.example.User.model;

import com.example.User.model.dao.UserDAO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {
    private int id;
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 4, max = 15, message = "Длина имени пользователя должна быть от 4 до 15 символов")
    private String username;
    @NotEmpty(message = "Пароль  не может быть пустым")
    @Size(min = 4, max = 30, message = "Длина пароля  должна быть от 4 до 30 символов")
    private String password;
    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;
    public User(int id,String username,String password,int age){
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
    public User(){
        this.id = UserDAO.getUsers_count();
        int temp = UserDAO.getUsers_count();
        UserDAO.setUsers_count(temp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}