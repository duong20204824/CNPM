package net.seedschool.duong.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import net.seedschool.duong.model.User;
import net.seedschool.duong.repository.UserRepository;
import net.seedschool.duong.service.AccountService;
import net.seedschool.duong.service.AccountServiceImpl;
import net.seedschool.duong.service.UserService;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() throws Exception{
        Map<String,Object> resMap= accountService.getAllUsers();
        JSONPObject res = new JSONPObject("result", resMap);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id
    ) throws Exception{
        Map<String, Object> res= accountService.deleteUser(id);
        if(res.get("message").equals("delete success")) return new ResponseEntity<>(new JSONPObject("result",res),HttpStatus.OK);
        else return new ResponseEntity<>(new JSONPObject("result",res),HttpStatus.NOT_FOUND);


    }
    @PostMapping("/update")
    public  ResponseEntity<Object> updateUser(@PathVariable Long id,@RequestBody User userDetail)throws Exception{
        Map<String,Object> res = accountService.updateUser(id, userDetail);
        if(res.get("message").equals("update success")) return new ResponseEntity<>(new JSONPObject("result",res),HttpStatus.OK);
        else return new ResponseEntity<>(new JSONPObject("result",res),HttpStatus.NOT_FOUND);
    }
    @PutMapping("/add/user")
    public ResponseEntity<Object> addUser(@RequestBody User user) throws Exception{
        Map<String,Object> res = accountService.addUser(user);
        if(res.get("message").equals("add success")) return  new ResponseEntity<>(new JSONPObject("result",res),HttpStatus.OK);
        else return  new ResponseEntity<>(new JSONPObject("result",res),HttpStatus.NO_CONTENT);
    }
}
