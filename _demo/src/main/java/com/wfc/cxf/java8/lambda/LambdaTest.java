package com.wfc.cxf.java8.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {

	public static void main(String[] args) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		// invoices.sort(comparingDouble(Invoice::amount));
		Invoice tt = new Invoice();

		tt.amount = 2.0;
		// Callable<Integer> c2 = true ? (() -> 42) : (() -> 24);
		// try {
		// System.out.println(c2.call());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		TTT ss = () -> System.out.print("hello lambda");
		ss.say();
	}

	static class Invoice {
		Double amount;

		public Double amount() {
			return amount;
		}
	}

}

// @FunctionalInterface
interface TTT {
	void say();
	
}


