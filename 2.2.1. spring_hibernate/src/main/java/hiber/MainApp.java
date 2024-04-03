package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Anna", "Vershinina", "veranna@gmail.com");
      User user2 = new User("Vladimir", "Klepikov", "vladKlepikov@gmail.com");
      User user3 = new User("Alexandr", "Anisin", "alexanisin@gmail.com");
      User user4 = new User("Anastasiya", "Zarovchatskaya", "anastasiya@gmail.com");

      Car car1 = new Car("Patriot", 300);
      Car car2 = new Car("Opel", 700);
      Car car3 = new Car("Kalina", 150);
      Car car4 = new Car("Toyota", 500);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println("---------------------------");
         System.out.println(user + " " + user.getCar());
         System.out.println("---------------------------");
      }
      System.out.println("--------------------------");
      System.out.println(userService.getUserWithCar("Patriot", 300));
      System.out.println("--------------------------");

      try {
         User notExistUser = userService.getUserWithCar("Granta", 256);
      } catch (Exception e) {
         System.out.println("---------------------");
         System.out.println("Потльзователя с такой машиной не существует в данной таблице");
         System.out.println("---------------------");
      }
      context.close();
   }
}
