package no.rodland.advent

enum class Dir(val move: (input: Pos, howMuch: Int) -> Pos) {
   N({ pos, howMuch -> pos.above(howMuch) }),
   S({ pos, howMuch -> pos.below(howMuch) }),
   W({ pos, howMuch -> pos.left(howMuch) }),
   E({ pos, howMuch -> pos.right(howMuch) }),
   NW({ pos, howMuch -> pos.nw(howMuch) }),
   NE({ pos, howMuch -> pos.ne(howMuch) }),
   SW({ pos, howMuch -> pos.sw(howMuch) }),
   SE({ pos, howMuch -> pos.se(howMuch) }),
}