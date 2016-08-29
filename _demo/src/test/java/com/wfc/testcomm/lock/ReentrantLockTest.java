package com.wfc.testcomm.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private void reentrantLockTest() {
        ReentrantLock lock = new ReentrantLock();
        lock.tryLock();
    }
}
