package synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;
	private Lock banklock;// = new ReentrantLock();
	private Condition sufficientFunds;

	public Bank(int n, double initBalance) {
		this.accounts = new double[n];
		Arrays.fill(this.accounts, initBalance);
		banklock = new ReentrantLock();
		sufficientFunds = banklock.newCondition();
	}
	
	public void transfer(int from, int to, double amount) {
		//if(this.accounts[from] < amount) return;
		
		banklock.lock();
		try
		{
			while (this.accounts[from] < amount)
				try {
					sufficientFunds.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.println(Thread.currentThread());
			this.accounts[from] -= amount;
			
			System.out.printf(" %10.2f from %d to %d", amount, from,to);
			this.accounts[to] += amount;
			System.out.printf(" Total Balance %10.2f%n", getTotalBalance());
			
			sufficientFunds.signalAll();
		}
		finally
		{
			banklock.unlock();
		}
	}

	public double getTotalBalance() {
		// TODO Auto-generated method stub
		banklock.lock();
		try
		{
		double sum = 0;
		for(double a : this.accounts)
			sum += a;
		return sum;
		}
		finally
		{
			banklock.unlock();
		}
	}
	
	public int size() {
		return this.accounts.length;
	}
	
	

}
