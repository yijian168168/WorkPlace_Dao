package com.dao.commonsdbutils.bean;

/**
 * 对应数据库中字段
 *
 * Created by zhangqingrong on 2016/5/8.
 */
public class WorkerInfo {

    private int id;

    private String workername;

    private String sex;

    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "WorkerInfo{" +
                "id=" + id +
                ", workername='" + workername + '\'' +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                '}';
    }
}
