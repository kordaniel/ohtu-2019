Feature: A new user account can be created if a proper unused username and password are Given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When username "newuser" and password "abcd1234" are entered
        Then system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When username "pekka" and password "abcd1234" are entered
        Then system will respond with "new user not registered"
    
    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When username "ab" and password "abcd1234" are entered
        Then system will respond with "new user not registered"
    
    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When username "newuser" and password "abcd123" are entered
        Then system will respond with "new user not registered"