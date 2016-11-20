package com.adaming.myapp.comment.dao;

import java.util.List;

import com.adaming.myapp.entities.Comment;

public interface ICommentDao {

	public Comment addComment(Comment c);
	
	public List<Comment> getAllComments();
}
