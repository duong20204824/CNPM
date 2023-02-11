package net.seedschool.duong.service;

import net.seedschool.duong.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface AccountService {
    public Map<String,Object> getAllUsers() throws Exception;
    public Map<String,Object> deleteUser(Long id) throws Exception;
    public  Map<String,Object> updateUser(Long id, User userDetail) throws  Exception;
    public Map<String,Object> addUser(User user) throws Exception;
}
