Feature: A new user account can be created if a proper unused username and password are Given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When username "newuser" and password "abcd1234" are entered
        Then system will respond with "new user registered"