package com.freeboard01.api.user;

import com.freeboard01.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final HttpSession httpSession;
    private final UserService userService;

    @PostMapping
    private void join(@RequestBody UserForm user){
        userService.join(user);
    }

    @PostMapping(params = {"type=LOGIN"})
    private ResponseEntity<UserDto> login(@RequestBody UserForm user){
        UserDto userDto = userService.login(user);
        httpSession.setAttribute("USER", user);
        return ResponseEntity.ok(userDto);
    }
}
