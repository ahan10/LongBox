package org.longbox.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookFinishedList;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

import java.util.List;

public class ComicBookFinishedListDaoImpl implements ComicBookFinishedListDao{

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    @Override
    public boolean saveToFinished(User user, ComicBook comicBook) throws UserIDDoesNotExistException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = (User) session.merge(user);
            comicBook = (ComicBook) session.merge(comicBook);
            ComicBookFinishedList finishedItem = new ComicBookFinishedList(user, comicBook);
            session.persist(finishedItem);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public int removeFromFavorites(Long userId, Long comicBookId) {
        Session session = null;
        Transaction transaction = null;
        int deletedEntities = 0;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ComicBookFinishedList where id.userId = :userId and id.comicBookId = :comicBookId");
            query.setParameter("userId", userId);
            query.setParameter("comicBookId", comicBookId);
            deletedEntities = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            session.close();
        }
        return deletedEntities;
    }

    @Override
    public List<ComicBook> getUsersFinishedList(Long userId) {

        return null;
    }

    @Override
    public List<User> getListOfUsersFinished(Long comicBookId) {
        return null;
    }
}