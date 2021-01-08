package com.eugene.crude.crude.practic.repository.hibernate;


import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.utils.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.NoResultException;
import java.util.List;


public class RegionRepositoryImpl implements RegionRepository {


    Query query;

    public RegionRepositoryImpl() {

    }

    @Override
    public Region getById(Integer aLong) {
        List<Region> listReg;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();

            query = session.createQuery(" FROM Region WHERE id=:param1");
            query.setParameter("param1", aLong);
            listReg = query.list();

        }
        return new RegionBuilderImpl(listReg.get(0).getId(), listReg.get(0).getCharRegName()).build();
    }

    @Override
    public List<Region> getAll() {
        List<Region> listReg;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            listReg = session.createQuery(" FROM Region").list();

        }

        return listReg;
    }
@Override
    public Region findByName(Region region) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Region where charRegName =:name");
            query.setParameter("name", region.getCharRegName());


            transaction.commit();
            return (Region) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public Region save(Region region) {

            try (Session session = HibernateConnection.getSessionFactory().openSession()) {
                Transaction transaction = null;
                transaction = session.beginTransaction();
                session.save(region);

                transaction.commit();
            }
            return region;
        }


    @Override
    public Region update(Region region) {

        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.update(region);
            transaction.commit();

        }
        return region;
    }

    @Override
    public void deleteById(Integer aLong) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.delete(session.get(Region.class, aLong));
            transaction.commit();
        }
    }
}
