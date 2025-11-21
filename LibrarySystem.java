import java.io.*;
import java.util.*;

class Book {
    String id, title, author;
    boolean isIssued;

    public Book(String id, String title, String author, boolean isIssued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
    }

    public String toString() {
        return id + "," + title + "," + author + "," + isIssued;
    }
}

class Member {
    String id, name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + "," + name;
    }
}

public class LibrarySystem {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();
    HashMap<String, String> issuedBooks = new HashMap<>(); // bookId → memberId

    // ---------------- LOAD DATA FROM FILES ----------------
    public void loadData() {
        try {
            // Load Books
            File bFile = new File("books.txt");
            if (bFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(bFile));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    books.add(new Book(data[0], data[1], data[2], Boolean.parseBoolean(data[3])));
                }
                br.close();
            }

            // Load Members
            File mFile = new File("members.txt");
            if (mFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(mFile));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    members.add(new Member(data[0], data[1]));
                }
                br.close();
            }

        } catch (Exception e) {
            System.out.println("Error loading data.");
        }
    }

    // ---------------- SAVE DATA TO FILES ----------------
    public void saveData() {
        try {
            // Save Books
            BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"));
            for (Book b : books) {
                bw.write(b.toString());
                bw.newLine();
            }
            bw.close();

            // Save Members
            BufferedWriter mw = new BufferedWriter(new FileWriter("members.txt"));
            for (Member m : members) {
                mw.write(m.toString());
                mw.newLine();
            }
            mw.close();

        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    // ---------------- ADD BOOK ----------------
    public void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author, false));
        System.out.println("Book Added Successfully!");
    }

    // ---------------- ADD MEMBER ----------------
    public void addMember() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();

        members.add(new Member(id, name));
        System.out.println("Member Added Successfully!");
    }

    // ---------------- ISSUE BOOK ----------------
    public void issueBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        String bid = sc.nextLine();

        System.out.print("Enter Member ID: ");
        String mid = sc.nextLine();

        for (Book b : books) {
            if (b.id.equals(bid)) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    issuedBooks.put(bid, mid);

                    saveTransaction("ISSUED", bid, mid);
                    System.out.println("Book Issued Successfully!");
                    return;
                } else {
                    System.out.println("Book already issued!");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }

    // ---------------- RETURN BOOK ----------------
    public void returnBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        String bid = sc.nextLine();

        for (Book b : books) {
            if (b.id.equals(bid)) {
                if (b.isIssued) {
                    b.isIssued = false;
                    issuedBooks.remove(bid);

                    saveTransaction("RETURNED", bid, "");
                    System.out.println("Book Returned Successfully!");
                    return;
                } else {
                    System.out.println("Book was not issued!");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }

    // ---------------- SAVE TRANSACTIONS ----------------
    public void saveTransaction(String type, String bid, String mid) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true));
            bw.write(type + "," + bid + "," + mid + "," + new Date());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving transaction.");
        }
    }

    // ---------------- MAIN MENU ----------------
    public void menu() {
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. Save & Exit");
            System.out.print("Choose: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addBook(); break;
                case 2: addMember(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: for (Book b : books) System.out.println(b); break;
                case 6: saveData(); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        new LibrarySystem().menu();
    }
}
