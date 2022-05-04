package br.com.cod3r.proxy.bank.services;

import java.util.HashMap;
import java.util.Map;

import br.com.cod3r.proxy.bank.model.User;

public class Bank implements BankOperations {
	private Map<Long, User> userDatabase;
	
	public Bank() {
		userDatabase = new HashMap<Long, User>();
		userDatabase.put(123L, new User("User #1", 123L, "1234", 1000L));
		userDatabase.put(456L, new User("User #2", 456L, "9999", 200L));
	}

	@Override
	public void deposit(Long account, Long amount) {
		User user = userDatabase.get(account);
		if(user == null) {
			System.out.println("Conta Inválida");
			return;
		}
		user.setBalance(user.getBalance() + amount);
		System.out.println(String.format("%s +%d. Novo Saldo: %d", 
				user.getName(), amount, user.getBalance()));
	}

	@Override
	public void withdraw(Long account, String passwd, Long amount) {
		User user = userDatabase.get(account);
		if(user == null) {
			System.out.println("Conta Inválida");
			return;
		}
		if(!user.getPassword().equals(passwd)) {
			System.out.println("Senha invalida");
			return;
		}
		if(user.getBalance() < amount) {
			System.out.println("Você não tem recursos suficientes");
			return;
		}
		user.setBalance(user.getBalance() - amount);
		System.out.println(String.format("%s -%d. Novo Saldo: %d", 
				user.getName(), amount, user.getBalance()));
	}

	@Override
	public void changePassword(Long account, String oldPassword, String newPassword) {
		User user = userDatabase.get(account);
		if(user == null) {
			System.out.println("Conta Inválida");
			return;
		}
		if(!user.getPassword().equals(oldPassword)) {
			System.out.println("Senha invalida");
			return;
		}
		user.setPassword(newPassword);
		System.out.println(String.format("%s senha atualizada com sucesso!", 
				user.getName()));
	}

}
