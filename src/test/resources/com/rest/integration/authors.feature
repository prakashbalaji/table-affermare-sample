Feature: Authors

  Background: :
    Given I setup data
    Then I start server

  Scenario: Verify table after post
    Then I make a POST to "authors/123.json" with data
      | {                       |
      | "id": 123,              |
      | "name": "Martin Fowler" |
      | }                       |

    Then I verify that the table "authors" has the following entries
      | id  | name          |
      | 123 | Martin Fowler |


  Scenario: Verify table after put
    Then I make a POST to "authors/123.json" with data
      | {                       |
      | "id": 123,              |
      | "name": "Martin Fowler" |
      | }                       |

    Then I make a PUT to "authors/123.json" with data
      | {                       |
      | "id": 123,              |
      | "name": "martin fowler" |
      | }                       |

    Then I verify that the table "authors" has the following entries
      | id  | name          |
      | 123 | martin fowler |


  Scenario: Verify table after delete
    Then I make a POST to "authors/123.json" with data
      | {                       |
      | "id": 123,              |
      | "name": "Martin Fowler" |
      | }                       |

    Then I make a DELETE to "authors/123.json"

    Then I verify table "authors" is empty


  Scenario: Verify table entries for order
    Then I make a POST to "authors/123.json" with data
      | {                       |
      | "id": 123,              |
      | "name": "Martin Fowler" |
      | }                       |

    Then I make a POST to "authors/124.json" with data
      | {                   |
      | "id": 124,          |
      | "name": "Kent Beck" |
      | }                   |

    Then I verify that the table "authors" has the following entries in the same order
      | id  | name          |
      | 123 | Martin Fowler |
      | 124 | Kent Beck     |



