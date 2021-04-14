package org.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdactinTestNG extends BaseClass {
     
	BaseClass b = new BaseClass();
	

	@BeforeClass
	private void beforeClass() {
		b.getDriver();
		b.launchurl("https://adactinhotelapp.com/");
        b.maximize();
        
        System.out.println("changes are made");
	}

	@AfterMethod
	private void screenshot(Method m) throws IOException {
		screenshot(m.getName());
	}

	@Parameters({ "username", "password" })
	@Test(priority=1)
	private void tc1(String s, String s1) {
		WebElement id = b.id("username");
		id.sendKeys(s);
		WebElement pass = b.id("password");
		pass.sendKeys(s1);
		WebElement btnlogin = b.id("login");
		btnlogin.click();
	}

	@Parameters({ "loc", "hotel", "room", "roomNo", "adult", "child" })
	@Test(priority = 2)
	private void searchHotel(String s, String s1, String s2, String s3, String s4, String s5) {
		b.selectByText(b.id("location"), s);
		b.selectByText(b.id("hotels"), s1);
		b.selectByText(b.id("room_type"), s2);
		b.selectByText(b.id("room_nos"), s3);
		b.selectByText(b.id("adult_room"), s4);
		b.selectByText(b.id("child_room"), s5);
		b.click(b.id("Submit"));

	}

	@Test(priority = 3)
	private void selectHotel() {
		b.click(b.id("radiobutton_0"));
		b.click(b.id("continue"));

	}

	@Parameters({ "firstname", "lastname", "address", "cardNo", "cardtype", "month", "year", "cvv" })
	@Test(priority = 4)
	private void bookHotel(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
		b.sendkeys(b.id("first_name"), s);
		b.sendkeys(b.id("last_name"), s1);
		b.sendkeys(b.id("address"), s2);
		b.sendkeys(b.id("cc_num"), s3);
		b.selectByText(b.id("cc_type"), s4);
		b.selectByText(b.id("cc_exp_month"), s5);
		b.selectByText(b.id("cc_exp_year"), s6);
		b.sendkeys(b.id("cc_cvv"), s7);
		b.click(b.id("book_now"));

	}

}
