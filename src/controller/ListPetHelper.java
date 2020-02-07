package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListPet;

public class ListPetHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetCatalog");
	
	public void insertPet(ListPet lp) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(lp);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListPet> showAllPets(){
		EntityManager em = emfactory.createEntityManager();
		List<ListPet> allPets = em.createQuery("SELECT p FROM ListPet p").getResultList();
		return allPets;
	}
	
	public void deletePets(ListPet toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet> typedQuery = em.createQuery("select lp from ListPet lp where lp.type = :selectedType and lp.name = :selectedName and lp.owner = :selectedOwner", ListPet.class);
		
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		ListPet result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public ListPet searchForPetById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListPet found = em.find(ListPet.class, id);
		em.close();
		return found;
	}

	public void updatePet(ListPet toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListPet> searchForPetByType(String type) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet> typedQuery = em.createQuery("select lp from ListPet lp where lp.type =:selectedType", ListPet.class);
		typedQuery.setParameter("selectedType", type);
		List<ListPet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}

	public List<ListPet> searchForPetByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet> typedQuery = em.createQuery("select lp from ListPet lp where lp.name =:selectedName", ListPet.class);
		typedQuery.setParameter("selectedName", name);
		List<ListPet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public List<ListPet> searchForPetByOwner(String owner) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet> typedQuery = em.createQuery("select lp from ListPet lp where lp.owner =:selectedOwner", ListPet.class);
		typedQuery.setParameter("selectedOwner", owner);
		List<ListPet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
