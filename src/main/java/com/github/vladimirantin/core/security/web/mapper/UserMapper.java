package com.github.vladimirantin.core.security.web.mapper;

import com.github.vladimirantin.core.security.model.CoreUser;
import com.github.vladimirantin.core.security.web.DTO.UserDTO;
import com.github.vladimirantin.core.web.mapper.CoreMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends CoreMapperImpl<UserDTO, CoreUser> {}
