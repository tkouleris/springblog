package com.tkouleris.springblog.Exception;

public class PostNotFoundException extends RuntimeException{
	public PostNotFoundException(String message)
	{
		super(message);
	}

}
