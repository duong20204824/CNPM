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
//    public AccountController(UserService userService){ this.userService=userService;}
//    @GetMapping("/view")
//    public ResponseEntity<Object> getListUser(
//            @RequestParam(name="pageSize" , required = false, defaultValue = "30") String pageSize,
//            @RequestParam(name="currentPage", required = false,defaultValue = "1") String currentPage,
//            @RequestParam(name="keyword", required = false) String keyword
//    ) throws Exception{
//        Map<String,Object> resError = new HashMap<>();
//        JSONObject res;
//        if(pageSize==null && currentPage==null){
//            Map<String, Object> users=userService.getListUser(0,0,keyword);
//            res=new JSONObject(users);
//            return new ResponseEntity<>(res, HttpStatus.OK);
//        }
//        if(!isNumeric(pageSize) || !isNumeric(currentPage)){
//            resError.put("message","VALUE_NOT_BE_OBTAINED");
//            resError.put("error","CURRENT_PAGE_OR_NOT_BE_CHARACTER");
//            res=new JSONObject(resError);
//        }else {
//            int intPageSize=Integer.parseInt(pageSize);
//            int intCurrentPage=Integer.parseInt(currentPage);
//            Map<String,Object> reqMap=userService.getListUser(intPageSize,intCurrentPage,keyword);
//            res=new JSONObject(reqMap);
//        }
//        return new ResponseEntity<>(res,HttpStatus.OK);
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
}
