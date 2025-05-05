package dio.Projeto_API_REST.controller.dto;

import java.math.BigDecimal;

import dio.Projeto_API_REST.model.Account;

public record AccountDto(Long acount_id, String number, String agency, BigDecimal balance, BigDecimal limit) {

    public AccountDto(Account model) {
        this(model.getAccount_Id(), model.getNumber(), model.getAgency(), model.getBalance(), model.getLimit());
    }

    public Account toModel() {
        Account model = new Account();
        model.setAccount_Id(this.acount_id);
        model.setNumber(this.number);
        model.setAgency(this.agency);
        model.setBalance(this.balance);
        model.setLimit(this.limit);
        return model;
    }
}