package ma.lafnoune.bankingapp.Services;

import ma.lafnoune.bankingapp.DTO.BankAccountRequestDTO;
import ma.lafnoune.bankingapp.DTO.BankAccountResponseDTO;
import ma.lafnoune.bankingapp.Entities.BankAccount;
import ma.lafnoune.bankingapp.Mappers.AccountMapper;
import ma.lafnoune.bankingapp.Repositories.BankAccountRepository;
import ma.lafnoune.bankingapp.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount().builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccountResponseDTO(savedBankAccount);

        //L'utilité de Mapper nous évite de répéter ces blocs de code
//        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO().builder()
//                .id(savedBankAccount.getId())
//                .createdAt(savedBankAccount.getCreatedAt())
//                .balance(savedBankAccount.getBalance())
//                .type(savedBankAccount.getType())
//                .currency(savedBankAccount.getCurrency())
//                .build();
//        bankAccountResponseDTO.setId(bankAccount.getId());
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        // if (bankAccount.getId() != null) account.setId(bankAccount.getId());
        if (bankAccountDTO.getBalance() != null) account.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getCurrency() != null) account.setCurrency(bankAccountDTO.getCurrency());
        if (bankAccountDTO.getType() != null) account.setType(bankAccountDTO.getType());

        BankAccount savedBankAccount = bankAccountRepository.save(account);
        return accountMapper.fromBankAccountResponseDTO(savedBankAccount);
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(accountMapper::fromBankAccountResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return accountMapper.fromBankAccountResponseDTO(account);
    }

    @Override
    public void deleteAccount(String id) {
        if (!bankAccountRepository.existsById(id)) throw new AccountNotFoundException(id);
        bankAccountRepository.deleteById(id);
    }
}
