package pl.home.ekantor.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Getter
public class ObjectNotFoundException extends RuntimeException {

	@NonNull
	private final String friendlyMessage;

	public ObjectNotFoundException(@NonNull String friendlyMessage) {
		this.friendlyMessage = friendlyMessage;
	}

	public static ObjectNotFoundException byUUID(@Nullable UUID uuid, @NonNull Class<?> objectClass) {
		return create(String.valueOf(uuid), ObjectFieldName.UUID, objectClass.getSimpleName());
	}

	private static ObjectNotFoundException create(@Nullable String fieldValue, @NonNull ObjectFieldName fieldName,
												  @Nullable String objectType) {
		ObjectNotFoundException exception = new ObjectNotFoundException(String.format("%s searched by %s" +
			" : %s not found", objectType != null ? objectType : "Object", fieldName, fieldValue));
		return exception;
	}
}
