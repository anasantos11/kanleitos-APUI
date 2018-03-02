package br.com.kanleitos.util;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<String> messages;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getMessages() {
		messages = (messages != null) ? messages : new ArrayList<>();
		return messages;
	}

	public void addError(String error) {
		getMessages().add(error);
	}

}