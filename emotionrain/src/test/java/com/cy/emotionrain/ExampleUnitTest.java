package com.cy.emotionrain;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        int i = 0;
        while (i < 10) {
            Random random = new Random();
            int a = Math.round(random.nextFloat());
            System.out.println(a);
            i++;
        }
    }
}