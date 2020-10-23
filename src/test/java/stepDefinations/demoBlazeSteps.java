package stepDefinations;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import pageObjects.DemoBlazePageObj;

public class demoBlazeSteps {

	DemoBlazePageObj demoBlaze = new DemoBlazePageObj();
	
	@Given("user is on the open the demoblaze website")
	public void user_is_on_the_open_the_website() {
	   demoBlaze.navigates_to_demoBlazeWebsite();
	}

	@When("user selects a laptop from the {string} to adds it to the cart")
	public void user_selects_a_laptop_from_the_to_adds_it_to_the_cart(String category, DataTable dataTable) throws InterruptedException {
		List<String> laptopforCart = dataTable.asList();
	   demoBlaze.addLaptopToCart(laptopforCart, category);
	}

	@When("the user navigates to the cart to delete {string} from the cart")
	public void the_user_navigates_to_the_cart_to_delete_from_the_cart(String string) throws InterruptedException {
	  demoBlaze.deleteAProductfromCart(string);
	}

	@When("user place order by filling in web form details")
	public void user_place_order_by_filling_in_web_form_details(DataTable dataTable) {
		List<String> personalDetails = dataTable.asList();
				
	   demoBlaze.placingOrder(personalDetails);
	}



	@Then("the purchase amount should similar to {string} mention on the website")
	public void the_purchase_amount_should_similar_to_mention_on_the_website(String amount) {
	  demoBlaze.verifyThePurchase(amount);
	}

	@After
	public void close_the_browser() {
		demoBlaze.closeBrowser();
	}


}
