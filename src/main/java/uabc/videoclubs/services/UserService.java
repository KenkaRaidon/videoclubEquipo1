package uabc.videoclubs.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uabc.videoclubs.entities.Staff;
import uabc.videoclubs.repository.StaffRepository;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private StaffRepository staffRepository;

    private Logger logger =LoggerFactory.getLogger(UserService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Staff usuario =staffRepository.findByUsername(username);
        if(usuario==null){
            logger.error("Error en el login: No existe el usuario: '" + username + "' en el sistema");
            throw new UsernameNotFoundException("usuario: '" + username + "' no existe en el sistema");
        }
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("EMPLEADO"));
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    public Staff save(Staff staff){
        return staffRepository.save(staff);
    }

    public String getStaffByID(Integer id){
        staffRepository.findById(id);
        return null;
    }

    public String findByEmail(String email){
        return staffRepository.findByEmail(email);
    }

    public Staff findStaffByUsername(String username){
        return staffRepository.findByUsername(username);
    }
}
