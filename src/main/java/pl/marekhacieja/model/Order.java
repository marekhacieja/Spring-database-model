package pl.marekhacieja.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_order")
    private Long id;
    @ManyToMany
    @JoinTable(name = "order_books",
       joinColumns = {@JoinColumn(name="order_id", referencedColumnName="id_order")},
       inverseJoinColumns = {@JoinColumn(name="book_id", referencedColumnName="id_book")}
    )
    private List<Book> books;
    @Column(name = "date_issued")
    private String dateIssued;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    
    Order(){}

	public Order(String dateIssued) {
		this.dateIssued = dateIssued;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> book) {
		this.books = book;
	}


	public String getDateIssued() {
		return dateIssued;
	}


	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", book=" + books + ", dateIssued=" + dateIssued +
				", client=" + client.getFirstname() +" "+ client.getLastname() + "]";
	}
    
   
}