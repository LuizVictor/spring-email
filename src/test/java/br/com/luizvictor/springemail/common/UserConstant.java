package br.com.luizvictor.springemail.common;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.user.UserDto;

public class UserConstant {
    public static UserDto VALID_USER_DTO = new UserDto("John Doe", "john@email.com", "123456");
    public static User VALID_USER = new User(VALID_USER_DTO);
}
