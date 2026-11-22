package ma.lafnoune.bankingapp.Repositories;

import ma.lafnoune.bankingapp.Entities.BankAccount;
import ma.lafnoune.bankingapp.Entities.Customer;
import ma.lafnoune.bankingapp.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
