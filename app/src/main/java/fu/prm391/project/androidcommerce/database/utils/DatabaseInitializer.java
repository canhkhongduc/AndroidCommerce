package fu.prm391.project.androidcommerce.database.utils;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.dao.UserTypeDAO;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.database.entity.User;
import fu.prm391.project.androidcommerce.database.entity.UserType;
import fu.prm391.project.androidcommerce.utils.URIUtil;

/**
 * Created by Lam on 3/11/2018.
 */

public class DatabaseInitializer {
    private AppDatabase db;
    private URIUtil uriUtil;

    public DatabaseInitializer(AppDatabase db) {
        this.db = db;
        uriUtil = new URIUtil();
    }

    public void initUserType() {
        if (db.userTypeDAO().getAll().size() == 0) {
            UserType admin = new UserType(UserTypeDAO.TYPE_ADMIN, "Admin");
            UserType user = new UserType(UserTypeDAO.TYPE_USER, "User");

            db.userTypeDAO().insert(admin);
            db.userTypeDAO().insert(user);
        }
    }

    public void initUser() {
        if (db.userDAO().getAll().size() == 0) {
            User admin = new User("admin", "12345678", "canh.khong@gmail.com", "0985899602", "Hanoi", 1);
            User user1 = new User("lam", "1", "abc", "123", "abc", 2);

            db.userDAO().insert(admin);
            db.userDAO().insert(user1);
        }
    }

    public void initCategory() {
        if (db.categoryDAO().getAll().size() == 0) {
            Category cat1 = new Category("Computer", false);
            Category cat2 = new Category("Phone", false);
            Category cat3 = new Category("Food", false);
            Category cat4 = new Category("Shoe", false);
            Category cat5 = new Category("Car", false);

            db.categoryDAO().insert(cat1);
            db.categoryDAO().insert(cat2);
            db.categoryDAO().insert(cat3);
            db.categoryDAO().insert(cat4);
            db.categoryDAO().insert(cat5);
        }
    }

    public void initProduct() {
        if (db.productDAO().getAll().size() == 0) {
            Product product1 = new Product("PC Mouse", uriUtil.getURLForResource(R.drawable.g102), 1, 200000,
                    "Computer mouse", false, 4.5);
            Product product2 = new Product("Pizza", uriUtil.getURLForResource(R.drawable.pizza), 3, 120000,
                    "Cheapest pizza in the world!", false, 4.6);
            Product product3 = new Product("Lamborghini", uriUtil.getURLForResource(R.drawable.lamborghini), 5, 1000000,
                    "Fastest car in the world!", false, 5);
            Product product4 = new Product("Macbook", uriUtil.getURLForResource(R.drawable.macbook), 1, 20000000,
                    "Better computer, better life", false, 4.3);
            Product product5 = new Product("Vans", uriUtil.getURLForResource(R.drawable.shoe), 4, 1300000,
                    "Vans shoes", false, 4.5);

            db.productDAO().insert(product1);
            db.productDAO().insert(product2);
            db.productDAO().insert(product3);
            db.productDAO().insert(product4);
            db.productDAO().insert(product5);
        }
    }
}
