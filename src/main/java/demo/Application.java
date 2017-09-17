package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.servlet.Servlet;
import java.util.*;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

@Component
class BookingCommandLineRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    for (Booking b : this.bookingRepository.findAll()) {
      System.out.println(b.toString());
    }
  }

  @Autowired
  BookingRepository bookingRepository;


}


/**
 * Spring Data JPA-powered <em>repository</em> interface. Supports common operations like
 * {@link #findAll()} and {@link #save(Object)} against JPA entities. This particular repository
 * deals in {@link demo.Booking booking} objects.
 */
interface BookingRepository extends JpaRepository<Booking, Long> {
  Collection<Booking> findByBookingName(String bookingName);
}


/**
 * Represents one (very under-specified) <em>booking</em> for a named person at a theoretical
 * establishment like a restaurant.
 */
@Entity
class Booking {

  @Id
  @GeneratedValue
  private long id;
  private String bookingName;

  public Booking(String bookingName) {
    super();
    this.bookingName = bookingName;
  }

  Booking() {}

  @Override
  public String toString() {
    return "Booking{" + "bookingName='" + bookingName + '\'' + ", id=" + id + '}';
  }

  public String getBookingName() {
    return bookingName;
  }

  public void setBookingName(String bookingName) {
    this.bookingName = bookingName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}

