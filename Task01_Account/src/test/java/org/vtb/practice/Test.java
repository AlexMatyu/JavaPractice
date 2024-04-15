package org.vtb.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.NoSuchElementException;

class Test {
    @org.junit.jupiter.api.Test
    @DisplayName("Checking the name setting by creating an object")
    void createOwnerName() {
        Assertions.assertDoesNotThrow(() -> new Account("John"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account(""));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Checking the name setting by setting the name")
    void setOwnerName() {
        Account account = new Account("John");
        Assertions.assertDoesNotThrow(() -> account.setOwnerName("Bill"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.setOwnerName(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.setOwnerName(""));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Checking the resulting currency-value pairs")
    void getAvailableAccs() {
        Account account = new Account("John");
        account.setCurrencyAmount(Currency.RUB, 5);
        HashMap<Currency, Integer> pairsCurrencyValue = account.getAvailableAccs();
        pairsCurrencyValue.put(Currency.RUB, 20);

        Assertions.assertNotEquals(pairsCurrencyValue.get(Currency.RUB), account.getAvailableAccs().get(Currency.RUB));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Checking undo()")
    void checkUndo() {
        Account account = new Account("John");
        account.setCurrencyAmount(Currency.RUB, 1);
        account.setOwnerName("Bill");

        Assertions.assertDoesNotThrow(() -> account.undo());
        Assertions.assertEquals(account.getOwnerName(), "John");

        account.setCurrencyAmount(Currency.USD, 3);
        account.setCurrencyAmount(Currency.RUB, 2);

        Assertions.assertDoesNotThrow(() -> account.undo());
        Assertions.assertEquals(account.getAvailableAccs().get(Currency.USD), 3);
        Assertions.assertEquals(account.getAvailableAccs().get(Currency.RUB), 1);
        Assertions.assertDoesNotThrow(() -> account.undo());
        Assertions.assertEquals(account.getAvailableAccs().get(Currency.USD), null);
        Assertions.assertDoesNotThrow(() -> account.undo());
        Assertions.assertThrows(NoSuchElementException.class, () -> account.undo());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Checking save/load")
    void checkLoadable() {
        Account account = new Account("John");
        account.setOwnerName("Bill");
        account.setCurrencyAmount(Currency.RUB, 1);

        Loadable savePoint = account.save();
        String tmpOwnerName = account.getOwnerName();
        HashMap<Currency,Integer> tmpAvailableAccs = account.getAvailableAccs();

        account.setOwnerName("Boris");
        account.setCurrencyAmount(Currency.RUB, 2);
        account.setCurrencyAmount(Currency.RUB, 4);
        account.undo();
        account.setCurrencyAmount(Currency.USD, 5);

        savePoint.load();

        Assertions.assertEquals(account.getOwnerName(), tmpOwnerName);
        Assertions.assertEquals(account.getAvailableAccs().get(Currency.RUB), tmpAvailableAccs.get(Currency.RUB));
        Assertions.assertEquals(account.getAvailableAccs().get(Currency.USD), tmpAvailableAccs.get(Currency.USD));
    }
}