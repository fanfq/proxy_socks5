package com.proxy.socks5;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Socks5Processor implements Runnable{

    private final BlockingQueue<Socket> queue;
    private final Socks5Handler handler = new Socks5Handler();

    public Socks5Processor(BlockingQueue<Socket> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = queue.take();
                handler.handle(client,false/*false:需要密码 "bigbyto","123456"，true:不需要秘密*/);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
