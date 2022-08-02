package net.maiky.banking.routes;

import net.maiky.banking.Account;
import net.maiky.banking.database.UserDataRepository;
import net.maiky.banking.other.Log;
import net.maiky.banking.other.LogColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardRoute {

    @Autowired
    public UserDataRepository data;

    @GetMapping("/register")
    public String registerAccount(Model model) {
        model.addAttribute("account", new Account("Username", "8yf49f7ge9hdfba94towidhfbowufbw"));
        return "register";
    }

    @GetMapping("/dashboard")
    public String user(Principal principal, Model model) {
        for(Account account : data.findAll())
            if(account.getUserName().equals(principal.getName())) {
                model.addAttribute("money", account.getMoney() + "");
                List<Log> logs = new ArrayList<>();
                for (String s : account.getLogs()) {
                    logs.add(new Log(s.split(";")[1], Boolean.parseBoolean(s.split(";")[0])));
                    System.out.println(logs);
                }
                model.addAttribute("logs", logs);
            }
        return ("dash");
    }

    @GetMapping("/transfer")
    public String transfer(Principal principal, Model model) {
        for(Account account : data.findAll())
            if(account.getUserName().equals(principal.getName())) {
                model.addAttribute("money", account.getMoney() + "");
            }
        return ("transfer");
    }

    @GetMapping("/alimentare")
    public String aliemntare(Principal principal, Model model) {
        for(Account account : data.findAll())
            if(account.getUserName().equals(principal.getName())) {
                model.addAttribute("money", account.getMoney() + "");
            }
        return ("addmoney");
    }
}

