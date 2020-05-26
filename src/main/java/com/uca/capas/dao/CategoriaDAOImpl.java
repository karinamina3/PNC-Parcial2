package com.uca.capas.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Categoria;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {
	
	@PersistenceContext(unitName = "parcial")
	private EntityManager entityManager;

	@Override
	public List<Categoria> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_categoria");
		
		Query query = entityManager.createNativeQuery(sb.toString(), Categoria.class);
		List<Categoria> categorias = query.getResultList();
		
		return categorias;
	}

	@Override
	@Transactional
	public void save(Categoria categoria) throws DataAccessException {
		// TODO Auto-generated method stub
		entityManager.persist(categoria);
	}

}