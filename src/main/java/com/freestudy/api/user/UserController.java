package com.freestudy.api.user;

import com.freestudy.api.common.controller.BaseController;
import com.freestudy.api.oauth2.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/api/users")
public class UserController extends BaseController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/me")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity getUsersMe(@CurrentUser User user) {
    return buildResponse(user);
  }

  @PutMapping("/me")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity postUsersMe(
          @CurrentUser User user,
          @Valid @RequestBody UserDto userDto,
          Errors errors
  ) {

    if (errors.hasErrors()) {
      return BadRequest(errors);
    }

    userService.save(getOrNotFound(user), userDto);

    return buildResponse(user);
  }


  private ResponseEntity buildResponse(User user) {
    var userResource = new UserResource(user);
    return ResponseEntity.ok(userResource);
  }

}
