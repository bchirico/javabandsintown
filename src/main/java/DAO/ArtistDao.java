package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import entity.Artist;

public class ArtistDao implements DaoInterface<Artist, String> {

	private Session currentSession;
	
	private Transaction currentTransaction;

	public ArtistDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Artist entity) {
		getCurrentSession().save(entity);
	}

	public void update(Artist entity) {
		getCurrentSession().update(entity);
	}

	public Artist findById(String id) {
		Artist entity = (Artist) getCurrentSession().get(Artist.class, id);
		return entity; 
	}

	public void delete(Artist entity) {
		getCurrentSession().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Artist> findAll() {
		List<Artist> artists = (List<Artist>) getCurrentSession().createCriteria(Artist.class).list();
		return artists;
	}

	public void deleteAll() {
		List<Artist> entityList = findAll();
		for (Artist entity : entityList) {
			delete(entity);
		}
	}

}
