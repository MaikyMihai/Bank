package net.maiky.banking;

import net.maiky.banking.database.UserDataRepository;
import net.maiky.banking.other.Log;
import net.maiky.banking.other.LogColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@Controller
public class Utils {

    @Autowired
    public UserDataRepository data;

    @GetMapping("/")
    public String home() {
        return ("home");
    }

    @GetMapping("/process/{title}/{description}/{backpage}")
    public String process(Model model, @PathVariable String title, @PathVariable String description, @PathVariable String backpage) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("backpage", backpage);
        return ("popup");
    }

    @PostMapping("/api/add/account")
    public String registerAccount(@ModelAttribute Account acc, Model model) {
        model.addAttribute("account", acc);
        acc.setId((int) (System.currentTimeMillis() % 100000 + new Random().nextInt(100)));
        acc.setMoney(0);
        acc.setRoles("USER");
        acc.setActive(true);
        acc.setLogs(new ArrayList<>());
        acc.setActiveCode(new Random().nextLong(100000));
        data.save(acc);
        System.out.println(acc.getUserName() + " _ _ _ " + acc.getPassword() + acc.getRoles());
        return "redirect:http://localhost:8080/process/Account created/Ti-ai creat un cont cu success/dashboard";
    }

    @GetMapping("/api/intern/send/{to}/{sum}")
    public String sendMoney(Principal principal, @PathVariable String to, @PathVariable double sum) {
        Account fromAccount = getAccount(principal.getName());
        Account toAccount = getAccount(to);
        if(fromAccount.getMoney() >= sum) {
            fromAccount.setMoney(fromAccount.getMoney()-sum);
            List<String> logs = fromAccount.getLogs();
            logs.add("false;Ai trimis " + sum + " lei lui " + to);
            fromAccount.setLogs(logs);
            List<String> logsTo = getAccount(to).getLogs();
            logs.add("true;Ai primit " + sum + " lei de la " + fromAccount.getUserName());
            getAccount(to).setLogs(logs);
            toAccount.setMoney(toAccount.getMoney()+sum);
            updateAccount(getAccount(principal.getName()), fromAccount);
            updateAccount(getAccount(to), toAccount);
        }
        return "redirect:http://localhost:8080/process/Completes/Plata%20ta%20a%20fost%20finalizata%20cu%20suces.%20Multumim%20pentru%20tranzactie/dashboard";
    }

    @GetMapping("/api/intern/debug/addmoney/{sum}")
    public String getMoney(Principal principal, @PathVariable double sum) {
        Account fromAccount = getAccount(principal.getName());
        fromAccount.setMoney(fromAccount.getMoney()+sum);
        List<String> logs = fromAccount.getLogs();
        logs.add("true;Ai primit " + sum + " lei din alimentare");
        fromAccount.setLogs(logs);
        updateAccount(getAccount(principal.getName()), fromAccount);
        return "redirect:http://localhost:8080/process/Success/Ai%20incarcat%20contul%20tau%20cu%20success/dashboard";
    }

    public void updateAccount(Account user, Account newAccount) {
        data.delete(user);
        data.save(newAccount);
    }

    public Account getAccount(String name) {
        for(Account account : data.findAll()) {
            if(account.getUserName().equals(name))
                return account;
        }
        return null;
    }
}
