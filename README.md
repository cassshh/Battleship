# Battleship CLI

Command Line Interface game; Battleship

## Setup/Install

**Javac it like its hot**
```bash
$ cd src/
$ javac com/battleship/Game.java
```

**Start game**
```bash
$ java com/battleship/Game
```

*Nuke one another :)*


## Start Game
-----

```bash
Setting up the game...
Available ships:
  [1] ship 2 long
  [2] ship 3 long
  [3] ship 3 long
  [4] ship 4 long
  [5] ship 5 long

[1] What location do you want your ship to be?
> [location] <direction>

{{If no direction provided}}
[1] Which direction?
- Up
- Right
- Down
- Left
> [direction]

//Loop through each ship
```

**Setup 1st player done**
```bash
Ready or edit your ships?
> [ready/edit]

{{ready, move on to 2nd player}}
```

**Main view**
```bash
Opponent's field:
[ ][1][2][3][4][5][6][7][8][9][10]
[A][ ][ ][X][ ][ ][ ][ ][ ][ ][  ]
[B][ ][ ][X][ ][ ][ ][ ][O][ ][  ]
[C][ ][ ][ ][ ][ ][ ][ ][ ][ ][  ]
[D][ ][ ][ ][O][ ][ ][ ][ ][ ][  ]
[E][ ][ ][ ][ ][O][ ][ ][O][ ][  ]
[F][ ][O][ ][ ][ ][ ][ ][X][ ][  ]
[G][ ][ ][ ][ ][ ][ ][ ][X][ ][  ]
[H][ ][ ][ ][ ][ ][ ][ ][X][ ][  ]
[I][ ][ ][X][ ][ ][ ][ ][ ][ ][  ]
[J][ ][ ][ ][ ][ ][ ][ ][ ][ ][  ]

Send us the location to nuke:
> [location]
```

