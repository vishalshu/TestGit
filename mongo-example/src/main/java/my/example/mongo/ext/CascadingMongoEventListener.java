package my.example.mongo.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class CascadingMongoEventListener extends AbstractMongoEventListener {
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void onBeforeConvert(final Object source) {
		ReflectionUtils.doWithFields(source.getClass(),
				new ReflectionUtils.FieldCallback() {

					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {
						ReflectionUtils.makeAccessible(field);

						if (field.isAnnotationPresent(DBRef.class)
								&& field.isAnnotationPresent(CascadeSave.class)) {
							final Object fieldValue = field.get(source);

							if (fieldValue != null) {
								DbRefFieldCallback callback = new DbRefFieldCallback();

								ReflectionUtils.doWithFields(
										fieldValue.getClass(), callback);

								/*
								 * if(Collection.class.isAssignableFrom(fieldValue
								 * .getClass())){ for(Object
								 * fieldElValue:(Collection)fieldValue){
								 * 
								 * } }
								 */
								/*
								 * if (!callback.isIdFound()) { throw new
								 * MappingException(
								 * "Cannot perform cascade save on child object without id set"
								 * ); }
								 */

								mongoOperations.save(fieldValue);
								field.set(source, fieldValue);
							}
						}
					}

					/* private void saveField(Field field) */
				});
	}

	private static class DbRefFieldCallback implements
			ReflectionUtils.FieldCallback {
		private boolean idFound;

		public void doWith(Field field) throws IllegalArgumentException,
				IllegalAccessException {
			ReflectionUtils.makeAccessible(field);

			if (field.isAnnotationPresent(Id.class)) {
				idFound = true;
			}
		}

		public boolean isIdFound() {
			return idFound;
		}
	}
}