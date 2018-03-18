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
    }

    public void initUserType() {
        if (db.userTypeDAO().getAll().size() == 0) {
            UserType admin = new UserType(UserTypeDAO.TYPE_ADMIN, "Admin");
            UserType user = new UserType(UserTypeDAO.TYPE_USER, "User");

            db.userTypeDAO().insert(admin);
            db.userTypeDAO().insert(user);
        }
    }
    public void initAdmin(){
        if(db.userDAO().getUserByUsername("admin") == null){
            User admin = new User("admin", "12345678", "canh.khong@gmail.com","0985899602", "Hanoi",1);
            db.userDAO().insert(admin);
        }
    }
    public void initCategory() {
        if (db.categoryDAO().getAll().size() == 0) {
            Category cat1 = new Category("Fruit", false);
            Category cat2 = new Category("Car", false);
            Category cat3 = new Category("Girl", false);
            Category cat4 = new Category("Computer", false);
            Category cat5 = new Category("Shoe", false);

            db.categoryDAO().insert(cat1);
            db.categoryDAO().insert(cat2);
            db.categoryDAO().insert(cat3);
            db.categoryDAO().insert(cat4);
            db.categoryDAO().insert(cat5);
        }
    }

    public void initProduct() {
        if (db.productDAO().getAll().size() == 0) {
            uriUtil = new URIUtil();
            Product product1 = new Product("banana", uriUtil.getURLForResource(R.drawable.banana), 1, 1000,
                    "Cheapest banana in the world!",false, 4.5);
            Product product2 = new Product("apple", uriUtil.getURLForResource(R.drawable.apple), 1, 1200,
                    "Cheapest apple in the world!",false, 4.6);
            Product product3 = new Product("lamborghini", uriUtil.getURLForResource(R.drawable.lamborgini), 2, 1000000,
                    "Fastest car in the world!",false, 5);
            Product product4 = new Product("Tesla", uriUtil.getURLForResource(R.drawable.tesla), 2, 1200000,
                    "You want an electric car?",false, 4.5);
            Product product5 = new Product("Ngoc Trinh", uriUtil.getURLForResource(R.drawable.ngoctrinh), 3, 100000000,
                    "Want a date with a Viet super model?",false, 4.9);
            Product product6 = new Product("Hera", uriUtil.getURLForResource(R.drawable.hera), 3, 1200000,
                    "She's gonna blow your mind!",false, 4.2);
            Product product7 = new Product("Mac book", uriUtil.getURLForResource(R.drawable.mac), 4, 10000,
                    "Better computer, better life",false, 4.3);
            Product product8 = new Product("Women' running shoe", uriUtil.getURLForResource(R.drawable.shoe1), 5, 1200,
                    "Its a running shoe. And it's for women!",false, 4.5);

            db.productDAO().insert(product1);
            db.productDAO().insert(product2);
            db.productDAO().insert(product3);
            db.productDAO().insert(product4);
            db.productDAO().insert(product5);
            db.productDAO().insert(product6);
            db.productDAO().insert(product7);
            db.productDAO().insert(product8);
        }
    }
}
