package com.adaming.myapp.comment.dao;

import java.util.List;

import com.adaming.myapp.entities.Comment;

public interface ICommentDao {

	 Comment addComment(Comment c);
	
	 List<Comment> getAllComments();
}
