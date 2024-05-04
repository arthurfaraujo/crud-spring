package com.arthur.crudspring.exception;

public class RecordNotFoundException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  public RecordNotFoundException(long id) {
    super("Record with id " + id + " not found!");
  }
}
