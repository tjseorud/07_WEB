package dex.sdk.member.model.dto;

import java.sql.Date;

public class UserDTO {
	private String userId;
	private String userPw;
	private String userName;
	private Date enrollDate;

	public UserDTO() {
		super();
	}

	public UserDTO(String userId, String userPw, String userName, Date enrollDate) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.enrollDate = enrollDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", enrollDate=" + enrollDate + "]";
	}

}
