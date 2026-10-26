# The Debugger — watch your code run, one line at a time

**CSCI-121 · Week 10 · Recitation · Mon Oct 26 (R3) / Wed Oct 28 (R4)**

So far, when your code gives the wrong answer, you have two choices: stare at it, or add
`System.out.println` lines and guess. Today you learn a third choice that is much faster.

A **debugger** is a tool that runs your program **one line at a time**. It stops where you
tell it to. While it is stopped, it shows you the value of **every variable** right now.
You stop guessing. You just look.

## The rule for today

> **No `println` lines.** You find every bug by watching the variables in the debugger.

---

## Setup (5 minutes)

> ## ⚠️ Fork it. Do not clone this repository.
>
> A **fork** is your own copy on GitHub. You can push to it, and I can see it.
> **No fork, no credit.**

1. Click **Fork** at the top right of this page. The name at the top should now read
   `your-username / DebuggerLab`.
2. In IntelliJ: **File → New → Project from Version Control**. Paste the URL of **your**
   fork (it has your username in it). Click **Clone**, then **Trust Project**.
3. Open `src/main/java/debugger/GameStats.java`. Click the green ▶ next to `main` and
   choose **Run**. You should see:

   ```
   === Season stats ===
   Total yards:   140
   Average yards: 23.0
   Best game:     game 6
   Big games:     2
   ```

   **Every one of those lines is wrong.** That's on purpose. You will fix them today.

If there is no green ▶ next to `main`, right-click `pom.xml` → **Maven → Reload project**.

---

## The 5 buttons you need

| What it does | Button | Windows | Mac |
|---|---|---|---|
| **Debug**: run, but stop at breakpoints | green ▶ → **Debug** (the 🐞 bug icon) | Shift+F9 | Ctrl+D |
| **Step Over**: run this line, go to the next one | ↷ arrow over a line | F8 | F8 |
| **Step Into**: go *inside* the method on this line | ↓ arrow pointing down | F7 | F7 |
| **Step Out**: finish this method, go back to where it was called | ↑ arrow pointing up | Shift+F8 | Shift+F8 |
| **Resume**: run until the next breakpoint | green ▶ on the left side of the Debug window | F9 | Cmd+Option+R |

On a Mac laptop you may need to hold **fn** to use F7 and F8. The buttons on the Debug
window always work.

A **breakpoint** is a red dot you put next to a line. When the program reaches that line, it
**stops before running it**. To add one, click in the gray space just to the right of the
line number. Click the dot again to remove it.

---

## Part 1 — Watch a loop (10 minutes)

Open `Countdown.java`. There is **no bug** in this file. You are just learning the buttons.

1. **Before you run it**, read the code. On paper, write down what you think it will print.
2. Put a breakpoint on the line `int yardsToGo = 10;`.
3. Click the green ▶ next to `main` and choose **Debug**. The program stops on your line.
   It turns blue. A Debug window opens at the bottom.
4. Look at the **Variables** pane in the Debug window. Right now it only shows `args`.
   `yardsToGo` is not there yet. Why? *(The blue line has not run yet.)*
5. Press **Step Over** one time. Now `yardsToGo = 10` shows up.
6. Keep pressing **Step Over**. Every time you go around the loop, fill in this table:

   | Pass | `play` | `gain` | `yardsToGo` after the pass |
   |---|---|---|---|
   | 1 | | | |
   | 2 | | | |
   | 3 | | | |

7. When the loop ends, what is `yardsToGo`? Is that what you guessed in step 1?
8. Press **Resume** to let the program finish.

> **Tip:** IntelliJ also shows each variable's value in gray **right next to the code**.
> You can read values there or in the Variables pane.

---

## Part 2 — Step Into a method (10 minutes)

Open `GameStats.java`. It holds one receiver's yards for 6 games:

```java
int[] yards = {12, 45, 8, 0, 67, 20};
```

Add these up yourself. The right total is **152**. The program says **140**.

1. Put a breakpoint on the `System.out.println("Total yards: " ...` line in `main`.
2. **Debug** it. It stops on that line.
3. Press **Step Into**. You jump *inside* `totalYards`. This is the big new idea:
   Step Over would run the whole method in one step. Step Into lets you go inside it.
4. Press **Step Over** slowly. Watch `i` and `total` in the Variables pane.
   Look at which games get added to `total`. Which game never gets added?
5. You found **Bug 1**. Stop the program (red square), fix the line, and run again.
   The total should now be **152**.
6. Write it down in `NOTES.md` (see below). Then commit:

   ```
   Bug 1 fixed: totalYards
   ```

> **Step Out:** if you Step Into a method you don't care about (like `println`), press
> **Step Out** to get back to your own code.

---

## Part 3 — Find the other 3 bugs (20 minutes)

There is **one bug in each method**. You fixed `totalYards`. Three are left:

| Method | Right answer | What it prints now (after Bug 1 is fixed) |
|---|---|---|
| `averageYards` | `25.333333333333332` | `25.0` |
| `bestGame` | `game 5` (67 yards) | `game 6` |
| `countBigGames(yards, 20)` | `3` (45, 67 and 20 yards) | `2` |

For each one:

1. Put a breakpoint **inside** the method (on its first line).
2. **Debug**. Use **Resume** to jump from breakpoint to breakpoint.
3. Step through with **Step Over**. Before each step, **say out loud what you expect the
   next value to be.** When the debugger shows something different, you found the bug.
4. Fix it. Run it. Fill in `NOTES.md`. Commit with the message `Bug N fixed: methodName`.

**Hints, if you are stuck** (try for 5 minutes first):

<details><summary>Hint for averageYards</summary>

Step Over the `double average = ...` line. `total` is 152 and `yards.length` is 6.
What is `average` right after that line? Is there a decimal part? What do you get when you
divide one `int` by another `int` in Java?
</details>

<details><summary>Hint for bestGame</summary>

`best` is supposed to hold an **index** (a game number), not a number of yards. Watch
what `yards[i]` gets compared to on each pass. Is it comparing yards to yards?
</details>

<details><summary>Hint for countBigGames</summary>

Put the breakpoint on the `if` line. Press **Resume** until `i` is 5. What is `yards[i]`?
What is `min`? Does `count` go up? Should it? Read the comment above the method again.
</details>

**Bonus: a conditional breakpoint.** Right-click a red dot and type a condition, like
`i == 4`. Now the program only stops when that condition is true. This is great for a loop
that runs 1,000 times.

---

## NOTES.md — hand this in

Fill in `NOTES.md` as you go. For each bug, write:

- **Where:** the method and the line.
- **What the debugger showed:** the variable, and the value you saw.
- **The fix:** what you changed.

Example: *Bug 1 — `totalYards`, the `for` line. On the first pass, `i` was already 1, so
`yards[0]` (12 yards) was never added. I changed `int i = 1` to `int i = 0`.*

## Hand it in

You should have **4 commits** (one per bug), and `NOTES.md` filled in. Push them:

```bash
git log --oneline     # you should see your 4 "Bug N fixed" commits
git push
```

Then open your fork on GitHub and refresh. Your newest commit should be at the top.

When you're done, run `GameStats` one last time. It should print:

```
=== Season stats ===
Total yards:   152
Average yards: 25.333333333333332
Best game:     game 5
Big games:     3
```

**Why this matters for SB6:** if your `prefixAverage` gives the wrong numbers, don't guess.
Set a breakpoint inside it and watch your sum and your `i` change.
