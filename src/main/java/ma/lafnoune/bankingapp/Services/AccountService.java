package ma.lafnoune.bankingapp.Services;

import ma.lafnoune.bankingapp.DTO.BankAccountRequestDTO;
import ma.lafnoune.bankingapp.DTO.BankAccountResponseDTO;
import ma.lafnoune.bankingapp.Entities.BankAccount;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    List<BankAccountResponseDTO> getAllAccounts();
    BankAccountResponseDTO getAccountById(String id);
    void deleteAccount(String id);
}