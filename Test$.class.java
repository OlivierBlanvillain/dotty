/*
 * Decompiled with CFR 0_119.
 * 
 * Could not load the following classes:
 *  scala.MatchError
 *  scala.Predef
 *  scala.Predef$
 *  scala.Product
 *  scala.runtime.BoxedUnit
 *  scala.runtime.BoxesRunTime
 */
import dotty.Pair;
import dotty.TupleCons$;
import scala.MatchError;
import scala.Predef;
import scala.Product;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class Test$ {
    public static final Test$ MODULE$;

    public static {
        new Test$();
    }

    public Test$() {
        MODULE$ = this;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void main(String[] args) {
        Box box;
        Product x4;
        Box x1;
        Product product;
        Product x6;
        Pair pair;
        Product x5;
        Product x2;
        Product x3;
        Box box2 = x1 = Box$.MODULE$.apply(TupleCons$.MODULE$.apply("a", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)2), (Object)TupleCons$.MODULE$.apply(A$.MODULE$.apply(3, 4), (Object)BoxedUnit.UNIT))));
        if (Box$.MODULE$.unapply(box2) == null || (pair = TupleCons$.MODULE$.unapply(product = (box = Box$.MODULE$.unapply(box2))._1())).isEmpty()) throw new MatchError((Object)box2);
        Pair pair2 = pair.get();
        String a3 = (String)pair2._1();
        Product product2 = (Product)pair2._2();
        Pair pair3 = TupleCons$.MODULE$.unapply(product2);
        if (pair3.isEmpty()) throw new MatchError((Object)box2);
        Pair pair4 = pair3.get();
        int b3 = BoxesRunTime.unboxToInt((Object)pair4._1());
        Product product3 = (Product)pair4._2();
        Pair pair5 = TupleCons$.MODULE$.unapply(product3);
        if (pair5.isEmpty()) throw new MatchError((Object)box2);
        Pair pair6 = pair5.get();
        A a = (A)pair6._1();
        BoxedUnit boxedUnit = (BoxedUnit)pair6._2();
        if (A$.MODULE$.unapply(a) == null) throw new MatchError((Object)box2);
        A a2 = A$.MODULE$.unapply(a);
        int a1 = a2._1();
        int a22 = a2._2();
        BoxedUnit boxedUnit2 = BoxedUnit.UNIT;
        BoxedUnit boxedUnit3 = boxedUnit;
        if (boxedUnit2 == null ? boxedUnit3 != null : !boxedUnit2.equals((Object)boxedUnit3)) throw new MatchError((Object)box2);
        BoxedUnit boxedUnit4 = boxedUnit;
        String string = a3;
        String string2 = "a";
        Predef..MODULE$.assert(!(string == null ? string2 != null : !string.equals(string2)));
        Predef..MODULE$.assert(b3 == 2);
        Predef..MODULE$.assert(a1 == 3);
        Predef..MODULE$.assert(a22 == 4);
        Product product4 = x2 = TupleCons$.MODULE$.apply("a", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)2), (Object)BoxedUnit.UNIT));
        Pair pair7 = TupleCons$.MODULE$.unapply(product4);
        if (pair7.isEmpty()) throw new MatchError((Object)product4);
        Pair pair8 = pair7.get();
        String a23 = (String)pair8._1();
        Product product5 = (Product)pair8._2();
        Pair pair9 = TupleCons$.MODULE$.unapply(product5);
        if (pair9.isEmpty()) throw new MatchError((Object)product4);
        Pair pair10 = pair9.get();
        int b2 = BoxesRunTime.unboxToInt((Object)pair10._1());
        BoxedUnit boxedUnit5 = (BoxedUnit)pair10._2();
        BoxedUnit boxedUnit6 = BoxedUnit.UNIT;
        BoxedUnit boxedUnit7 = boxedUnit5;
        if (boxedUnit6 == null ? boxedUnit7 != null : !boxedUnit6.equals((Object)boxedUnit7)) throw new MatchError((Object)product4);
        BoxedUnit boxedUnit8 = boxedUnit5;
        String string3 = a23;
        String string4 = "a";
        Predef..MODULE$.assert(!(string3 == null ? string4 != null : !string3.equals(string4)));
        Predef..MODULE$.assert(b2 == 2);
        Product product6 = x3 = TupleCons$.MODULE$.apply("a", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)2), (Object)TupleCons$.MODULE$.apply("c", (Object)BoxedUnit.UNIT)));
        Pair pair11 = TupleCons$.MODULE$.unapply(product6);
        if (pair11.isEmpty()) throw new MatchError((Object)product6);
        Pair pair12 = pair11.get();
        String a32 = (String)pair12._1();
        Product product7 = (Product)pair12._2();
        Pair pair13 = TupleCons$.MODULE$.unapply(product7);
        if (pair13.isEmpty()) throw new MatchError((Object)product6);
        Pair pair14 = pair13.get();
        int b32 = BoxesRunTime.unboxToInt((Object)pair14._1());
        Product product8 = (Product)pair14._2();
        Pair pair15 = TupleCons$.MODULE$.unapply(product8);
        if (pair15.isEmpty()) throw new MatchError((Object)product6);
        Pair pair16 = pair15.get();
        String c3 = (String)pair16._1();
        BoxedUnit boxedUnit9 = (BoxedUnit)pair16._2();
        BoxedUnit boxedUnit10 = BoxedUnit.UNIT;
        BoxedUnit boxedUnit11 = boxedUnit9;
        if (boxedUnit10 == null ? boxedUnit11 != null : !boxedUnit10.equals((Object)boxedUnit11)) throw new MatchError((Object)product6);
        BoxedUnit boxedUnit12 = boxedUnit9;
        String string5 = a32;
        String string6 = "a";
        Predef..MODULE$.assert(!(string5 == null ? string6 != null : !string5.equals(string6)));
        Predef..MODULE$.assert(b32 == 2);
        String string7 = c3;
        String string8 = "c";
        Predef..MODULE$.assert(!(string7 == null ? string8 != null : !string7.equals(string8)));
        Product product9 = x4 = TupleCons$.MODULE$.apply("a", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)2), (Object)TupleCons$.MODULE$.apply("c", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)4), (Object)BoxedUnit.UNIT))));
        Pair pair17 = TupleCons$.MODULE$.unapply(product9);
        if (pair17.isEmpty()) throw new MatchError((Object)product9);
        Pair pair18 = pair17.get();
        String a4 = (String)pair18._1();
        Product product10 = (Product)pair18._2();
        Pair pair19 = TupleCons$.MODULE$.unapply(product10);
        if (pair19.isEmpty()) throw new MatchError((Object)product9);
        Pair pair20 = pair19.get();
        int b4 = BoxesRunTime.unboxToInt((Object)pair20._1());
        Product product11 = (Product)pair20._2();
        Pair pair21 = TupleCons$.MODULE$.unapply(product11);
        if (pair21.isEmpty()) throw new MatchError((Object)product9);
        Pair pair22 = pair21.get();
        String c4 = (String)pair22._1();
        Product product12 = (Product)pair22._2();
        Pair pair23 = TupleCons$.MODULE$.unapply(product12);
        if (pair23.isEmpty()) throw new MatchError((Object)product9);
        Pair pair24 = pair23.get();
        int d4 = BoxesRunTime.unboxToInt((Object)pair24._1());
        BoxedUnit boxedUnit13 = (BoxedUnit)pair24._2();
        BoxedUnit boxedUnit14 = BoxedUnit.UNIT;
        BoxedUnit boxedUnit15 = boxedUnit13;
        if (boxedUnit14 == null ? boxedUnit15 != null : !boxedUnit14.equals((Object)boxedUnit15)) throw new MatchError((Object)product9);
        BoxedUnit boxedUnit16 = boxedUnit13;
        String string9 = a4;
        String string10 = "a";
        Predef..MODULE$.assert(!(string9 == null ? string10 != null : !string9.equals(string10)));
        Predef..MODULE$.assert(b4 == 2);
        String string11 = c4;
        String string12 = "c";
        Predef..MODULE$.assert(!(string11 == null ? string12 != null : !string11.equals(string12)));
        Predef..MODULE$.assert(d4 == 4);
        Product product13 = x5 = TupleCons$.MODULE$.apply("a", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)2), (Object)TupleCons$.MODULE$.apply("c", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)4), (Object)TupleCons$.MODULE$.apply("e", (Object)BoxedUnit.UNIT)))));
        Pair pair25 = TupleCons$.MODULE$.unapply(product13);
        if (pair25.isEmpty()) throw new MatchError((Object)product13);
        Pair pair26 = pair25.get();
        String a5 = (String)pair26._1();
        Product product14 = (Product)pair26._2();
        Pair pair27 = TupleCons$.MODULE$.unapply(product14);
        if (pair27.isEmpty()) throw new MatchError((Object)product13);
        Pair pair28 = pair27.get();
        int b5 = BoxesRunTime.unboxToInt((Object)pair28._1());
        Product product15 = (Product)pair28._2();
        Pair pair29 = TupleCons$.MODULE$.unapply(product15);
        if (pair29.isEmpty()) throw new MatchError((Object)product13);
        Pair pair30 = pair29.get();
        String c5 = (String)pair30._1();
        Product product16 = (Product)pair30._2();
        Pair pair31 = TupleCons$.MODULE$.unapply(product16);
        if (pair31.isEmpty()) throw new MatchError((Object)product13);
        Pair pair32 = pair31.get();
        int d5 = BoxesRunTime.unboxToInt((Object)pair32._1());
        Product product17 = (Product)pair32._2();
        Pair pair33 = TupleCons$.MODULE$.unapply(product17);
        if (pair33.isEmpty()) throw new MatchError((Object)product13);
        Pair pair34 = pair33.get();
        String e5 = (String)pair34._1();
        BoxedUnit boxedUnit17 = (BoxedUnit)pair34._2();
        BoxedUnit boxedUnit18 = BoxedUnit.UNIT;
        BoxedUnit boxedUnit19 = boxedUnit17;
        if (boxedUnit18 == null ? boxedUnit19 != null : !boxedUnit18.equals((Object)boxedUnit19)) throw new MatchError((Object)product13);
        BoxedUnit boxedUnit20 = boxedUnit17;
        String string13 = a5;
        String string14 = "a";
        Predef..MODULE$.assert(!(string13 == null ? string14 != null : !string13.equals(string14)));
        Predef..MODULE$.assert(b5 == 2);
        String string15 = c5;
        String string16 = "c";
        Predef..MODULE$.assert(!(string15 == null ? string16 != null : !string15.equals(string16)));
        Predef..MODULE$.assert(d5 == 4);
        String string17 = e5;
        String string18 = "e";
        Predef..MODULE$.assert(!(string17 == null ? string18 != null : !string17.equals(string18)));
        Product product18 = x6 = TupleCons$.MODULE$.apply("a", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)2), (Object)TupleCons$.MODULE$.apply("c", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)4), (Object)TupleCons$.MODULE$.apply("e", (Object)TupleCons$.MODULE$.apply(BoxesRunTime.boxToInteger((int)6), (Object)BoxedUnit.UNIT))))));
        Pair pair35 = TupleCons$.MODULE$.unapply(product18);
        if (pair35.isEmpty()) throw new MatchError((Object)product18);
        Pair pair36 = pair35.get();
        String a6 = (String)pair36._1();
        Product product19 = (Product)pair36._2();
        Pair pair37 = TupleCons$.MODULE$.unapply(product19);
        if (pair37.isEmpty()) throw new MatchError((Object)product18);
        Pair pair38 = pair37.get();
        int b6 = BoxesRunTime.unboxToInt((Object)pair38._1());
        Product product20 = (Product)pair38._2();
        Pair pair39 = TupleCons$.MODULE$.unapply(product20);
        if (pair39.isEmpty()) throw new MatchError((Object)product18);
        Pair pair40 = pair39.get();
        String c6 = (String)pair40._1();
        Product product21 = (Product)pair40._2();
        Pair pair41 = TupleCons$.MODULE$.unapply(product21);
        if (pair41.isEmpty()) throw new MatchError((Object)product18);
        Pair pair42 = pair41.get();
        int d6 = BoxesRunTime.unboxToInt((Object)pair42._1());
        Product product22 = (Product)pair42._2();
        Pair pair43 = TupleCons$.MODULE$.unapply(product22);
        if (pair43.isEmpty()) throw new MatchError((Object)product18);
        Pair pair44 = pair43.get();
        String e6 = (String)pair44._1();
        Product product23 = (Product)pair44._2();
        Pair pair45 = TupleCons$.MODULE$.unapply(product23);
        if (pair45.isEmpty()) throw new MatchError((Object)product18);
        Pair pair46 = pair45.get();
        int f6 = BoxesRunTime.unboxToInt((Object)pair46._1());
        BoxedUnit boxedUnit21 = (BoxedUnit)pair46._2();
        BoxedUnit boxedUnit22 = BoxedUnit.UNIT;
        BoxedUnit boxedUnit23 = boxedUnit21;
        if (boxedUnit22 == null ? boxedUnit23 != null : !boxedUnit22.equals((Object)boxedUnit23)) throw new MatchError((Object)product18);
        BoxedUnit boxedUnit24 = boxedUnit21;
        String string19 = a6;
        String string20 = "a";
        Predef..MODULE$.assert(!(string19 == null ? string20 != null : !string19.equals(string20)));
        Predef..MODULE$.assert(b6 == 2);
        String string21 = c6;
        String string22 = "c";
        Predef..MODULE$.assert(!(string21 == null ? string22 != null : !string21.equals(string22)));
        Predef..MODULE$.assert(d6 == 4);
        String string23 = e6;
        String string24 = "e";
        Predef..MODULE$.assert(!(string23 == null ? string24 != null : !string23.equals(string24)));
        Predef..MODULE$.assert(f6 == 6);
    }
}
