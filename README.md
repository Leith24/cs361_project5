UTEID: gdl386; dao384;
FIRSTNAME: Gavin; Daniel;
LASTNAME: Leith; Olvera;
CSACCOUNT: gavindl; dolvera1;
EMAIL: gavinleith@utexas.edu; daolvera1@gmail.com;

[Program 5]
[Description]
Our program has 2 java files, all of which can be compiled with javac *.java. To run the program, you will use java PasswordCrack [dictionary] [inputfile]. Our code runs through an initial trial of the passwords for the users. This initial trial tries all the given tests such as removing the last word, removing the first word, reversing, capitalizing, and so forth. As we try these initial combinations, our program prints out the user information along with the status of a successful crack or a failed crack. As we crack passwords, we add them to our total password crack count. After we have gone through the initial trials, we send the failed passwords into our mangle. From here, we recheck the passwords using various combinations of the trials. If these are successful in the mangled combinations, we print out the successful crack. We run through the combinations to check for multiple mangled passwords.

[Finish]
We finished the assignment. We did not pass all the passwords however.

[Test Cases]
[Input of test 1]
https://www.cs.utexas.edu/~byoung/cs361/passwd1

[Output of test 1]
attempting to crack: T8jwuve9rQBo.
FAILED

attempting to crack: !cI6tOT/9D2r6
found password: Salizar

attempting to crack: 5WW698tSZJE9I
FAILED

attempting to crack: KelgNcBOZdHmA
FAILED

attempting to crack: gwjT8yTnSCVQo
found password: enoggone

attempting to crack: nxsr/UAKmKnvo
FAILED

attempting to crack: 8joIBJaXJvTd2
FAILED

attempting to crack: w@EbBlXGLTue6
found password: THIRTY

attempting to crack: jsQGVbJ.IiEvE
found password: sATCHEL

attempting to crack: 3dIJJXzELzcRE
found password: Impact

attempting to crack: 7we09tBSVT76o
found password: keyskeys

attempting to crack: e4DBHapAtnjGk
found password: doorrood

attempting to crack: khnVjlJN3Lyh2
found password: rdoctor

attempting to crack: %xPBzF/TclHvg
found password: abort6

attempting to crack: {ztmy9azKzZgU
found password: icious

attempting to crack: feohQuHOnMKGE
found password: squadro

attempting to crack: <qt0.GlIrXuKs
found password: eeffoc

attempting to crack: (bUx9LiAcW8As
found password: amazing

attempting to crack: &i4KZ5wmac566
FAILED

attempting to crack: atbWfKL4etk4U
found password: michael

attemping to crack: &i4KZ5wmac566
found password: liagiba

attemping to crack: 8joIBJaXJvTd2
found password: teserP

We can crack 16 cases. 
List of cracked:
Salizar
enoggone
THIRTY
sATCHEL
Impact
keyskeys
doorrood
rdoctor
abort6
icious
squadro
eeffoc
amazing
michael
liagiba
teserP

We can not crack 4 cases.
List of uncracked:
KelgNcBOZdHmA
5WW698tSZJE9I
T8jwuve9rQBo.
nxsr/UAKmKnvo

We can crack 16 cases in 4.912 seconds


[Input of test 2]
https://www.cs.utexas.edu/~byoung/cs361/passwd2
[Output of test 2]
attempting to crack: T8U5jQaDVv/o2
FAILED

attempting to crack: !cSaQELm/EBV.
FAILED

attempting to crack: 5Wb2Uqxhoyqfg
FAILED

attempting to crack: KenK1CTDGr/7k
FAILED

attempting to crack: gw9oXmw1L08RM
FAILED

attempting to crack: nxr9OOqvZjbGs
FAILED

attempting to crack: 8jGWbU0xbKz06
found password: zoossooz

attempting to crack: w@FxBZv.d0y/U
FAILED

attempting to crack: js5ctN1leUABo
found password: ellows

attempting to crack: 3d1OgBYfvEtfg
found password: ^bribed

attempting to crack: 7wKTWsgNJj6ac
found password: INDIGNITY

attempting to crack: e4EyEMhNzYLJU
found password: ElmerJ

attempting to crack: kh/1uC5W6nDKc
found password: dIAMETER

attempting to crack: %xqFrM5RVA6t6
found password: soozzoos

attempting to crack: {zQOjvJcHMs7w
found password: enchant$

attempting to crack: fe8PnYhq6WoOQ
FAILED

attempting to crack: <qf.L9z1/tZkA
found password: eltneg

attempting to crack: (bt0xiAwCf7nA
found password: cOnNeLlY

attempting to crack: &ileDTgJtzCRo
found password: Saxon

attempting to crack: atQhiiJLsT6cs
found password: tremors

attemping to crack: fe8PnYhq6WoOQ
found password: Lacque

attemping to crack: gw9oXmw1L08RM
found password: nosral

We can crack 14 cases. 
List of cracked:
zoossooz
ellows
^bribed
INDIGNITY
ElmerJ
dIAMETER
soozzoos
enchant$
eltneg
cOnNeLlY
Saxon
tremors
Lacque
nosral

We can not crack 6 cases.
List of uncracked:
!cSaQELm/EBV.
5Wb2Uqxhoyqfg
KenK1CTDGr/7k
T8U5jQaDVv/o2
nxr9OOqvZjbGs
w@FxBZv.d0y/U

We can crack 14 cases in 5.839 seconds