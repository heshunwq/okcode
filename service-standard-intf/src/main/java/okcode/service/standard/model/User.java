//package okcode.service.standard.model;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Map;
//
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.MapKeyColumn;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import okcode.service.standard.enums.Gender;
//import okcode.service.standard.util.StandardConstants;
//
//@Entity
//@Table(name = StandardConstants.TABLE_PREFIX_NAMING + "identity_user")
//public class User implements Serializable {
//
//	private static final long serialVersionUID = -441280225512165188L;
//
//	public static final String ATTR_PASSWORD_URL = "passwordUrl";
//	
//	@Id
//	@Column(name = "id", unique = true, nullable = false)
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@Column(name = "user_name", unique = true, nullable = false)
//	private String userName;
//	
//	@Column(name = "nick_name", nullable = false)
//	private String nickName;
//	
//	@Enumerated
//	@Column(name = "gender")
//	private Gender gender;
//	
//	@Column(name = "birthday")
//	private Date birthday;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@Column(name = "photo")
//	private String photo;
//	
//	@Column(name = "email", unique = true)
//	private String email;
//	
//	@Column(name = "mobile", unique = true)
//	private String mobile;
//	
//	@Column(name = "tel")
//	private String tel;
//	
//	@Column(name = "qq")
//	private String qq;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "create_at", nullable = false)
//	private Date createAt;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "update_at", nullable = false)
//	private Date updateAt;
//	
//	@ElementCollection(fetch = FetchType.LAZY)
//	@JoinTable(name = StandardConstants.TABLE_PREFIX_NAMING + "identity_user_attr", 
//			joinColumns = @JoinColumn(name = "user_id"))
//	@MapKeyColumn(length = 255, name = "attr_key")
//	@Column(name = "attr_value", length = 3000)
//	private Map<String, String> attributes;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getNickName() {
//		return nickName;
//	}
//
//	public void setNickName(String nickName) {
//		this.nickName = nickName;
//	}
//
//	public Gender getGender() {
//		return gender;
//	}
//
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}
//
//	public Date getBirthday() {
//		return birthday;
//	}
//
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	public String getTel() {
//		return tel;
//	}
//
//	public void setTel(String tel) {
//		this.tel = tel;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(String photo) {
//		this.photo = photo;
//	}
//
//	public String getQq() {
//		return qq;
//	}
//
//	public void setQq(String qq) {
//		this.qq = qq;
//	}
//
//	public Date getCreateAt() {
//		return createAt;
//	}
//
//	public void setCreateAt(Date createAt) {
//		this.createAt = createAt;
//	}
//
//	public Date getUpdateAt() {
//		return updateAt;
//	}
//
//	public void setUpdateAt(Date updateAt) {
//		this.updateAt = updateAt;
//	}
//
//	public Map<String, String> getAttributes() {
//		return attributes;
//	}
//
//	public void setAttributes(Map<String, String> attributes) {
//		this.attributes = attributes;
//	}
//
//}
