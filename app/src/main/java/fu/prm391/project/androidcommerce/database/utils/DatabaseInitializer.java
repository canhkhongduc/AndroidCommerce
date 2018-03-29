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
            User admin = new User("admin", "12345678", "canh.khong@gmail.com", "0985899602", "Hanoi", uriUtil.getURLForResource(R.drawable.boss), 1);
            User user1 = new User("lam", "1", "abc", "123", "abc",uriUtil.getURLForResource(R.drawable.boss), 2);

            db.userDAO().insert(admin);
            db.userDAO().insert(user1);
        }
    }

    public void initCategory() {
        if (db.categoryDAO().getAll().size() == 0) {
            Category cat1 = new Category("Computer", uriUtil.getURLForResource(R.drawable.elec_icon), false);
            Category cat2 = new Category("Phone", uriUtil.getURLForResource(R.drawable.phone_icon),false);
            Category cat3 = new Category("Food", uriUtil.getURLForResource(R.drawable.food_icon),false);
            Category cat4 = new Category("Shoe", uriUtil.getURLForResource(R.drawable.shoe_icon),false);
            Category cat5 = new Category("Car", uriUtil.getURLForResource(R.drawable.car_icon),false);
            Category cat6 = new Category("Dress", uriUtil.getURLForResource(R.drawable.dress),false);
            Category cat7 = new Category("Lingerie", uriUtil.getURLForResource(R.drawable.panties),false);
            Category cat8 = new Category("Suit", uriUtil.getURLForResource(R.drawable.suit),false);
            db.categoryDAO().insert(cat1);
            db.categoryDAO().insert(cat2);
            db.categoryDAO().insert(cat3);
            db.categoryDAO().insert(cat4);
            db.categoryDAO().insert(cat5);
            db.categoryDAO().insert(cat6);
            db.categoryDAO().insert(cat7);
            db.categoryDAO().insert(cat8);
        }
    }

    public void initProduct() {
        if (db.productDAO().getAll().size() == 0) {
            Product product1 = new Product("PC Mouse", uriUtil.getURLForResource(R.drawable.g102), 1, 200000,
                    "Computer mouse", 100, false, 4.5);
            Product product2 = new Product("Pizza", uriUtil.getURLForResource(R.drawable.pizza), 3, 120000,
                    "Cheapest pizza in the world!", 100, false, 4.6);
            Product product3 = new Product("Lamborghini", uriUtil.getURLForResource(R.drawable.lamborghini), 5, 1000000,
                    "Fastest car in the world!", 100,false, 5);
            Product product4 = new Product("Macbook", uriUtil.getURLForResource(R.drawable.macbook), 1, 20000000,
                    "Better computer, better life", 100,false, 4.3);
            Product product5 = new Product("Vans", uriUtil.getURLForResource(R.drawable.shoe), 4, 1300000,
                    "Vans shoes", 100,false, 4.5);
            Product product6 = new Product("Samsung s8", uriUtil.getURLForResource(R.drawable.sss8), 2, 8000000,
                    "Samsung Galaxy s8", 2, false, 1);
            Product product7 = new Product("Samsung s9", uriUtil.getURLForResource(R.drawable.sss9), 2, 8000000,
                    "Samsung Galaxy s8", 2, false, 1);
            Product product8 = new Product("Sexy Lingerie", uriUtil.getURLForResource(R.drawable.lingerie1), 7, 8000000,
                    "Sexy Lingerie. Want a boyfriend?", 100, false, 5);
            Product product9 = new Product("Elegant dress", uriUtil.getURLForResource(R.drawable.dress1), 6, 8000000,
                    "Best dress for prom", 2, false, 4);
            Product product10 = new Product("Wedding suit", uriUtil.getURLForResource(R.drawable.suit1), 8, 8000000,
                    "Happy wedding", 2, false, 3.5);
            Product product11 = new Product("Sexy lingerie for Ngoc Trinh", uriUtil.getURLForResource(R.drawable.lingerie2), 2, 8000000,
                    "Wanna be sexy as Ngoc Trinh. Wear panties just like her!", 2, false, 4.8);
            Product product12 = new Product("Elegant dress 2", uriUtil.getURLForResource(R.drawable.dress2), 6, 8000000,
                    "Best dress for prom", 2, false, 4.3);
            db.productDAO().insert(product1);
            db.productDAO().insert(product2);
            db.productDAO().insert(product3);
            db.productDAO().insert(product4);
            db.productDAO().insert(product5);
            db.productDAO().insert(product6);
            db.productDAO().insert(product7);
            db.productDAO().insert(product8);
            db.productDAO().insert(product9);
            db.productDAO().insert(product10);
            db.productDAO().insert(product11);
            db.productDAO().insert(product12);
        }
    }
}
