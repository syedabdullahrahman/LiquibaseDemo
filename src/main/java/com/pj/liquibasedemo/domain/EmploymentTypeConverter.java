package com.pj.liquibasedemo.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Employee Type Converter that converts the Enum type to a String and vice versa.
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Converter(autoApply = true)
public class EmploymentTypeConverter implements AttributeConverter<EmploymentType, String>
{
	@Override
	public String convertToDatabaseColumn(EmploymentType employmentType)
	{
		return employmentType == null ? null : employmentType.getLabel();
	}

	@Override
	public EmploymentType convertToEntityAttribute(String label)
	{
		return label == null ? null : Stream.of(EmploymentType.values())
				.filter(c -> c.getLabel().equals(label))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
