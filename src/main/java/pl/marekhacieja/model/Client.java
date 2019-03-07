package pl.marekhacieja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Long id;
	@Column(name = "firstname", nullable = false)
	private String firstname;
	@Column(name = "lastname", nullable = false)
	private String lastname;
	@Column(name = "address", nullable = false, length = 256)
	private String address;
	@OneToMany(mappedBy = "client", 
			fetch = FetchType.EAGER,
			cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
			orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	Client() {
	}

	public Client(String firstname, String lastname, String address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	  public void addOrder(Order order) {
	        order.setClient(this);
	        getOrders().add(order);
	    }
	

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstname + ", lastName=" + lastname + ", address=" + address
				+ orders.size() + ",\n orders=" + orders +"]";
	}

}
