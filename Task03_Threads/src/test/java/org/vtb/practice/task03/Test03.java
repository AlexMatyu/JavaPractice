package org.vtb.practice.task03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test03 {
    // В классе FractionTst на кэшируемый метод выставлено время жизни 2000 для упрощения тестирования
    @Test
    @DisplayName("Check Cache")
    void checkCache() {
        FractionTst fractionExt = new FractionTst(1, 4);
        FractionableTst fractionable = Utils.cache(fractionExt);
        double res1 = fractionable.doubleValue();
        double res2 = fractionable.doubleValue();

        Assertions.assertEquals(res1, res2);
        Assertions.assertEquals(fractionExt.methodCallCount, 1);
    }

    @Test
    @DisplayName("Check Mutator")
    void checkMutator() {
        FractionTst fractionExt = new FractionTst(1, 4);
        FractionableTst fractionable = Utils.cache(fractionExt);
        double res1 = fractionable.doubleValue();
        fractionable.doubleValue();

        fractionable.setNum(2);

        double res3 = fractionable.doubleValue();
        double res4 = fractionable.doubleValue();

        Assertions.assertNotEquals(res3, res1);
        Assertions.assertEquals(res3, res4);
        Assertions.assertEquals(fractionExt.methodCallCount, 2);
    }

    @Test
    @DisplayName("Check not cached method")
    void checkNotCachedMethod() {
        FractionTst fractionExt = new FractionTst(1, 4);
        FractionableTst fractionable = Utils.cache(fractionExt);
        double res1 = fractionable.doubleValueNotCached();
        double res2 = fractionable.doubleValueNotCached();

        Assertions.assertEquals(res1, res2);
        Assertions.assertEquals(fractionExt.methodCallCount, 2);
    }

    @Test
    @DisplayName("Check lifeTime")
    void checklifeTime() {
        FractionTst fractionExt = new FractionTst(1, 4);
        FractionableTst fractionable = Utils.cache(fractionExt);
        double res1 = fractionable.doubleValue();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double res2 = fractionable.doubleValue();

        Assertions.assertEquals(res1, res2);
        Assertions.assertEquals(fractionExt.methodCallCount, 2);
    }

    @Test
    @DisplayName("Check lifeTime extension")
    void checklifeTimeExt() {
        FractionTst fractionExt = new FractionTst(1, 4);
        FractionableTst fractionable = Utils.cache(fractionExt);
        double res1 = fractionable.doubleValue();
        double res2 = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            res2 = fractionable.doubleValue();
        }

        Assertions.assertEquals(res1, res2);
        Assertions.assertEquals(fractionExt.methodCallCount, 1);
    }
}
