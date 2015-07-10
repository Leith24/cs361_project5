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

[Input of test 2]
https://www.cs.utexas.edu/~byoung/cs361/passwd2
[Output of test 2]