package ch.romix.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements TypeConverter<Date> {

	@Override
	public Date convert(String value) {
		try {
			return new SimpleDateFormat().parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
