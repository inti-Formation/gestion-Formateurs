package com.adaming.myapp.comment.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Comment;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public class CommentDaoImpl implements ICommentDao {
    
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("CommentDaoImpl");
	@Override
	public Comment addComment(Comment c) {
		em.persist(c);
		log.info("Comment à bien été ajouter avec success");
		return c;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getAllComments() {
		Query query = em.createQuery("from Comment c");
		log.info("the size of comment it is "+query.getResultList().size());
		return query.getResultList();
	}

}
