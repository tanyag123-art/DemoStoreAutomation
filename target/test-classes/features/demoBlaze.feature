Feature: Demo Blaze Online


Scenario Outline: Verify if the user is able to place order for a laptop
Given user is on the open the demoblaze website

When user selects a laptop from the "<Category>" to adds it to the cart
	|Sony vaio i5|
	|Dell i7 8gb |
And the user navigates to the cart to delete "<laptopType>" from the cart
And user place order by filling in web form details

|Sam |
|India|
|Mumbai|
|Visa|
|12|
|2024|

Then the purchase amount should similar to "<actualPrice>" mention on the website

Examples:
|demoblaze									|Category  | laptopType | actualPrice  |
|https://www.demoblaze.com/index.html		|Laptop		|Dell i7 8gb|790 USD	|