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

      userService.add(new User("Yakov", "Marushchak", "yakov@bk.ru", new Car("LADA", 2112)));
      userService.add(new User("Marina", "Marushchak", "mar@mail.ru"));
      userService.add(new User("Ivan", "Ivanov", "ii89@mail.ru", new Car("Ford", 6)));
      userService.add(new User("Max", "Petrov", "PMax@list.ru", new Car("Mersedes Benz", 600)));

      User user = userService.getUser(new Car("Ford", 7));
      if (user != null) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      } else {
         System.out.println("Пользователь не найден");
      }

//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println("Car = " + user.getCar());
//         System.out.println();
//      }

      context.close();
   }
}
