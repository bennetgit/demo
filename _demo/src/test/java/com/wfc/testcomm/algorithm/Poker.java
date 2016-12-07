package com.wfc.testcomm.algorithm;

import java.util.Arrays;

/*
 * 洗牌在生活中十分常见，现在需要写一个程序模拟洗牌的过程。
 * 现在需要洗2n张牌，从上到下依次是第1张，第2张，第3张一直到第2n张。首先，我们把这2n张牌分成两堆
 * ，左手拿着第1张到第n张（上半堆），右手拿着第n+1张到第2n张
 * （下半堆）。接着就开始洗牌的过程，先放下右手的最后一张牌，再放下左手的最后一张牌，接着放下右手的倒数第二张牌
 * ，再放下左手的倒数第二张牌，直到最后放下左手的第一张牌。接着把牌合并起来就可以了。
 * 例如有6张牌，最开始牌的序列是1,2,3,4,5,6。首先分成两组，左手拿着1
 * ,2,3；右手拿着4,5,6。在洗牌过程中按顺序放下了6,3,5,2,4,1。
 * 把这六张牌再次合成一组牌之后，我们按照从上往下的顺序看这组牌，就变成了序列1,4,2,5,3,6。
 * 现在给出一个原始牌组，请输出这副牌洗牌k次之后从上往下的序列。
 * 
 * 输入描述:
 * 第一行一个数T(T ≤ 100)，表示数据组数。对于每组数据，第一行两个数n,k(1 ≤ n,k ≤
 * 100)，接下来一行有2n个数a1,a2,…,a2n(1 ≤ ai ≤ 1000000000)。表示原始牌组从上到下的序列。
 * 输出描述:
 * 对于每组数据，输出一行，最终的序列。数字之间用空格隔开，不要在行末输出多余的空格。
 * 
 * 输入例子:
 * 3
 * 3 1
 * 1 2 3 4 5 6
 * 3 2
 * 1 2 3 4 5 6
 * 2 2
 * 1 1 1 1
 * 输出例子:
 * 1 4 2 5 3 6
 * 1 5 4 3 2 6
 * 1 1 1 1
 */
public class Poker {

	// solution:
	// for the 1th, a[i] = a[i+2]. 2th, a[i] = a[i+1]. what's more, a[0] is
	// constant

	public static void main(String[] args) {

		int retry = 1;
		int[] arrys = { 1, 2, 3, 4, 5, 6, 7, 8 };
		riffle(arrys, retry);
		System.out.println(Arrays.toString(arrys));
		Doshuffle(arrys, 4, retry);
		test(arrys, 4, retry);
	}
	
	public static void test(int[] nums, int n, int k){
	    if(k==0){
	        System.out.println(Arrays.toString(nums));
	        return;
	    }
	    int i=0, j=n, c=0;
	    int[] re=new int[2*n];
	    while(i<n){
	        re[c++]=nums[i++];
	        re[c++]=nums[j++];
	    }
	    test(re, n, k-1);
	}

	private static void Doshuffle(int[] a, int n, int k) {
		int[] b = new int[2 * n];

		for (int i = 0; i < 2 * n; i++) {
			int index = i + 1; // index是数组下标值加1
			for (int j = 0; j < k; j++) { // k层循环，改变index值
				if (index <= n) {
					index = 2 * index - 1;
				} else {
					index = 2 * (index - n);
				}
			}
			b[index - 1] = a[i]; // 完成k次洗牌后，将得到的新牌赋值给新的数组b

		}
		// 输出得到的洗牌顺序
		System.out.print(b[0]);
		for (int i = 1; i < 2 * n; i++) {
			System.out.print(" " + b[i]);
		}
		System.out.println();
	}

	private static void riffle(int[] poker, int retry) {
		int length = poker.length;
		int mid = length / 2;
		if (length <= 2) {
			return;
		}
		int temp = 0;
		while (retry > 0) {
			retry--;
			for (int i = 1; i < length - 2; i++) {
				if (i < mid - 1) {
					temp = poker[i];
					poker[i] = poker[i + 2];
					poker[i + 2] = temp;
				} else {
					temp = poker[i];
					poker[i] = poker[i + 1];
					poker[i + 1] = temp;
				}
			}
		}
	}

}
