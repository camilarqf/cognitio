package br.com.cognitio.infrastructure.api.controller;

import br.com.cognitio.application.dto.UserDto;
import br.com.cognitio.application.mapper.UserMapper;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.in.UserUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<UserDto>saveUser(@RequestBody UserDto userDto){

        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        userUseCase.createUser(user);
        logger.info("Usuário criado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        User userToUpdate = UserMapper.INSTANCE.userDtoToUser(userDto);
        User updatedUser =  userUseCase.updateUser(id, userToUpdate);
        UserDto updatedUserDto = UserMapper.INSTANCE.userToUserDto(updatedUser);
        logger.info("Usuário editado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>>getAllUsers(){

        logger.info("Buscando usuários");
        List<User> userList = userUseCase.findAllUsers();
        List<UserDto> userDtos = userList.stream()
                .map(UserMapper.INSTANCE::userToUserDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUserById(@PathVariable Long id){

        logger.info("Buscando usuário com ID:" + id);
        User user = userUseCase.findUserById(id);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }

    @PostMapping("/block/{id}")
    public ResponseEntity<UserDto>blockUseById(@PathVariable Long id){

        logger.info("Buscando usuário com ID:" + id);
        User user = userUseCase.blockUser(id);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }

    @PostMapping("/unblock/{id}")
    public ResponseEntity<UserDto>unBlockUseById(@PathVariable Long id){

        logger.info("Buscando usuário com ID:" + id);
        User user = userUseCase.unBlockUser(id);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }

}
