package net.maiky.banking.database;

import net.maiky.banking.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDataRepository extends CrudRepository<Account, String> {
}
