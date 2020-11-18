Feature: Login.feature

  Scenario Outline: Validate Selling and Buying Price
    Given User is on "LoginPage"
    And User clicks on the button "DemoMode"
     And User clicks on the button "LoginTab"
    When User enter the value in the text box "UserEmail" "cm.jnanesh@gmail.com"
    When User enter the value in the text box "Password" "plus500_jna"
    And User clicks on the button "Login"
    Then User is on "HomePage"
    Then User Verifies the page title "Plus500 WebTrader | Leading CFD Platform | Online Trading"
    When User enter the value in the text box "Search" "GBP/USD"
    Then User Press Enter "Search"
    Then User Validate "Sell" Price with the Threshold "<Min>" "<Max>"
    Then User Validate "Buy" Price with the Threshold "<Min>" "<Max>"
    
    Examples:
    |Min|Max|
    |1.2|1.4|
    
    
    
    
