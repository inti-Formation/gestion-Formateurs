package com.adaming.myapp.comment.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.comment.dao.ICommentDao;
import com.adaming.myapp.entities.Comment;
@Transactional
public class CommentServiceImpl implements ICommentService{
    
	Logger log = Logger.getLogger("CommentServiceImpl ");
	
	private ICommentDao dao;

	public void setDao(ICommentDao dao) {
		log.info("<--------Dao Comment Injected--------->");
		this.dao = dao;
	}

	@Override
	public Comment addComment(Comment c) {
		// TODO Auto-generated method stub
		return dao.addComment(c);
	}

	@Override
	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return dao.getAllComments();
	}

}
