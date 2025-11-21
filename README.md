📘 Overview

The City Central Library Management System is a Java-based console application designed to digitize basic library operations.
It uses File Handling for data persistence and the Java Collections Framework for efficient storage, searching, and management of records.

This system allows librarians to:

Add new books

Add members

Issue and return books

Save and load data automatically from files

Maintain transaction logs

Manage data using ArrayList and HashMap

This project is ideal for academic assignments, Java labs, and mini-projects.

🧱 Features
✔ Book Management

Add new books

Track availability (issued/not issued)

✔ Member Management

Add new library members

Store member details persistently

✔ Issue & Return System

Issue books to members

Return books

Track issued books using a HashMap (bookId → memberId)

Store all transactions in a log file

✔ File Handling

The system stores data in:

File	Purpose
books.txt	Book records
members.txt	Member records
transactions.txt	Issue/Return logs

All data is auto-saved and loaded every time the application starts.

✔ Collections Used

ArrayList<Book> → Store books

ArrayList<Member> → Store members

HashMap<String, String> → Track issued books

🏗 Project Structure
LibrarySystem/
│
├── LibrarySystem.java
├── books.txt
├── members.txt
└── transactions.txt

💻 How to Run the Program
1. Compile
javac LibrarySystem.java

2. Run
java LibrarySystem


On first run, the system automatically creates the required text files.

📂 Menu Options

When the program starts, you will see:

===== LIBRARY MENU =====
1. Add Book
2. Add Member
3. Issue Book
4. Return Book
5. View All Books
6. Save & Exit


You can enter any option number to perform the task.

📄 Data Formats
Book Format (books.txt)
bookId,title,author,isIssued

Member Format (members.txt)
memberId,name

Transaction Format (transactions.txt)
TYPE,bookId,memberId,dateAndTime 

Author

Parinita singh btech cse KR Mangalam university
