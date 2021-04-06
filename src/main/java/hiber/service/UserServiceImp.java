package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public Optional<User> getUserByCar(int series, String model) {
      List<Car> list = userDao.getCar(series, model);
      return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0).getUser());
   }

   @Transactional
   @Override
   public User getUser(long id) {
      return userDao.getUser(id);
   }

}
