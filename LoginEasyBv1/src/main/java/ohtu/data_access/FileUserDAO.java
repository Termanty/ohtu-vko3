package ohtu.data_access;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

@Component
public class FileUserDAO implements UserDao {
    Scanner reader = null;

    @Override
    public List<User> listAll() {       
        List<User> users = getUsers();
        return users;
    }

    @Override
    public User findByName(String name) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        openFile();
        List<User> users = getUsers();
        users.add(user);  
        save(users);
    }
    
    public List<User> getUsers() {
        openFile();
        List<User> users = new ArrayList<User>();
        while (reader.hasNextLine()) {
            String rivi = reader.nextLine();
            String[] a = rivi.split(" ");
            users.add(new User(a[0],a[1]));
        }
        closeFile();
        return users;
    }
    
    public void save(List<User> users) {
        User u = users.get(users.size() - 1);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("users.txt");
        } catch (Exception e) {
            System.err.println("Tiedostoon kirjottaminen epäonnistui. Virhe: " + e.getMessage());
            return;
        }
        for (User user : users) {
            writer.println(user.getUsername()+" "+user.getPassword());
        }
        writer.close();
    }

    private boolean openFile() {
        File file = new File("users.txt");
        try {
            reader = new Scanner(file);
        } catch (Exception e) {
            System.err.println("Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage());
            return false; // poistutaan metodista
        }
        return true;
    }
    
    private void closeFile() {
        reader.close();
    }

}
