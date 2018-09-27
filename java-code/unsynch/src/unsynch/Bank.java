package unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;
	private Lock banklock = new ReentrantLock();

	public Bank(int n, double initBalance) {
		this.accounts = new double[n];
		Arrays.fill(this.accounts, initBalance);
	}
	
	public void transfer(int from, int to, double amount) {
		if(this.accounts[from] < amount) return;
		
		banklock.lock();
		try
		{
			System.out.println(Thread.currentThread());
			this.accounts[from] -= amount;
			
			System.out.printf(" %10.2f from %d to %d", amount, from,to);
			this.accounts[to] += amount;
			System.out.printf(" Total Balance %10.2f%n", getTotalBalance());
		}
		finally
		{
			banklock.unlock();
		}
	}

	public double getTotalBalance() {
		// TODO Auto-generated method stub
		double sum = 0;
		for(double a : this.accounts)
			sum += a;
		return sum;
	}
	
	public int size() {
		return this.accounts.length;
	}
	
	

}
