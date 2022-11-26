package mp05;

public class Main {
    public static void main(String[] args) {
        PasswordInfo p;
        PasswordDAO passwordDAO = new PasswordDAOImpl();

        System.out.println("--- inserting...");
        p = new PasswordInfo("https://www.smu.ac.kr", "smu", "abcde");
        passwordDAO.insert(p);
        p = new PasswordInfo("https://www.smu2.ac.kr", "smu2", "abcde");
        passwordDAO.insert(p);

        System.out.println("--- finding all...");
        for (PasswordInfo pi : passwordDAO.findAll()) {
            System.out.println("reading... " + pi);
        }

        System.out.println("--- updating...");
        p = passwordDAO.findAll().get(1);
        p.setId("smu1");
        passwordDAO.update(p);

        System.out.println("--- see if updated...");
        p = passwordDAO.findByKey(p.getUrl());
        if (p != null) {
            System.out.println(p);
        }

        System.out.println("--- deleting...");
        passwordDAO.delete(p);

        System.out.println("--- finding all after deleting...");
        for (PasswordInfo pi : passwordDAO.findAll()) {
            System.out.println("reading... " + pi);
        }
    }
}