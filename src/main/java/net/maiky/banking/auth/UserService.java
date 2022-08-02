package net.maiky.banking.auth;

import net.maiky.banking.Account;
import net.maiky.banking.database.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDataRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        for(Account account : userRepository.findAll()) {
            if (account.getUserName().equals(userName))
                return new AuthAuthority(account);
        }
        new UsernameNotFoundException("user not found");
        return null;
    }
}