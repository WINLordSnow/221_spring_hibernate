package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

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
   public User getUser(Car car) {
      String hql = "FROM Car WHERE model = :model and series = :series";
      Session session = sessionFactory.openSession();
      TypedQuery<Car> query = session.createQuery(hql);
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      List<Car> list = query.getResultList();
      session.close();
      if (!list.isEmpty()) {
         return list.get(0).getUser();
      }
      return null;
   }

}
