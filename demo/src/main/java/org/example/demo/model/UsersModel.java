package org.example.demo.model;

public class UsersModel {
    private int idUser;
    private String username;
    private String email;
    private int roleId;

    public UsersModel(int idUser, String username, String email, int roleId) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.roleId = roleId;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}