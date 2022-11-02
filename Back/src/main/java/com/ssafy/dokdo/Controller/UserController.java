package com.ssafy.dokdo.Controller;

import com.ssafy.dokdo.Entity.Badge;
import com.ssafy.dokdo.Entity.Dogam;
import com.ssafy.dokdo.Entity.User;
import com.ssafy.dokdo.Exception.ResourceNotFoundException;
import com.ssafy.dokdo.Model.UserDto;
import com.ssafy.dokdo.Security.CurrentUser;
import com.ssafy.dokdo.Security.UserPrincipal;
import com.ssafy.dokdo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("user")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCurrentUser(userPrincipal.getId());
    }

    @PutMapping("quiz")
    public ResponseEntity<?> setQuiz(@CurrentUser UserPrincipal userPrincipal, @RequestBody Map<String, Integer> body) {
        try{
            return new ResponseEntity<>(
                    userService.updateQuizResult(userPrincipal.getId(), body.get("quiz")),
                    HttpStatus.OK);
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
                    userService.updateUserCharacter(userPrincipal.getId(), user.getUserCharacter()),
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
            // 게시판 닉네임 변경 로직...??
            return new ResponseEntity<>(
                    userService.updateName(userPrincipal.getId(), user.getName()),
                    HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/dogams")
    public List<Dogam> getDogamList(@CurrentUser UserPrincipal userPrincipal){
        return userService.getDogamList(userPrincipal.getId());
    }

    @GetMapping("/badge")
    public List<Badge> getAllBadges(@CurrentUser UserPrincipal userPrincipal) {

        Long user_id = userPrincipal.getId();

        return userService.getAllBadges(user_id);
    }

    @GetMapping("/user/dogam")
    public boolean getDogamList(@CurrentUser UserPrincipal userPrincipal, @RequestParam String domain, @RequestParam(name = "mongo_id") String mongoId){
        return userService.checkDogam(userPrincipal.getId(),domain,mongoId);
    }

}
