import Entity.MediaItemsEntity;
import hib.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class main {
    public static void main(String[] args) {
//        boolean res=Assignment.isExistUsername("haarel");
//        String res=Assignment.insertUser("aa","11","aaa"
//                ,"aas","10","9","1995");
//        List res=Assignment.getTopNItems(4);
//        String res=Assignment.validateUser("harel","1234");
        Assignment.insertToHistory("aa","1");
//        System.out.println(res);
    }
}
