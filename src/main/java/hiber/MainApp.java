package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car(1,"toyota");
      Car car2 = new Car(2,"volvo");
      Car car3 = new Car(3,"lada");
      Car car4 = new Car(4,"bmw");

      User user1 = new User("Andrey", "Lastname1", "user1@mail.ru");
      User user2 = new User("Ugor", "Lastname2", "user2@mail.ru");
      User user3 = new User("Alexey", "Lastname3", "user3@mail.ru");
      User user4 = new User("Max", "Lastname4", "user4@mail.ru");

      car1.setUser(user1);
      user1.setCar(car1);
      car2.setUser(user2);
      user2.setCar(car2);
      car3.setUser(user3);
      user3.setCar(car3);
      car4.setUser(user4);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();

      System.out.println();
      System.out.println("Пользователи в базе данных:");
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      User userCar1 = userService.getUserByCar(1, "toyota");
      User userCar2 = userService.getUserByCar(2, "volvo");

      System.out.println();
      System.out.println("Пользователь с машиной " + car1);
      System.out.println(userCar1);
      System.out.println();
      System.out.println("Пользователь с машиной " + car2);
      System.out.println(userCar2);

      context.close();
   }
}
