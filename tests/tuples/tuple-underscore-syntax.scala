import dotty._

object Test {
  def main(args: Array[String]) = {
    // seq 22 | while read i; do echo -n "val t$i = ("; seq $i | xargs -i -n1 echo "_," | xargs; done
    // seq 22 | while read i; do seq $i | xargs -i -n1 echo "assert(t$i._{} == {})"; done

    // val t1 = Tuple1(1)
    // val t2 = (1, 2)
    // val t3 = (1, 2, 3)
    val t4 = (1, 2, 3, 4)
    val t5 = (1, 2, 3, 4, 5)
    // val t6 = (1, 2, 3, 4, 5, 6)
    // val t7 = (1, 2, 3, 4, 5, 6, 7)
    // val t8 = (1, 2, 3, 4, 5, 6, 7, 8)
    // val t9 = (1, 2, 3, 4, 5, 6, 7, 8, 9)
    // val t10 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // val t11 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    // val t12 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    // val t13 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
    // val t14 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
    // val t15 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    // val t16 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    // val t17 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
    // val t18 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
    // val t19 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
    // val t20 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    // val t21 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21)
    // val t22 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22)
    // assert(t1._1 == 1)
    // assert(t2._1 == 1)
    // assert(t2._2 == 2)
    // assert(t3._1 == 1)
    // assert(t3._2 == 2)
    // assert(t3._3 == 3)
    assert(t4._1 == 1)
    // assert(t4._2 == 2)
    // assert(t4._3 == 3)
    // assert(t4._4 == 4)
    assert(t5._1 == 1)
    // assert(t5._2 == 2)
    // assert(t5._3 == 3)
    // assert(t5._4 == 4)
    // assert(t5._5 == 5)
    // assert(t6._1 == 1)
    // assert(t6._2 == 2)
    // assert(t6._3 == 3)
    // assert(t6._4 == 4)
    // assert(t6._5 == 5)
    // assert(t6._6 == 6)
    // assert(t7._1 == 1)
    // assert(t7._2 == 2)
    // assert(t7._3 == 3)
    // assert(t7._4 == 4)
    // assert(t7._5 == 5)
    // assert(t7._6 == 6)
    // assert(t7._7 == 7)
    // assert(t8._1 == 1)
    // assert(t8._2 == 2)
    // assert(t8._3 == 3)
    // assert(t8._4 == 4)
    // assert(t8._5 == 5)
    // assert(t8._6 == 6)
    // assert(t8._7 == 7)
    // assert(t8._8 == 8)
    // assert(t9._1 == 1)
    // assert(t9._2 == 2)
    // assert(t9._3 == 3)
    // assert(t9._4 == 4)
    // assert(t9._5 == 5)
    // assert(t9._6 == 6)
    // assert(t9._7 == 7)
    // assert(t9._8 == 8)
    // assert(t9._9 == 9)
    // assert(t10._1 == 1)
    // assert(t10._2 == 2)
    // assert(t10._3 == 3)
    // assert(t10._4 == 4)
    // assert(t10._5 == 5)
    // assert(t10._6 == 6)
    // assert(t10._7 == 7)
    // assert(t10._8 == 8)
    // assert(t10._9 == 9)
    // assert(t10._10 == 10)
    // assert(t11._1 == 1)
    // assert(t11._2 == 2)
    // assert(t11._3 == 3)
    // assert(t11._4 == 4)
    // assert(t11._5 == 5)
    // assert(t11._6 == 6)
    // assert(t11._7 == 7)
    // assert(t11._8 == 8)
    // assert(t11._9 == 9)
    // assert(t11._10 == 10)
    // assert(t11._11 == 11)
    // assert(t12._1 == 1)
    // assert(t12._2 == 2)
    // assert(t12._3 == 3)
    // assert(t12._4 == 4)
    // assert(t12._5 == 5)
    // assert(t12._6 == 6)
    // assert(t12._7 == 7)
    // assert(t12._8 == 8)
    // assert(t12._9 == 9)
    // assert(t12._10 == 10)
    // assert(t12._11 == 11)
    // assert(t12._12 == 12)
    // assert(t13._1 == 1)
    // assert(t13._2 == 2)
    // assert(t13._3 == 3)
    // assert(t13._4 == 4)
    // assert(t13._5 == 5)
    // assert(t13._6 == 6)
    // assert(t13._7 == 7)
    // assert(t13._8 == 8)
    // assert(t13._9 == 9)
    // assert(t13._10 == 10)
    // assert(t13._11 == 11)
    // assert(t13._12 == 12)
    // assert(t13._13 == 13)
    // assert(t14._1 == 1)
    // assert(t14._2 == 2)
    // assert(t14._3 == 3)
    // assert(t14._4 == 4)
    // assert(t14._5 == 5)
    // assert(t14._6 == 6)
    // assert(t14._7 == 7)
    // assert(t14._8 == 8)
    // assert(t14._9 == 9)
    // assert(t14._10 == 10)
    // assert(t14._11 == 11)
    // assert(t14._12 == 12)
    // assert(t14._13 == 13)
    // assert(t14._14 == 14)
    // assert(t15._1 == 1)
    // assert(t15._2 == 2)
    // assert(t15._3 == 3)
    // assert(t15._4 == 4)
    // assert(t15._5 == 5)
    // assert(t15._6 == 6)
    // assert(t15._7 == 7)
    // assert(t15._8 == 8)
    // assert(t15._9 == 9)
    // assert(t15._10 == 10)
    // assert(t15._11 == 11)
    // assert(t15._12 == 12)
    // assert(t15._13 == 13)
    // assert(t15._14 == 14)
    // assert(t15._15 == 15)
    // assert(t16._1 == 1)
    // assert(t16._2 == 2)
    // assert(t16._3 == 3)
    // assert(t16._4 == 4)
    // assert(t16._5 == 5)
    // assert(t16._6 == 6)
    // assert(t16._7 == 7)
    // assert(t16._8 == 8)
    // assert(t16._9 == 9)
    // assert(t16._10 == 10)
    // assert(t16._11 == 11)
    // assert(t16._12 == 12)
    // assert(t16._13 == 13)
    // assert(t16._14 == 14)
    // assert(t16._15 == 15)
    // assert(t16._16 == 16)
    // assert(t17._1 == 1)
    // assert(t17._2 == 2)
    // assert(t17._3 == 3)
    // assert(t17._4 == 4)
    // assert(t17._5 == 5)
    // assert(t17._6 == 6)
    // assert(t17._7 == 7)
    // assert(t17._8 == 8)
    // assert(t17._9 == 9)
    // assert(t17._10 == 10)
    // assert(t17._11 == 11)
    // assert(t17._12 == 12)
    // assert(t17._13 == 13)
    // assert(t17._14 == 14)
    // assert(t17._15 == 15)
    // assert(t17._16 == 16)
    // assert(t17._17 == 17)
    // assert(t18._1 == 1)
    // assert(t18._2 == 2)
    // assert(t18._3 == 3)
    // assert(t18._4 == 4)
    // assert(t18._5 == 5)
    // assert(t18._6 == 6)
    // assert(t18._7 == 7)
    // assert(t18._8 == 8)
    // assert(t18._9 == 9)
    // assert(t18._10 == 10)
    // assert(t18._11 == 11)
    // assert(t18._12 == 12)
    // assert(t18._13 == 13)
    // assert(t18._14 == 14)
    // assert(t18._15 == 15)
    // assert(t18._16 == 16)
    // assert(t18._17 == 17)
    // assert(t18._18 == 18)
    // assert(t19._1 == 1)
    // assert(t19._2 == 2)
    // assert(t19._3 == 3)
    // assert(t19._4 == 4)
    // assert(t19._5 == 5)
    // assert(t19._6 == 6)
    // assert(t19._7 == 7)
    // assert(t19._8 == 8)
    // assert(t19._9 == 9)
    // assert(t19._10 == 10)
    // assert(t19._11 == 11)
    // assert(t19._12 == 12)
    // assert(t19._13 == 13)
    // assert(t19._14 == 14)
    // assert(t19._15 == 15)
    // assert(t19._16 == 16)
    // assert(t19._17 == 17)
    // assert(t19._18 == 18)
    // assert(t19._19 == 19)
    // assert(t20._1 == 1)
    // assert(t20._2 == 2)
    // assert(t20._3 == 3)
    // assert(t20._4 == 4)
    // assert(t20._5 == 5)
    // assert(t20._6 == 6)
    // assert(t20._7 == 7)
    // assert(t20._8 == 8)
    // assert(t20._9 == 9)
    // assert(t20._10 == 10)
    // assert(t20._11 == 11)
    // assert(t20._12 == 12)
    // assert(t20._13 == 13)
    // assert(t20._14 == 14)
    // assert(t20._15 == 15)
    // assert(t20._16 == 16)
    // assert(t20._17 == 17)
    // assert(t20._18 == 18)
    // assert(t20._19 == 19)
    // assert(t20._20 == 20)
    // assert(t21._1 == 1)
    // assert(t21._2 == 2)
    // assert(t21._3 == 3)
    // assert(t21._4 == 4)
    // assert(t21._5 == 5)
    // assert(t21._6 == 6)
    // assert(t21._7 == 7)
    // assert(t21._8 == 8)
    // assert(t21._9 == 9)
    // assert(t21._10 == 10)
    // assert(t21._11 == 11)
    // assert(t21._12 == 12)
    // assert(t21._13 == 13)
    // assert(t21._14 == 14)
    // assert(t21._15 == 15)
    // assert(t21._16 == 16)
    // assert(t21._17 == 17)
    // assert(t21._18 == 18)
    // assert(t21._19 == 19)
    // assert(t21._20 == 20)
    // assert(t21._21 == 21)
    // assert(t22._1 == 1)
    // assert(t22._2 == 2)
    // assert(t22._3 == 3)
    // assert(t22._4 == 4)
    // assert(t22._5 == 5)
    // assert(t22._6 == 6)
    // assert(t22._7 == 7)
    // assert(t22._8 == 8)
    // assert(t22._9 == 9)
    // assert(t22._10 == 10)
    // assert(t22._11 == 11)
    // assert(t22._12 == 12)
    // assert(t22._13 == 13)
    // assert(t22._14 == 14)
    // assert(t22._15 == 15)
    // assert(t22._16 == 16)
    // assert(t22._17 == 17)
    // assert(t22._18 == 18)
    // assert(t22._19 == 19)
    // assert(t22._20 == 20)
    // assert(t22._21 == 21)
    // assert(t22._22 == 22)
  }
}
