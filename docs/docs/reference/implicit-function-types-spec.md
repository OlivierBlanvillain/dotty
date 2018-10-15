---
layout: doc-page
title: "Implicit Function Types - More Details"
---

Initial implementation in (#1775)[https://github.com/lampepfl/dotty/pull/1775].

## Syntax

Type              ::=  [`implicit'] FunArgTypes `=>' Type
                    |  HkTypeParamClause `=>' Type
                    |  InfixType
Expr              ::=  [`implicit'] FunParams `=>' Expr
BlockResult       ::=  [`implicit'] FunParams `=>' Block
                    |  Expr1

Implicit function types associate to the right, e.g.
`implicit S ⇒ implicit T ⇒ U` is the same as `implicit S ⇒ (implicit T ⇒ U)`.

## Desugarings

Implicit function types are syntactic sugar similar to function types. In
addition the syntax for anonymous implicit function expressions described
above, there is an additional syntactic sugar when expressions have implicit
function types as expected types. Similarly to by-name parameters, closures
are automatically inserted to match the expected type. For example:

    val ift: implicit Int => Int = 1

Is equivalent to

    val ift: implicit Int => Int = implicit (ev: Int) => 1

The same mechanism is used to eta-expand method call with one implicit
parameter block, like in the following example:

    scala> def method(implicit i: Int) = 1
    def method(implicit i: Int): Int

    scala> val value: implicit Int => Int = method
    val value: implicit Int => Int = Lambda$1465/588503940@216372b7


## Implementation

Implicit function types are shorthands for class types that define `apply`
functions with implicit parameters.  Specifically, the `N`-ary function type
`implicit T1, ..., TN ⇒ R` is a shorthand for the class type
`ImplicitFunctionN[T1 , ... , TN, R]`. Such class types are defined in the
Scala library for `N` between 1 and 22 as follows.

    package scala
    trait ImplicitFunctionN[-T1 , ... , -TN, +R] {
      def apply(implicit x1: T1 , ... , xN: TN): R
    }

Implicit function types are eraised to normal functions, that is, types
`scala.ImplicitFunctionN[T1, ..., TN, R]` are turned into `scala.FunctionN[T1,
..., TN, R]` by erasure.

This generalizes to `N > 22` in the same way that functions do, see [the
corresponding
documentation](https://dotty.epfl.ch/docs/reference/dropped/limit22.html).


## Examples

See the section on Expressiveness from [Simplicitly: foundations and
applications of implicit function
types](https://dl.acm.org/citation.cfm?id=3158130). I've extracted it in [this
Gist](https://gist.github.com/OlivierBlanvillain/234d3927fe9e9c6fba074b53a7bd9
592), it might easier to access than the pdf.


### Type Checking

After desugaring  no additional typing rules are required for implicit function types.
