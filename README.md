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