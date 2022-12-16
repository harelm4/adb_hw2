import Entity.AdministratorsEntity;
import Entity.HistoryEntity;
import Entity.MediaItemsEntity;
import Entity.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.util.NotImplementedException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Assignment {
    public static boolean isExistUsername (String username){
        Session session= hib.HibernateUtil.currentSession();
        try {
            Query query=
                    session.createQuery("select u from UsersEntity u where u.username=:username");
            query.setParameter("username",username);
            List<UsersEntity> items=query.list();
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
    public static String validateUser (String username, String password){
        Session session= hib.HibernateUtil.currentSession();
        try {
            String q="select u" +
                    " from UsersEntity u " +
                    "where u.username=:username and u.password=:password";
            Query query= session.createQuery(q);
            query.setParameter("username",username);
            query.setParameter("password",password);
            List<UsersEntity> res=query.list();
            if(res.size()>0){
                return ((UsersEntity)res.get(0)).getFirstName()+' '+((UsersEntity)res.get(0)).getLastName();
            }
            else{
                return "Not Found! Could be a hacker.";
            }

        }
        catch (HibernateException e){
            System.out.println(e);
            return null;
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
    }
    public static String validateAdministrator (String username,String password){
        Session session= hib.HibernateUtil.currentSession();
        try {
            String q="select au" +
                    " from AdministratorsEntity au " +
                    "where au.username=:username and au.password=:password";
            Query query= session.createQuery(q);
            query.setParameter("username",username);
            query.setParameter("password",password);
            List<AdministratorsEntity> res=query.list();
            if(res.size()>0){
                return Long.toString(((AdministratorsEntity)res.get(0)).getAdminid());
            }
            else{
                return "Not Found! Could be a hacker.";
            }

        }
        catch (HibernateException e){
            System.out.println(e);
            return null;
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
    }
    public static void insertToHistory (String userid, String mid){
        Session session= hib.HibernateUtil.currentSession();
        try {
            Transaction tx = session.beginTransaction();

            HistoryEntity history = new HistoryEntity();
            history.setViewtime(new Date(System.currentTimeMillis()));
            history.setUserid(Long.valueOf(userid));
            history.setMid(Long.valueOf(mid));
            session.save(history);
            tx.commit();
            System.out.println("Good job,The insertion to history table was successful!");
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("inputs should be numbers");
            System.out.println(e);
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
    }
    public static List getHistory (String userid){
        throw new NotImplementedException();
    }
    public static void insertToLog (String userid){
        throw new NotImplementedException();
    }
    public static int getNumberOfRegistredUsers(int n){
        throw new NotImplementedException();
    }
    public static List getUsers (){
        throw new NotImplementedException();
    }
    public static UsersEntity getUser (String userid){
        throw new NotImplementedException();
    }


}
