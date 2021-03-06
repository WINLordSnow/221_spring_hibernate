package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> getCar(int series, String model) {
      String hql = "FROM Car WHERE series = :series and model = :model";
      Session session = sessionFactory.openSession();
      TypedQuery<Car> query = session.createQuery(hql);
      query.setParameter("series", series);
      query.setParameter("model", model);
      List<Car> list = query.getResultList();
      session.close();
      return list;
   }

   @Override
   public User getUser(long id) {
      return sessionFactory.getCurrentSession().get(User.class, id);
   }

}
