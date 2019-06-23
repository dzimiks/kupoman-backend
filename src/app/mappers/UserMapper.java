package app.mappers;

import app.dataTransferObjects.UserDTO;
import app.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:43
 */
@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO userToUserDTO(User user);
}
