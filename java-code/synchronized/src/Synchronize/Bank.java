package Synchronize;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;

	public Bank(int n, double initBalance) {
		this.accounts = new double[n];
		Arrays.fill(this.accounts, initBalance);
	}
	
	public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
		while (this.accounts[from] < amount)
			wait();
		
		System.out.println(Thread.currentThread());
		this.accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from,to);
		
		this.accounts[to] += amount;
		System.out.printf(" Total Balance %10.2f%n", getTotalBalance());
		
		notifyAll();
	}

	public synchronized double getTotalBalance() {
		double sum = 0;
		for(double a : this.accounts)
			sum += a;
		return sum;
	}
	
	public int size() {
		return this.accounts.length;
	}
	
	

}
