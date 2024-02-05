package br.com.cognitio.infrastructure.api.controller;

import br.com.cognitio.application.mapper.UserMapper;
import br.com.cognitio.domain.dto.UserDto;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.in.UserUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    ResponseEntity<UserDto>save(@RequestBody UserDto userDto){

        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        userUseCase.createUser(user);
        logger.info("Usuário criado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }
}