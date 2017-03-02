/*
 * Decompiled with CFR 0_119.
 * 
 * Could not load the following classes:
 *  scala.MatchError
 *  scala.Option
 *  scala.Predef
 *  scala.Predef$
 *  scala.Product
 *  scala.Tuple1
 *  scala.Tuple1$
 *  scala.Tuple2
 *  scala.Tuple2$
 *  scala.Tuple3
 *  scala.Tuple3$
 *  scala.Tuple4
 *  scala.Tuple4$
 *  scala.collection.mutable.ArrayOps
 *  scala.runtime.BoxedUnit
 */
package dotty;

import dotty.Pair;
import dotty.TupleImplN;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.mutable.ArrayOps;
import scala.runtime.BoxedUnit;

public final class TupleCons$ {
    public static final TupleCons$ MODULE$;

    public static {
        new dotty.TupleCons$();
    }

    public TupleCons$() {
        MODULE$ = this;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Product apply(Object h, Object t) {
        var3_3 = t;
        if (var3_3 instanceof TupleImplN) ** GOTO lbl51
        v0 = BoxedUnit.UNIT;
        var8_8 = var3_3;
        if (v0 == null) ** GOTO lbl8
        if (v0.equals(var8_8)) ** GOTO lbl-1000
        ** GOTO lbl12
lbl8: // 1 sources:
        if (var8_8 == null) lbl-1000: // 2 sources:
        {
            var9_9 = (BoxedUnit)var3_3;
            v1 = new Tuple1(h);
            return (Product)v1;
        }
lbl12: // 3 sources:
        if (var3_3 instanceof Tuple1) {
            var10_10 = (Tuple1)var3_3;
            var11_11 = Tuple1..MODULE$.unapply((Tuple1)var3_3);
            if (!var11_11.isEmpty()) {
                e1 = var12_12 = var11_11.get();
                v1 = new Tuple2(h, e1);
                return (Product)v1;
            }
        }
        if (var3_3 instanceof Tuple2) {
            var14_14 = (Tuple2)var3_3;
            var15_15 = Tuple2..MODULE$.unapply((Tuple2)var3_3);
            if (!var15_15.isEmpty()) {
                var16_16 = (Tuple2)var15_15.get();
                e1 = var16_16._1();
                e2 = var16_16._2();
                v1 = new Tuple3(h, e1, e2);
                return (Product)v1;
            }
        }
        if (var3_3 instanceof Tuple3) {
            var19_19 = (Tuple3)var3_3;
            var20_20 = Tuple3..MODULE$.unapply((Tuple3)var3_3);
            if (!var20_20.isEmpty()) {
                var21_21 = (Tuple3)var20_20.get();
                e1 = var21_21._1();
                e2 = var21_21._2();
                e3 = var21_21._3();
                v1 = new Tuple4(h, e1, e2, e3);
                return (Product)v1;
            }
        }
        if (!(var3_3 instanceof Tuple4)) {
            throw new MatchError(var3_3);
        }
        var25_25 = (Tuple4)var3_3;
        var26_26 = Tuple4..MODULE$.unapply((Tuple4)var3_3);
        if (var26_26.isEmpty() != false) throw new MatchError(var3_3);
        var27_27 = (Tuple4)var26_26.get();
        e1 = var27_27._1();
        e2 = var27_27._2();
        e3 = var27_27._3();
        e4 = var27_27._4();
        a = new Object[]{h, e1, e2, e3, e4};
        v1 = new TupleImplN(a);
        return (Product)v1;
lbl51: // 1 sources:
        impln = (TupleImplN)var3_3;
        underlying = impln.underlying();
        s = Predef..MODULE$.genericArrayOps((Object)underlying).size();
        a = new Object[s + 1];
        a[0] = h;
        do {
            if (s == 0) {
                v1 = new TupleImplN(a);
                return (Product)v1;
            }
            a[s] = underlying[s - 1];
            --s;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Pair unapply(Product t) {
        var2_2 = t;
        if (var2_2 instanceof TupleImplN) ** GOTO lbl5
        if (var2_2 instanceof Tuple1) ** GOTO lbl11
        ** GOTO lbl17
lbl5: // 1 sources:
        impln = (TupleImplN)var2_2;
        underlying = impln.underlying();
        s = Predef..MODULE$.genericArrayOps((Object)underlying).size();
        if (s != 5) ** GOTO lbl48
        v0 = new Tuple4(underlying[1], underlying[2], underlying[3], underlying[4]);
        ** GOTO lbl56
lbl11: // 1 sources:
        var9_9 = (Tuple1)var2_2;
        var10_10 = Tuple1..MODULE$.unapply((Tuple1)var2_2);
        if (!var10_10.isEmpty()) {
            e1 = var11_11 = var10_10.get();
            v1 = new Pair(e1, (Object)BoxedUnit.UNIT);
            return v1;
        }
lbl17: // 3 sources:
        if (var2_2 instanceof Tuple2) {
            var13_13 = (Tuple2)var2_2;
            var14_14 = Tuple2..MODULE$.unapply((Tuple2)var2_2);
            if (!var14_14.isEmpty()) {
                var15_15 = (Tuple2)var14_14.get();
                e1 = var15_15._1();
                e2 = var15_15._2();
                v1 = new Pair(e1, (Object)new Tuple1(e2));
                return v1;
            }
        }
        if (var2_2 instanceof Tuple3) {
            var18_18 = (Tuple3)var2_2;
            var19_19 = Tuple3..MODULE$.unapply((Tuple3)var2_2);
            if (!var19_19.isEmpty()) {
                var20_20 = (Tuple3)var19_19.get();
                e1 = var20_20._1();
                e2 = var20_20._2();
                e3 = var20_20._3();
                v1 = new Pair(e1, (Object)new Tuple2(e2, e3));
                return v1;
            }
        }
        if (!(var2_2 instanceof Tuple4)) {
            throw new MatchError((Object)var2_2);
        }
        var24_24 = (Tuple4)var2_2;
        var25_25 = Tuple4..MODULE$.unapply((Tuple4)var2_2);
        if (var25_25.isEmpty() != false) throw new MatchError((Object)var2_2);
        var26_26 = (Tuple4)var25_25.get();
        e1 = var26_26._1();
        e2 = var26_26._2();
        e3 = var26_26._3();
        e4 = var26_26._4();
        v1 = new Pair(e1, (Object)new Tuple3(e2, e3, e4));
        return v1;
lbl48: // 1 sources:
        a = new Object[--s];
        do {
            if (s == 0) {
                v0 = new TupleImplN(a);
                break;
            }
            a[s - 1] = underlying[s];
            --s;
        } while (true);
lbl56: // 2 sources:
        tail = v0;
        head = underlying[0];
        v1 = new Pair(head, (Object)tail);
        return v1;
    }
}
