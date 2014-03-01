package ch.romix.junit;

/**
 * Implementors promise to convert a {@link String} into an instance of type
 * <code> * &lt;T&gt;</code>.
 * 
 * .
 */
public interface TypeConverter<T extends Object> {
	/**
	 * @param value
	 *            to convert
	 * @return converted value
	 */
	T convert(String value);
}
