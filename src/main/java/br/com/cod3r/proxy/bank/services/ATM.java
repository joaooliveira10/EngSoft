package br.com.cod3r.proxy.bank.services;

public class ATM implements BankOperations {
	private BankOperations bank;
	
	public ATM(BankOperations bank) {
		this.bank = bank;
	}

	@Override
	public void deposit(Long account, Long amount) {
		System.out.println("Proxy ATM enviando solicita��o ao banco");
		bank.deposit(account, amount);
	}

	@Override
	public void withdraw(Long account, String passwd, Long amount) {
		if(amount > 300) {
			System.out.println("Voc� n�o pode sacar valores acima de 300 aqui");
			return;
		}
		System.out.println("Proxy ATM enviando solicita��o ao banco");
		bank.withdraw(account, passwd, amount);
	}

	@Override
	public void changePassword(Long account, String oldPassword, String newPassword) {
		System.out.println("Voc� deve ir ao banco para realizar esta opera��o");
		return;
	}

}
