package com.ty.shopping.controller;

import com.ty.shopping.dao.UserDao;
import com.ty.shopping.dto.User;

public class UserController {
	public static void main(String[] args) {
		
		User user = new User();
//		user.setId(3);
//		user.setName("Nuthan");
//		user.setEmail("nuthan@gamil.com");
//		user.setPassword("nuthan");
//		user.setMobile(8266811332l);
		
		UserDao dao= new UserDao();
//		int res = dao.saveUser(user);
//		if(res>0) {
//			System.out.println("Data is inserted successfully");
//		}
//		else
//			System.out.println("Data is not inserted");
	
		 User res1 = dao.validateUser("nuthan@gmail.com", "nuthan");
	
		 if(res1 != null) {
			 System.out.println("Name of the user: "+res1.getName());
			 System.out.println("Phone umber of the user: "+res1.getMobile());
		 }
		 else
			 System.out.println("Email or password is wrong");
		
		
	}
}
