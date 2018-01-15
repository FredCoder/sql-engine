package com.k.parsing.command;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="db")
public class DBCommand {
	
	private String url;
	private String type;
	private String userName;
	private String passWord;
	public String getUrl() {
		return url;
	}
	public String getType() {
		return type;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	

}
