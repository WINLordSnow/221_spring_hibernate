package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Yakov", "Marushchak", "yakov@bk.ru", new Car(2112, "LADA")));
      userService.add(new User("Marina", "Marushchak", "mar@mail.ru"));
      userService.add(new User("Ivan", "Ivanov", "ii89@mail.ru", new Car(6, "Ford")));
      userService.add(new User("Max", "Petrov", "PMax@list.ru", new Car(600, "Mersedes Benz")));

      System.out.println(userService.getUser(3));
      System.out.println(userService.getUserByCar(6, "Ford")
              .map(User::toString)
              .orElse("Пользователь не найден"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      context.close();
   }
}
