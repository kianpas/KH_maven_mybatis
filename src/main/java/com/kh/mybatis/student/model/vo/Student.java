package com.kh.mybatis.student.model.vo;

import java.io.Serializable;
import java.util.Date;


public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String name;
	private String tel;
	private Date reg_date; //java.util.Date
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int no, String name, String tel, Date reg_date) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.reg_date = reg_date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + ", tel=" + tel + ", reg_date=" + reg_date + "]";
	}
	
	
}
