package com.ccsu.jc.tvbank.task;

import java.util.Timer;


public class TestMain {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //查询出有多少用户 每隔2分钟查询一次
        timer.schedule(new CallingMethod(), 1000, 120000);
    }

}
