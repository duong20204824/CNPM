package net.seedschool.duong.service;

import net.seedschool.duong.model.User;
import net.seedschool.duong.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Map<String,Object> getAllUsers() throws Exception{
        List<User> user= userRepository.findAll();
        Map<String,Object> res= new HashMap<>();
        res.put("All user", user);
    return res;
    }

    public  Map<String,Object> deleteUser(Long id) throws Exception{
        Map<String,Object> res=new HashMap<>();
        Optional<User> user= userRepository.findById(id);
        if(!user.isPresent()){
            res.put("error","account doesn't exist");
        }else{
            User userneeddeleted=user.get();
            userRepository.delete(userneeddeleted);
            res.put("message","delete success");
        }
        return res;
    }
    public  Map<String,Object> updateUser(Long id, User userDetail) throws Exception{
        Map<String, Object> res = new HashMap<>();
        Optional<User> userupdate= userRepository.findById(id);
        if(!userupdate.isPresent()){
            res.put("error","account doesn't exist");
        }else{
            User userneedupdate=userupdate.get();
            userneedupdate.setId(userDetail.getId());
            userneedupdate.setEmail(userDetail.getEmail());
            userneedupdate.setPassword(userDetail.getPassword());
            userneedupdate.setFirstName(userDetail.getFirstName());
            userneedupdate.setLastName(userDetail.getLastName());
            userneedupdate.setRole(userDetail.getRole());
            res.put("message","update success");
        }
        return res;
    }
}
