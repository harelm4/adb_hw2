import Entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.util.NotImplementedException;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
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
        //distinct

//        Session session= hib.HibernateUtil.currentSession();
//        int res=0;
//        try {
//            res = (int) session.createQuery("select distinct(users.userid) from UsersEntity users where users.registrationDate > CURRENT_DATE()-"+n).list().get(0);
//
//
//        }
//        catch (HibernateException e){
//            System.out.println(e);
//
//        }
//        finally {
//            hib.HibernateUtil.closeSession();
//            return res;
//        }

        throw new NotImplementedException();
    }
    public static void insertToLog (String userid){
        Session session = hib.HibernateUtil.currentSession();
        Transaction tx = null;
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        long uid = Long.parseLong((String.valueOf(userid)));

        try {
            tx = session.beginTransaction();
            LoginLogEntity log_data = new LoginLogEntity();
            log_data.setLogintime(date);
            log_data.setUserid(uid);
            session.save(log_data);//????
            tx.commit();
            System.out.println("Good job, The insertion to log table was successful! ");

        }catch (HibernateException e){
            System.out.println(e);
        }finally {
            hib.HibernateUtil.closeSession();
        }


    }
    public static long getNumberOfRegistredUsers(int n){

        Session session= hib.HibernateUtil.currentSession();
        long res=0;
        List res1=null;
        try {
            //res = (int) session.createQuery("select count(users.userid) from UsersEntity users where users.registrationDate > CURRENT_DATE()-"+n).list().get(0);
            Query   q = (session.createQuery("select count(users.userid) from UsersEntity users where users.registrationDate > CURRENT_DATE()-"+n));
            res1 = q.list();
            res = (long) res1.get(0);

        }
        catch (HibernateException e){
            System.out.println(e);

        }
        finally {
            hib.HibernateUtil.closeSession();

        }
        return res;
    }
    public static List getUsers (){
        Session session = hib.HibernateUtil.currentSession();
        List<UsersEntity> res_list = null;
        try {
            Query query= session.createQuery("select u from UsersEntity u");
            res_list = query.list();
            if (res_list.size()==0){
                return null;
            }
            return res_list;
        }
        catch (HibernateException e){
            System.out.println(e);
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
        return res_list;
    }
    public static UsersEntity getUser (String userid){
        Session session = hib.HibernateUtil.currentSession();
        UsersEntity res = null;
        long userid1 = Long.parseLong((String.valueOf(userid)));
        try {
            Query query= session.createQuery("select u from UsersEntity u where u.userid=:userid1");
            query.setParameter("userid1",userid1);
            List<UsersEntity> res_list = query.list();
            if (res_list.size()>0){
            res = res_list.get(0);}
            return res;
        }
        catch (HibernateException e){
            System.out.println(e);
        }
        finally {
            hib.HibernateUtil.closeSession();
        }
        return res;
    }





}
