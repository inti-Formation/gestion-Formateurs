package com.adaming.myapp.comment.service;

import java.util.List;

import com.adaming.myapp.entities.Comment;

public interface ICommentService {

	public Comment addComment(Comment c);

	public List<Comment> getAllComments();
}
