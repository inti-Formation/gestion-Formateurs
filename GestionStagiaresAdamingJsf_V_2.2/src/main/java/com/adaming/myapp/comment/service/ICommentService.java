package com.adaming.myapp.comment.service;

import java.util.List;

import com.adaming.myapp.entities.Comment;

public interface ICommentService {

	 Comment addComment(Comment c);

	 List<Comment> getAllComments();
}
