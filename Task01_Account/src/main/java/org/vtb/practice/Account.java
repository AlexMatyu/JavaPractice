package org.vtb.practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Account {
    private String ownerName;
    private HashMap<Currency, Integer> availableAccs;
    private Deque<Cancelable> actions;

    private class SaveInstance implements Loadable{
        private final String ownerName;
        private final HashMap<Currency, Integer> availableAccs;
        private final Deque<Cancelable> actions;

        public SaveInstance() {
            this.ownerName = Account.this.ownerName;
            this.availableAccs = new HashMap<>(Account.this.availableAccs);
            this.actions = new ArrayDeque<>(Account.this.actions);
        }

        public void load () {
            Account.this.ownerName = this.ownerName;
            Account.this.availableAccs = new HashMap<>(this.availableAccs);
            Account.this.actions = new ArrayDeque<>(this.actions);
        }
    }

    private Account () {};

    public Account(String ownerName) {
        verificationOwnerName(ownerName);

        this.ownerName = ownerName;
        this.availableAccs = new HashMap<>();
        this.actions = new ArrayDeque<Cancelable>();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        verificationOwnerName(ownerName);

        String oldName = this.ownerName;
        this.actions.push(() -> this.ownerName = oldName);

        this.ownerName = ownerName;
    }

    private void verificationOwnerName(String ownerName) {
        if (ownerName == null) throw new IllegalArgumentException("Owner name must not be null");
        if (ownerName.isEmpty()) throw new IllegalArgumentException("Owner name must not be empty");
    }

    public HashMap<Currency, Integer> getAvailableAccs() {
        HashMap<Currency, Integer> ret = new HashMap<>(this.availableAccs);
        return ret;
    }

    public void setCurrencyAmount (Currency cur, Integer sumValue) {
        if (availableAccs.containsKey(cur)) {
            Integer oldValue = this.availableAccs.get(cur);
            this.actions.push(() -> this.availableAccs.put(cur, oldValue));
        } else {
            this.actions.push(() -> this.availableAccs.remove(cur));
        }

        this.availableAccs.put(cur, sumValue);
    }

    @Override
    public String toString() {
        return "Account{" +
                "ownerName='" + ownerName + '\'' +
                ", availableAccs=" + availableAccs +
                '}';
    }

    public void undo () throws NoSuchElementException {
        if (this.actions.isEmpty()) throw new NoSuchElementException("There's nothing to cancel");
        this.actions.pop().undo();
    }

    public Loadable save () {
        return new SaveInstance();
    }
}
