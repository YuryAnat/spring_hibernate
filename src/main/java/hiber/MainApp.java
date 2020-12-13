package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException, ClassNotFoundException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car lada = new Car("lada", 52);
      Car bmv = new Car("bmv", 525);

      carService.add(lada);
      carService.add(bmv);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(lada);
      user2.setCar(lada);
      user3.setCar(bmv);
      user4.setCar(bmv);


      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car id = " + user.getCar().getId());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }

      User userByCar = userService.getUserByCar(lada.getModel(), lada.getSeries());
      System.out.println(userByCar.getId());

      context.close();
   }
}
