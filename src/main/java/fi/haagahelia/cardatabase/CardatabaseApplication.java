package fi.haagahelia.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.cardatabase.domain.Car;
import fi.haagahelia.cardatabase.domain.CarRepository;
import fi.haagahelia.cardatabase.domain.Owner;
import fi.haagahelia.cardatabase.domain.OwnerRepository;
import fi.haagahelia.cardatabase.domain.User;
import fi.haagahelia.cardatabase.domain.UserRepository;

@SpringBootApplication
public class CardatabaseApplication {
	
  private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);
  
  // Inject repositories to main class
  @Autowired 
  private CarRepository repository;
  
  @Autowired 
  private OwnerRepository orepository;
  
  @Autowired 
  private UserRepository urepository;
  
  public static void main(String[] args) {
    SpringApplication.run(CardatabaseApplication.class, args);
 
  }
  
  @Bean
  CommandLineRunner runner(){
    return args -> {
    	// Save demo data to database
        // Add owner objects and save these to db
        Owner owner1 = new Owner("John" , "Johnson");
        Owner owner2 = new Owner("Mary" , "Robinson");
        orepository.save(owner1);
        orepository.save(owner2);
  
        // Add car object with link to owners and save these to db.
        Car car = new Car("Ford", "Mustang", "Red", 
            "ADF-1121", 2017, 59000, owner1);
        repository.save(car);
        car = new Car("Nissan", "Leaf", "White",
            "SSJ-3002", 2014, 29000, owner2);
        repository.save(car);
        car = new Car("Toyota", "Prius", "Silver",
            "KKO-0212", 2018, 39000, owner2);
        repository.save(car);
        
        // username: user password: user
        urepository.save(new User("user",
        "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi",
        "USER"));
        // username: admin password: admin
        urepository.save(new User("admin",
        "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", 
        "ADMIN"));
      };
     }
    } 

