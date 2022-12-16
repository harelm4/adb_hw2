import Entity.MediaItemsEntity;
import Entity.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Assignment {
    public static boolean isExistUsername (String username){
        Session session= hib.HibernateUtil.currentSession();
        try {
            List<UsersEntity> items =
                    session.createQuery("select u from UsersEntity u where u.username=username").list();
            return items.size()>0;
        }
        catch (HibernateException e){
            System.out.println(e);
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
        return false;
    }
    public static String insertUser(String username, String password,
                                    String first_name, String last_name, String day_of_birth,
                                    String month_of_birth, String year_of_birth){
        if(!isExistUsername(username)){
            Session session= hib.HibernateUtil.currentSession();
            try {
                Transaction tx = session.beginTransaction();
                UsersEntity user = new UsersEntity();
                user.setDateOfBirth(Date.valueOf(year_of_birth + '-' + month_of_birth + '-' + day_of_birth));
                user.setUsername(username);
                user.setPassword(password);
                user.setFirstName(first_name);
                user.setLastName(last_name);
                user.setRegistrationDate(new Date(System.currentTimeMillis()));
                String uid = session.save(user).toString();
                tx.commit();
                return uid;
            }
            catch (HibernateException e){
                System.out.println(e);
            }
            finally {
                hib.HibernateUtil.closeSession();
            }
            return null;
        }
        else {
            return null;
        }
    }
    public static List getTopNItems (int top_n){
        Session session= hib.HibernateUtil.currentSession();
        try {
            return
                    session.createQuery("select mi from MediaItemsEntity mi order by mi.mid asc").list();

        }
        catch (HibernateException e){
            System.out.println(e);
            return null;
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
    }
}
