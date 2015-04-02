package com.wu.ftpfile.model;

/**
 * 本类是对子菜单选项进行数据的封装。主要封装菜单名和图标名
 * Created by wuxinbo on 2015/4/2.
 */
public class SubMenu {
    /**
     * 菜单名。
     */
    private  String name;

    /**
     * 默认无参数构造器。
     */
    public  SubMenu(){};

    /**
     * 根据菜单名进行构造。
     * @param name
     */
    public SubMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
