package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r from Role r")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "role")
	private List<User> users;
	
	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);
		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);
		return user;
	}
}
