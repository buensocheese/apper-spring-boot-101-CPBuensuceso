package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account= accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        return responseList;
    }

    @PutMapping("{accountId}")
    public AccountUpdateResponse updateAccount(@PathVariable String accountId, @RequestBody UpdateAccountRequest request) {
        Account updatedAccount = accountService.update(accountId, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        AccountUpdateResponse response = new AccountUpdateResponse();
        response.setLastUpdate(updatedAccount.getLastUpdated());

        return response;
    }

    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) {
        accountService.delete(accountId);
    }

    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());

        return response;
    }
}
