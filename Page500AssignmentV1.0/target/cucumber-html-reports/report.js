$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Assignement.feature");
formatter.feature({
  "line": 1,
  "name": "Login.feature",
  "description": "",
  "id": "login.feature",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "Validate Selling and Buying Price",
  "description": "",
  "id": "login.feature;validate-selling-and-buying-price",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "User is on \"LoginPage\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User clicks on the button \"DemoMode\"",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "User clicks on the button \"LoginTab\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "User enter the value in the text box \"UserEmail\" \"cm.jnanesh@gmail.com\"",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "User enter the value in the text box \"Password\" \"plus500_jna\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "User clicks on the button \"Login\"",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "User is on \"HomePage\"",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "User Verifies the page title \"Plus500 WebTrader | Leading CFD Platform | Online Trading\"",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "User enter the value in the text box \"Search\" \"GBP/USD\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "User Press Enter \"Search\"",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "User Validate \"Sell\" Price with the Threshold \"\u003cMin\u003e\" \"\u003cMax\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "User Validate \"Buy\" Price with the Threshold \"\u003cMin\u003e\" \"\u003cMax\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 17,
  "name": "",
  "description": "",
  "id": "login.feature;validate-selling-and-buying-price;",
  "rows": [
    {
      "cells": [
        "Min",
        "Max"
      ],
      "line": 18,
      "id": "login.feature;validate-selling-and-buying-price;;1"
    },
    {
      "cells": [
        "1.2",
        "1.4"
      ],
      "line": 19,
      "id": "login.feature;validate-selling-and-buying-price;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 720192,
  "status": "passed"
});
formatter.scenario({
  "line": 19,
  "name": "Validate Selling and Buying Price",
  "description": "",
  "id": "login.feature;validate-selling-and-buying-price;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "User is on \"LoginPage\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User clicks on the button \"DemoMode\"",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "User clicks on the button \"LoginTab\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "User enter the value in the text box \"UserEmail\" \"cm.jnanesh@gmail.com\"",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "User enter the value in the text box \"Password\" \"plus500_jna\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "User clicks on the button \"Login\"",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "User is on \"HomePage\"",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "User Verifies the page title \"Plus500 WebTrader | Leading CFD Platform | Online Trading\"",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "User enter the value in the text box \"Search\" \"GBP/USD\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "User Press Enter \"Search\"",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "User Validate \"Sell\" Price with the Threshold \"1.2\" \"1.4\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "User Validate \"Buy\" Price with the Threshold \"1.2\" \"1.4\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "LoginPage",
      "offset": 12
    }
  ],
  "location": "StandardSteps.user_is_on(String)"
});
formatter.result({
  "duration": 1427171435,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "DemoMode",
      "offset": 27
    }
  ],
  "location": "StandardSteps.user_clicks_on_the_Button(String)"
});
formatter.result({
  "duration": 543363929,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "LoginTab",
      "offset": 27
    }
  ],
  "location": "StandardSteps.user_clicks_on_the_Button(String)"
});
formatter.result({
  "duration": 261367954,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "UserEmail",
      "offset": 38
    },
    {
      "val": "cm.jnanesh@gmail.com",
      "offset": 50
    }
  ],
  "location": "StandardSteps.user_enter_the_value_in_the_text_box(String,String)"
});
formatter.result({
  "duration": 298172790,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Password",
      "offset": 38
    },
    {
      "val": "plus500_jna",
      "offset": 49
    }
  ],
  "location": "StandardSteps.user_enter_the_value_in_the_text_box(String,String)"
});
formatter.result({
  "duration": 224942490,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Login",
      "offset": 27
    }
  ],
  "location": "StandardSteps.user_clicks_on_the_Button(String)"
});
formatter.result({
  "duration": 147391554,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "HomePage",
      "offset": 12
    }
  ],
  "location": "StandardSteps.user_is_on(String)"
});
formatter.result({
  "duration": 90987,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Plus500 WebTrader | Leading CFD Platform | Online Trading",
      "offset": 30
    }
  ],
  "location": "StandardSteps.user_Verifies_the_page_title(String)"
});
formatter.result({
  "duration": 9551667,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Search",
      "offset": 38
    },
    {
      "val": "GBP/USD",
      "offset": 47
    }
  ],
  "location": "StandardSteps.user_enter_the_value_in_the_text_box(String,String)"
});
formatter.result({
  "duration": 4014800687,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Search",
      "offset": 18
    }
  ],
  "location": "StandardSteps.user_Press_Enter(String)"
});
formatter.result({
  "duration": 395595572,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sell",
      "offset": 15
    },
    {
      "val": "1.2",
      "offset": 47
    },
    {
      "val": "1.4",
      "offset": 53
    }
  ],
  "location": "StandardSteps.user_Validate_Price_with_the_Threshold(String,String,String)"
});
formatter.result({
  "duration": 10192383239,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Buy",
      "offset": 15
    },
    {
      "val": "1.2",
      "offset": 46
    },
    {
      "val": "1.4",
      "offset": 52
    }
  ],
  "location": "StandardSteps.user_Validate_Price_with_the_Threshold(String,String,String)"
});
formatter.result({
  "duration": 10205735832,
  "status": "passed"
});
formatter.embedding("image/png", "embedded0.png");
formatter.write("url: https://app.plus500.com/trade/major");
formatter.write("name :Validate Selling and Buying Price");
formatter.after({
  "duration": 204536027,
  "status": "passed"
});
});