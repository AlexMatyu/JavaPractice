package org.vtb.practice.task03;

public class FractionTst implements FractionableTst {
    int methodCallCount = 0;
    private int num;
    private int denum;

    public FractionTst(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    @Cache(2000)
    public double doubleValue() {
        methodCallCount++;
        return (double) num/denum;
    }

    public double doubleValueNotCashed() {
        methodCallCount++;
        return (double) num/denum;
    }
}
