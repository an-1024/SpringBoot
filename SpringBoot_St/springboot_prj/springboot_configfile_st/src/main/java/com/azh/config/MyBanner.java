package com.azh.config;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class MyBanner implements Banner {
    
    private static final String BANNER = "${AnsiColor.BRIGHT_YELLOW}\n" +
            " \n" +
            " \n" +
            "                           _ooOoo_\n" +
            "                          o8888888o\n" +
            "                          88\" . \"88\n" +
            "                          (| -_- |)\n" +
            "                          O\\  =  /O\n" +
            "                       ____/`---'\\____\n" +
            "                     .'  \\\\|     |//  `.\n" +
            "                    /  \\\\|||  :  |||//  \\\n" +
            "                   /  _||||| -:- |||||-  \\\n" +
            "                   |   | \\\\\\  -  /// |   |\n" +
            "                   | \\_|  ''\\---/''  |   |\n" +
            "                   \\  .-\\__  `-`  ___/-. /\n" +
            "                 ___`. .'  /--.--\\  `. . __\n" +
            "              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
            "             | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
            "             \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
            "        ======`-.____`-.___\\_____/___.-`____.-'======\n" +
            "                           `=---='\n" +
            "        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
            "               // 佛祖保佑 永不宕机 永无BUG //\n" +
            " ";
    
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.printf(BANNER);
        out.println();
    }
}
