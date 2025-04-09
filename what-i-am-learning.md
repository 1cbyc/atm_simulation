I am learning to write Java professionally and I want to understand what I'm doing while at it.

**Key Concepts I'm Practicing:**

- OOP principles (Encapsulation, Inheritance if needed)
- Java I/O (reading/writing to files)
- User input (Scanner)
- Basic control flow (if/else, loops)

Seeing what I am learning, I feel like Java makes repetition severally. 

So, now I created dummy data for data/users.txt, and this places that my format is (account, pin, balance):

123456,1234,10000.0
789012,4321,5000.0


i kept getting an error, so i added this right after the loadUsers() side right inside the ATM class:

```java

private void saveUsers() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
        for (User user : users.values()) {
            writer.write(user.getAccountNumber() + "," + user.getPin() + "," + user.getBalance());
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error saving users: " + e.getMessage());
    }
}
```

so what this code does is to loop through all User objects in the users map. then, write each user to users.txt in the same format:
accountNumber,pin,balance then overwrite the old file with the new updated data.