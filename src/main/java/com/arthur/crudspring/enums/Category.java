package com.arthur.crudspring.enums;

public enum Category {
  BACK_END("Back-end"), FRONT_END("Front-end");

  private String value;

  private Category(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static Category fromValue(String value) {
    for (Category category : Category.values()) {
      if (category.getValue().equals(value)) {
        return category;
      }
    }
    throw new IllegalArgumentException("Unknown category: " + value);
  }
}
