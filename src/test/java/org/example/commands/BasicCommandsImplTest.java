package org.example.commands;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class BasicCommandsImplTest {

    BasicCommands<String, Integer> basicCommands = new BasicCommandsImpl<String, Integer>();


    @Test
    void set() {
        basicCommands.set("a", 10);
        Assert.assertEquals(10, Integer.parseInt(String.valueOf(basicCommands.getDataStore().get("a"))));
    }

    @Test
    void get() {
        basicCommands.set("ex", 100);
        Assert.assertEquals(100, Integer.parseInt(String.valueOf(basicCommands.getDataStore().get("ex"))));
    }

    @Test
    void unset() {
        basicCommands.set("yay", 500);
        basicCommands.unset("yay");
        Assert.assertNull(basicCommands.getDataStore().get("yay"));
    }

    @Test
    void numEqualTo() {
        basicCommands.set("yay", 500);
        basicCommands.set("ex", 500);
        basicCommands.set("lee", 500);
        basicCommands.set("aaa", 500);
        Assert.assertEquals(4, basicCommands.numEqualTo(500));
    }

    @Test
    void getDataStore() {
        basicCommands.set("yay", 10);
        basicCommands.set("ex", 20);
        basicCommands.set("lee", 40);
        Assert.assertEquals(3, basicCommands.getDataStore().size());
    }
}