package com.ssafy.dokdo.Controller;

import com.ssafy.dokdo.Entity.User;
import com.ssafy.dokdo.Exception.ResourceNotFoundException;
import com.ssafy.dokdo.Model.UserDto;
import com.ssafy.dokdo.Repository.UserRepository;
import com.ssafy.dokdo.Security.CurrentUser;
import com.ssafy.dokdo.Security.UserPrincipal;
import com.ssafy.dokdo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("user")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User findUser = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return convertToDto(findUser);
    }

//    @GetMapping("user/dogam")
//    public List<Dogam> getDogam(@CurrentUser UserPrincipal userPrincipal) {
//        User findUser = userRepository.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//        return findUser.getDogamList();
////        return dogamRepository.findAllByUserId(userPrincipal.getId())
////                        .orElseThr() -> new ResourceNotFoundException("Dogam", "user_id", userPrincipal.getId()));
//    }

    @PutMapping("quiz")
    public ResponseEntity<?> setQuiz(@CurrentUser UserPrincipal userPrincipal, @RequestBody Map<String, Integer> body) {
        try{
            User user = userService.updateQuizResult(userPrincipal.getId(), body.get("quiz"));
            return new ResponseEntity<>(user.getQuizUser(), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("character")
    public ResponseEntity<?> setCharacter(@CurrentUser UserPrincipal userPrincipal, @RequestBody User user) {
        try{
            return new ResponseEntity<>(
                    convertToDto(userService.updateUserCharacter(userPrincipal.getId(), user.getUserCharacter())),
                    HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("nickname")
    public ResponseEntity<?> setNickname(@CurrentUser UserPrincipal userPrincipal, @RequestBody User user) {
        try{
            return new ResponseEntity<>(
                    convertToDto(userService.updateName(userPrincipal.getId(), user.getName())),
                    HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private UserDto convertToDto(User findUser){
        if (findUser == null) return null;
        UserDto dto = new UserDto();
        dto.setName(findUser.getName());
        dto.setEmail(findUser.getEmail());
        dto.setUserCharacter(findUser.getUserCharacter());
        return dto;
    }

}
