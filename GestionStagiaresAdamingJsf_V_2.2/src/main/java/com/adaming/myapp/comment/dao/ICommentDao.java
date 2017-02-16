package com.adaming.myapp.comment.dao;

import java.util.List;

import com.adaming.myapp.entities.Comment;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface ICommentDao {

	 Comment addComment(Comment c);
	
	 List<Comment> getAllComments();
}
