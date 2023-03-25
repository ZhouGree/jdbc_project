package com.gelgel.Example;

/**
 * 实体类
 */

public class Users {
    //用户id
    private Integer id;
    //用户名
    private String name;
    //用户性别
    private String gender;
    //状态：0-禁用，1-启用
    private Integer status ;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //@Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                '}';
    }
}

