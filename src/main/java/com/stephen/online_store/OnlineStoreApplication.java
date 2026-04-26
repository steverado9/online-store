package com.stephen.online_store;

import com.stephen.online_store.entity.Product;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Role;
import com.stephen.online_store.repository.ProductRepository;
import com.stephen.online_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByEmail("isaac.stephen@example.com").isEmpty()) {
			String encodedPassword = passwordEncoder.encode("12345");

			User user1 = new User("isaac.stephen@example.com", encodedPassword, "Isaac", "Stephen", "08096502070", Role.ADMIN);
			userRepository.save(user1);

			Product product1 = new Product(
					"Foldsack No. 1 Backpack, Fits 15 Laptops",
					109.95,
					"Your perfect pack for everyday use and walks in the forest.",
					"men's clothing",
					"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
					3.9);

			Product product2 = new Product(
					"Mens Casual Premium Slim Fit T-Shirts",
					22.3,
					"Slim-fitting style.",
					"men's clothing",
					"https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_t.png",
					4.1);

			Product product3 = new Product(
					"Mens Cotton JacketMens Cotton Jacket",
					55.99,
					"great outerwear jackets for Spring/Autumn/Winter.",
					"men's clothing",
					"https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_t.png",
					4.7);

			Product product4 = new Product(
					"Mens Casual Slim Fit",
					15.99,
					"The color could be slightly different between on the screen and in practice.",
					"men's clothing",
					"https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_t.png",
					2.1);

			Product product5 = new Product(
					"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
					695.0,
					"The Naga was inspired by the mythical water dragon that protects the ocean's pearl.",
					"jewelery",
					"https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_t.png",
					4.6);

			Product product6 = new Product(
					"Solid Gold Petite Micropave",
					168.0,
					"Satisfaction Guaranteed.",
					"jewelery",
					"https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_t.png",
					3.9);

			Product product7 = new Product(
					"White Gold Plated Princess",
					168.0,
					"Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her.",
					"jewelery",
					"https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_t.png",
					3.1);

			Product product8 = new Product(
					"Pierced Owl Rose Gold Plated Stainless Steel Double",
					10.99,
					"Rose Gold Plated Double Flared Tunnel Plug Earrings.",
					"jewelery",
					"https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_t.png",
					1.9);

			Product product9 = new Product(
					"WD 2TB Elements Portable External Hard Drive - USB 3.0 ",
					64.99,
					"USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity.",
					"electronics",
					"https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_t.png",
					3.3);

			Product product10 = new Product(
					"SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
					64.99,
					"Easy upgrade for faster boot up, shutdown, application load and response.",
					"electronics",
					"https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_t.png",
					2.9);

			Product product11 = new Product(
					"Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5",
					109.98,
					"3D NAND flash are applied to deliver high transfer speeds.",
					"electronics",
					"https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_t.png",
					4.8);

			Product product12 = new Product(
					"WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive",
					114.34,
					"Expand your PS4 gaming experience, Play anywhere Fast and easy.",
					"electronics",
					"https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_t.png",
					4.8);

			Product product13 = new Product(
					"Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin",
					599.14,
					"21. 5 inches Full HD (1920 x 1080) widescreen IPS display.",
					"electronics",
					"https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_t.png",
					2.9);

			Product product14 = new Product(
					"Samsung 49-Inch",
					999.99,
					"49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR.",
					"electronics",
					"https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_t.png",
					2.2);

			Product product15 = new Product(
					"BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats",
					56.99,
					"100% Polyester; Detachable Liner Fabric: Warm Fleece.",
					"women's clothing",
					"https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_t.png",
					2.6);

			Product product16 = new Product(
					"Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
					29.95,
					"100% POLYURETHANE(shell) 100% POLYESTER(lining) 75% POLYESTER 25% COTTON (SWEATER).",
					"women's clothing",
					"https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_t.png",
					2.9);

			Product product17 = new Product(
					"Rain Jacket Women Windbreaker Striped Climbing Raincoats",
					39.99,
					"Lightweight perfect for trip or casual wear.",
					"women's clothing",
					"https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2t.png",
					3.8);

			Product product18 = new Product(
					"MBJ Women's Solid Short Sleeve Boat Neck V",
					9.85,
					"95% RAYON 5% SPANDEX, Made in USA.",
					"women's clothing",
					"https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_t.png",
					4.7);

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
			productRepository.save(product4);
			productRepository.save(product5);
			productRepository.save(product6);
			productRepository.save(product7);
			productRepository.save(product8);
			productRepository.save(product9);
			productRepository.save(product10);
			productRepository.save(product11);
			productRepository.save(product12);
			productRepository.save(product13);
			productRepository.save(product14);
			productRepository.save(product15);
			productRepository.save(product16);
			productRepository.save(product17);
			productRepository.save(product18);

			System.out.println("Default admin user and product created successfully.");
		} else {
			System.out.println("Admin user already exists.");
		}
	}
}
