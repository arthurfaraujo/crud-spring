package com.arthur.crudspring.enums.converters;

import java.util.stream.Stream;

import com.arthur.crudspring.enums.Status;

import jakarta.persistence.AttributeConverter;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
  
  @Override
  public String convertToDatabaseColumn(Status status) {
    if (status == null) {
      return null;
    }
    return status.getValue();
  }

  @Override
  public Status convertToEntityAttribute(String value) {

    if (value == null) {
      return null;
    }

    return Stream.of(Status.values())
        .filter(stat -> stat.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
