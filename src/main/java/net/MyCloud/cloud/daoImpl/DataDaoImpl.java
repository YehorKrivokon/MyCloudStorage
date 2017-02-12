package net.MyCloud.cloud.daoImpl;

import net.MyCloud.cloud.dao.DataDao;
import net.MyCloud.cloud.model.Data;
import org.hibernate.Hibernate;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataDaoImpl implements DataDao {
    Query query;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Data data) {
        entityManager.persist(data);
    }

    @Override
    public void delete(long[] ids) {
        Data c;
        for (long id : ids) {
            c = entityManager.getReference(Data.class, id);
            entityManager.remove(c);
        }
    }

    @Override
    public Data findByDataid(long id) {
        Query query = entityManager.createQuery("SELECT c FROM Data c WHERE c.id = :id", Data.class);
        query.setParameter("id", id);
        return (Data) query.getSingleResult();
    }

    @Override
    public List<Data> userDataList(Long user_id, String type) {
        if (type.equals("") || type.equals("all")) {
            query = entityManager.createQuery("SELECT g FROM Data g WHERE g.user_id = :user_id", Data.class);
        } else
        if (type.equals("video")) {
            query = entityManager.createQuery("SELECT g FROM Data g WHERE g.user_id = :user_id AND g.type IN ('webm','mp4','avi', 'mov', 'flv', '3gp')", Data.class);
        } else
        if (type.equals("audio")) {
            query = entityManager.createQuery("SELECT g FROM Data g WHERE g.user_id = :user_id AND g.type IN ('mp3', 'wma', 'wav', 'flac')", Data.class);
        } else
        if (type.equals("images")) {
            query = entityManager.createQuery("SELECT g FROM Data g WHERE g.user_id = :user_id AND g.type IN ('png', 'jpg', 'jpeg', 'gif', 'tiff', 'bmp', 'psd', 'jpe')", Data.class);
        } else
        if (type.equals("text")) {
            query = entityManager.createQuery("SELECT g FROM Data g WHERE g.user_id = :user_id AND g.type IN ('txt', 'pdf', 'doc', 'docx', 'fb2', 'rtf', 'odt', 'djvu')", Data.class);
        }
        query.setParameter("user_id", user_id);
        return (List<Data>) query.getResultList();
    }

    @Override
    public List<Data> searchData(String pattern) {
        Query query = entityManager.createQuery("SELECT c FROM Data c WHERE c.name LIKE :pattern", Data.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Data>) query.getResultList();
    }

}
